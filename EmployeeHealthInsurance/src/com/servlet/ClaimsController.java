package com.servlet;

import java.io.IOException;
import java.util.*;

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
			rd.forward(request, response);
		}
		else{
			//the heading to be displayed on the result page
			request.setAttribute("heading", "Claims Management");
			
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
					List<String> beneficiaryNameList = new ArrayList<String>();
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
					
						/*proceed further only if the health insurance policy is approved,
						 * one cannot place claims if approval is pending
						 */
						if(employeeHealthInsuranceId != -1){
							beneficiaryNameList.add(employee.getEmployeeName());
							
							//get the details of the dependents
							List<Dependent> dependentList = new ArrayList<Dependent>();
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
						
						e.printStackTrace();
					}
					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(Integer.toString(healthInsuranceId));
				}
			}
			else if("getHospitalizationClaimForm".equals(action)){
				
				//retrieving name from URL
				String name = request.getParameter("name");
				
				//initial request to load the form
				if(name == null){
					
					String state[] = {"Andaman and Nicobar Islands","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chandigarh",
							"Chhattisgarh","Dadra and Nagar Haveli","Daman and Diu","Delhi","Goa","Gujarat","Haryana","Himachal Pradesh",
							"Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Lakshadweep","Madhya Pradesh","Maharashtra","Manipur",
							"Meghalaya","Mizoram","Nagaland","Odisha","Pondicherry","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana",
							"Tripura","Uttaranchal","Uttar Pradesh","West Bengal"};
					
					ArrayList<String> stateList = new ArrayList<String>(Arrays.asList(state));
								
					//reference to service classes
					EmployeeService es = new EmployeeService();
					PolicyService ps = new PolicyService();
					DependentService ds = new DependentService();
				
					//initializing 
					List<String> beneficiaryNameList = new ArrayList<String>();
					Employee employee = null;
					int employeeId = 0;
					try {
						//get employee details corresponding to username
						employee = es.getEmployeeDetails(username);
					
						/*retrieve mobile number, employee name, employeeId and email-ID 
						 * which is supposed to be auto-populated in the form
						 */
						employeeId = employee.getEmployeeId();
											
						//fetch healthInsuranceId of the employee
						int employeeHealthInsuranceId = ps.fetchPolicyId(employeeId,true);
					
						/*proceed further only if the health insurance policy is approved
						 * one cannot place claims if approval is pending
						 */
						if(employeeHealthInsuranceId != -1){
							beneficiaryNameList.add(employee.getEmployeeName());
							
							//get the details of the dependents
							List<Dependent> dependentList = new ArrayList<Dependent>();
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
						
						e.printStackTrace();
					}
				
					request.setAttribute("employee", employee);
					request.setAttribute("beneficiaryNameList", beneficiaryNameList);
					request.setAttribute("stateList", stateList);
										
					rd = request.getRequestDispatcher("/jsp/forms/hospitalizationClaimForm.jsp");
					rd.forward(request, response);
				}
				//ajax get request
				else{
					//this is retrieved from URL
					int employeeId = Integer.parseInt(request.getParameter("employeeId"));
					String value = request.getParameter("value");
					
					System.out.println("Reached Here");
					if("healthInsuranceId".equals(value)){
						PolicyService ps = new PolicyService();
					
						int healthInsuranceId = 0;
						try {
							healthInsuranceId = ps.fetchPolicyId(employeeId,name);
						} catch (Exception e) {
						
							e.printStackTrace();
						}					
						response.setContentType("text/plain");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(Integer.toString(healthInsuranceId));
					}
					else if("relation".equals(value)){
						
						DependentService ds = new DependentService();
						
						String relation = null;
						try {
							relation = ds.fetchRelation(employeeId,name);
						} catch (Exception e) {

							e.printStackTrace();
						}
						response.setContentType("text/plain");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(relation);
					}
				}
				
			}
			else if("getSearchClaimForm".equals(action)){
				rd = request.getRequestDispatcher("/jsp/forms/searchClaimForm.jsp");
				rd.forward(request, response);
			}
			else if("getUnapprovedDomiciliaryClaimList".equals(action)){
				ClaimsService cs = new ClaimsService();
				List<DomiciliaryClaimApproval> unapprovedDomiciliaryClaimList = new ArrayList<DomiciliaryClaimApproval>();
				try {
					unapprovedDomiciliaryClaimList = cs.getUnapprovedDomiciliaryClaimList();
					request.setAttribute("unapprovedDomiciliaryClaimList", unapprovedDomiciliaryClaimList);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(unapprovedDomiciliaryClaimList.size() != 0)
					request.setAttribute("type", "list");
				else{
					request.setAttribute("type", "message");
					request.setAttribute("message", " No domiciliary claims are pending for approval.");
				}
				rd = request.getRequestDispatcher("/jsp/lists/unapprovedDomiciliaryClaimList.jsp");
				rd.forward(request, response);
			}
			else if("getUnapprovedHospitalizationClaimList".equals(action)){
				ClaimsService cs = new ClaimsService();
				List<HospitalizationClaimApproval> unapprovedHospitalizationClaimList = new ArrayList<HospitalizationClaimApproval>();
				try {
					unapprovedHospitalizationClaimList = cs.getUnapprovedHospitalizationClaimList();
					request.setAttribute("unapprovedHospitalizationClaimList", unapprovedHospitalizationClaimList);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(unapprovedHospitalizationClaimList.size() != 0)
					request.setAttribute("type", "list");
				else{
					request.setAttribute("type", "message");
					request.setAttribute("message", " No hospitalization claims are pending for approval.");
				}
				rd = request.getRequestDispatcher("/jsp/lists/unapprovedHospitalizationClaimList.jsp");
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
		
		RequestDispatcher rd = null;
		
		//the heading to be displayed on the result page
		request.setAttribute("heading", "Claims Management");
						
		//service class references
		ClaimsService cs = new ClaimsService();
		EmployeeService es = new EmployeeService();
		PolicyService ps = new PolicyService();
				
		//this is retrieved from the url mentioned in action attribute in form tag
		String action = request.getParameter("action");
		System.out.println("The action retreived is " + action);
						
		//if-else code block for action
		if("domiciliaryClaim".equals(action)){
			System.out.println("In domiciliaryClaim action if-else block");
			
			//retrieving input values from the domiciliaryClaimForm.jsp 
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
							+ " The auto-generated claim no is "+claimNo+".");
				}
				//case 2 - data couldn't be inserted into claims table
				else{
					request.setAttribute("type", "failure_message");
					request.setAttribute("message", "The claim couldn't be noted successfully.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			rd = request.getRequestDispatcher("/jsp/report/claimReport.jsp");
		}
		else if("hospitalizationClaim".equals(action)){
			System.out.println("In hospitalizationClaim action if-else block");
			
			//retrieving input values from the hospitalizationClaim.jsp
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String mobNo = request.getParameter("mobNo");
			String beneficiaryName = request.getParameter("beneficiaryName");
			int healthInsuranceId = Integer.parseInt(request.getParameter("healthInsuranceId"));
			String hospitalName = request.getParameter("hospitalName");
			String admissionDate = request.getParameter("admissionDate");
			String dischargeDate = request.getParameter("dischargeDate");
			double totalClaimAmount = Double.parseDouble(request.getParameter("totalClaimAmount"));
			String typeOfInjury = request.getParameter("typeOfInjury");
			String alcoholInvolved = request.getParameter("alcoholInvolved");
			String relation = request.getParameter("relation");
			
			//making a HospitalizationClaim bean
			HospitalizationClaim hospitalizationClaim = new HospitalizationClaim();
			hospitalizationClaim.setEmployeeId(employeeId);
			hospitalizationClaim.setHealthInsuranceId(healthInsuranceId);
			hospitalizationClaim.setMobNo(mobNo);
			hospitalizationClaim.setBeneficiaryName(beneficiaryName);
			hospitalizationClaim.setAdmissionDate(admissionDate);
			hospitalizationClaim.setDischargeDate(dischargeDate);
			hospitalizationClaim.setTotalClaimAmount(totalClaimAmount);
			hospitalizationClaim.setTypeOfInjury(typeOfInjury);
			hospitalizationClaim.setAlcoholInvolved(alcoholInvolved);
			hospitalizationClaim.setHospitalName(hospitalName);
			hospitalizationClaim.setRelation(relation);
			
			try {
				int claimNo = cs.submitHospitalizationClaim(hospitalizationClaim);
				/*case 1 - successfully inserted in claim and hospitalization claim table
				 *displaying the claim number generated 
				 */
				if(claimNo != 0){
					request.setAttribute("type", "success_message");
					request.setAttribute("message", "Your claim have been successfully "
							+ "noted. The claim is pending admin approval."
							+ " The auto-generated claim no is "+claimNo+".");
				}
				//case 2 - data couldn't be inserted into claims table
				else{
					request.setAttribute("type", "failure_message");
					request.setAttribute("message", "The claim couldn't be noted successfully.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			rd = request.getRequestDispatcher("/jsp/report/claimReport.jsp");
		}
		else if("search_claim".equals(action)){
			System.out.println("In search_claim action if-else block");
			
			//this is retrieved from the hidden value passed while submitting the form
			String action1 = request.getParameter("action1");
			System.out.println("The action1 retreived is " + action1);
			
			List<Claim> claimList = new ArrayList<Claim>();
			List<Integer> healthInsuranceIdList = new ArrayList<Integer>();
			Employee employee = null;
			
			//get reference to existing session
			HttpSession session = request.getSession(false);
			String username = (String) session.getAttribute("username");
			
			try {
				employee = es.getEmployeeDetails(username);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			int employeeId = employee.getEmployeeId();
			
			try {
				healthInsuranceIdList = ps.fetchPolicyIdList(employeeId);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			//if-else code block for action1
			if("searchClaim".equals(action1)){
				
				//retrieving input values from the searchClaimForm.jsp
				String claimType = request.getParameter("claimType");
				String relation = request.getParameter("relation");
				
				int[] healthInsuranceIdArray = new int[healthInsuranceIdList.size()];
			    for (int i=0; i < healthInsuranceIdArray.length; i++)
			    {
			    	healthInsuranceIdArray[i] = healthInsuranceIdList.get(i).intValue();
			    }
				
				try {
					claimList = cs.searchClaim(claimType,relation,healthInsuranceIdArray);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(claimList.isEmpty()){
					request.setAttribute("type", "failure_message");
					request.setAttribute("message", "You have no such claim corresponding to that details.");
				}
				else{
					request.setAttribute("type", "report");
					request.setAttribute("claimList", claimList);
				}
			}
			else if("searchClaimByHealthInsuranceId".equals(action1)){
				
				//retrieving input values from the searchClaimForm.jsp
				int healthInsuranceId = Integer.parseInt(request.getParameter("healthInsuranceId"));
				
				if(healthInsuranceIdList.contains(healthInsuranceId)){
					try {
						claimList = cs.searchClaimByHealthInsuranceId(healthInsuranceId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(claimList.isEmpty()){
						request.setAttribute("type", "failure_message");
						request.setAttribute("message", "You have no such claim corresponding to that health insurance Id.");
					}
					else{
						request.setAttribute("type", "report");
						request.setAttribute("claimList", claimList);
					}
				}
				else{
					request.setAttribute("type", "failure_message");
					request.setAttribute("message", "The health insurance Id entered doesn't belong to you or any of your dependents.");
				}
			}
			rd = request.getRequestDispatcher("/jsp/report/claimReport.jsp");
		}
		else if("approve_domiciliary_claim".equals(action)){
			System.out.println("In approve_domiciliary_claim action if-else block");
			
			//retrieving list of check marked Claim No
			String approvedClaimNo [] = request.getParameterValues("approval");
			String rejectedClaimNo [] = request.getParameterValues("rejection");
			String approvedClaimAmount [] = request.getParameterValues("approvedAmount");
			
			//combine the claim no and approved amount to one model
			Map<Integer, Double> combinations = new HashMap<Integer, Double>();
			
			//to avoid null pointer exception if all claims are rejected
			if(approvedClaimNo != null){
				for(int i=0; i<approvedClaimNo.length; i++)
					combinations.put(Integer.parseInt(approvedClaimNo[i]), Double.parseDouble(approvedClaimAmount[i]));
			}
			
			try {
				int count = cs.approveDomiciliaryClaim(combinations, rejectedClaimNo);
				request.setAttribute("type", "success_message");
				request.setAttribute("message", count+" policies have been modified.");
			} catch (Exception e) {
				e.printStackTrace();
			}
			rd = request.getRequestDispatcher("/jsp/report/claimReport.jsp");
		}
		else if("approve_hospitalization_claim".equals(action)){
			System.out.println("In approve_hospitalization_claim action if-else block");
			
			//retrieving list of check marked Claim No
			String approvedClaimNo [] = request.getParameterValues("approval");
			String rejectedClaimNo [] = request.getParameterValues("rejection");
			String approvedClaimAmount [] = request.getParameterValues("approvedAmount");
			
			//combine the claim no and approved amount to one model
			Map<Integer, Double> combinations = new HashMap<Integer, Double>();
			
			//to avoid null pointer exception if all claims are rejected
			if(approvedClaimNo != null){
				for(int i=0; i<approvedClaimNo.length; i++)
					combinations.put(Integer.parseInt(approvedClaimNo[i]), Double.parseDouble(approvedClaimAmount[i]));
			}
			
			try {
				int count = cs.approveHospitalizationClaim(combinations, rejectedClaimNo);
				request.setAttribute("type", "success_message");
				request.setAttribute("message", count+" policies have been modified.");
			} catch (Exception e) {
				e.printStackTrace();
			}
			rd = request.getRequestDispatcher("/jsp/report/claimReport.jsp");
		}
		
		System.out.println("Exiting doPost() in ClaimsController Class");
		rd.forward(request, response);
		
	}

}
