package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.services.*;
import com.bean.*;

/**
 * Servlet implementation class ClaimsController
 */
@WebServlet("/ClaimsController")
public class ClaimsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClaimsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entering doGet() in ClaimsController Class");
		
		RequestDispatcher rd = null;
		
		//get reference to existing session
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
				
		//redirecting the user to login page if session has expired
		if(username == null){
			request.setAttribute("message", "Your session has expired, please login again to continue");
			rd = request.getRequestDispatcher("jsp/forms/loginForm.jsp");
		}
		else{
			
			//this is retrieved from URL
			String action = request.getParameter("action");
			
			//if-else code block for action
			if("getDomiciliaryClaimForm".equals(action)){
				
				//retrieving name from URL
				String name = request.getParameter("name");
				
				//initial request to load the form
				if(name == null){					
								
					//reference to service classes
					EmployeeService es = new EmployeeService();
					PolicyService ps = new PolicyService();
					DependentService ds = new DependentService();
				
					//initializing 
					ArrayList<String> beneficiaryNameList = new ArrayList<>();
					String mobNo = "";
					String altEmailId = "";
					int employeeId = 0;
					try {
						//get employee details corresponding to username
						Employee employee = es.getEmployeeDetails(username);
					
						/*retrieve mobile number, employeeId and alternate Email-ID 
						 * which is supposed to be auto-populated in the form
						 */
						mobNo = employee.getMobNo();
						employeeId = employee.getEmployeeId();
						altEmailId = employee.getAltEmailId();
					
						//fetch healthInsuranceId of the employee
						int employeeHealthInsuranceId = ps.fetchPolicyId(employeeId,true);
					
						/*proceed further only if the health insurance policy is approved
						 * one cannot place claims if approval is pending
						 */
						if(employeeHealthInsuranceId != -1){
							beneficiaryNameList.add(employee.getEmployeeName());
							
							//get the details of the dependents
							ArrayList<Dependent> dependentList = new ArrayList<>();
							dependentList = ds.fetchDependentDetails(employeeId);
							for(Dependent dependent : dependentList){
								//fetch health Insurance Id of dependents
								int dependentHealthInsuranceId = ps.fetchPolicyId(employeeId, dependent.getDependentId(),true);
							
								/*add only if the health insurance policy of dependent is 
								 * approved, one cannot place claims if approval is pending
								 */
								if(dependentHealthInsuranceId != -1)
									beneficiaryNameList.add(dependent.getBeneficiaryName());
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					request.setAttribute("employeeId", employeeId);
					request.setAttribute("beneficiaryNameList", beneficiaryNameList);
					request.setAttribute("mobNo", mobNo);
					request.setAttribute("altEmailId", altEmailId);
					
					rd = request.getRequestDispatcher("/jsp/forms/domiciliaryClaimForm.jsp");
					rd.forward(request, response);
				}
				//ajax get request
				else{
					//this is retrieved from URL
					int employeeId = Integer.parseInt(request.getParameter("employeeId"));
					
					PolicyService ps = new PolicyService();
					
					int healthInsuranceId = 0;
					try {
						healthInsuranceId = ps.fetchPolicyId(employeeId,name);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
					response.setContentType("text/xml");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(Integer.toString(healthInsuranceId));
				}
			}
			else if("getHospitalizationClaimForm".equals(action)){
				rd = request.getRequestDispatcher("/jsp/forms/hospitalizationClaimForm.jsp");
				rd.forward(request, response);
			}
			else if("searchClaimForm".equals(action)){
				rd = request.getRequestDispatcher("/jsp/forms/searchClaimForm.jsp");
				rd.forward(request, response);
			}
		
		System.out.println("Exiting doGet() in ClaimsController Class");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entering doPost() in ClaimsController Class");
		
		//service class references
		ClaimsService cs = new ClaimsService();
		
		
		//this is retrieved from the hidden value passed while submitting the form
		String action = request.getParameter("action");
		System.out.println("The action retreived is " + action);
			
		//the heading to be displayed on the result page
		request.setAttribute("heading", "Claims Management");
				
		//if-else code block for action
		if("domiciliaryClaim".equals(action)){
			System.out.println("In domiciliaryClaim action if-else block");
			
			//retrieving input values from the employeeRegisterForm.jsp 
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String mobNo = request.getParameter("mobNo");
			String beneficiaryName = request.getParameter("beneficiaryName");
			int healthInsuranceId = Integer.parseInt(request.getParameter("healthInsuranceId"));
			String dateOfInjury = request.getParameter("dateOfInjury");
			String treatmentStartDate = request.getParameter("treatmentStartDate");
			double totalClaimAmount = Double.parseDouble(request.getParameter("totalClaimAmount"));
			String typeOfInjury = request.getParameter("typeOfInjury");
			
			//making a DomiciliaryClaim bean
			DomiciliaryClaim domiciliaryClaim = new DomiciliaryClaim();
			domiciliaryClaim.setEmployeeId(employeeId);
			domiciliaryClaim.setHealthInsuranceId(healthInsuranceId);
			domiciliaryClaim.setMobNo(mobNo);
			domiciliaryClaim.setBeneficiaryName(beneficiaryName);
			domiciliaryClaim.setDateOfInjury(dateOfInjury);
			domiciliaryClaim.setTreatmentStartDate(treatmentStartDate);
			domiciliaryClaim.setTotalClaimAmount(totalClaimAmount);
			domiciliaryClaim.setTypeOfInjury(typeOfInjury);
			
			try {
				int claimNo = cs.submitDomiciliaryClaim(domiciliaryClaim);
				/*case 1 - successfully inserted in claim and domiciliary claim table
				 *displaying the claim number generated 
				 */
				if(claimNo != 0){
					request.setAttribute("type", "success_message");
					request.setAttribute("message", "Your claim have been successfully "
							+ "noted. The claim is pending admin approval."
							+ " The auto-generated claim no is "+claimNo);
				}
				//case 2 - data couldn't be inserted into claims table
				else{
					request.setAttribute("type", "failure_message");
					request.setAttribute("message", "The claim couldn't be noted successfully");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("Exiting doPost() in ClaimsController Class");
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/result.jsp");
		rd.forward(request, response);
		
	}

}
