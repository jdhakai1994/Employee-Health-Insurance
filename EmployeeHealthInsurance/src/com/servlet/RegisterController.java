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

import com.bean.*;
import com.services.*;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entering doGet() in RegisterController Class");
		
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
		
			//this is retrieved from the URL
			String action = request.getParameter("action");
			System.out.println("The action retreived is " + action);
		
			//if-else code block for action
			if("getRegisterEmployeeForm".equals(action))
				rd = request.getRequestDispatcher("/jsp/forms/employeeRegisterForm.jsp");
			else if("getDependentEmployeeForm".equals(action))
				rd = request.getRequestDispatcher("/jsp/forms/dependentRegisterForm.jsp");				
			else if("getECardForm".equals(action)){
				
				//reference to service classes
				EmployeeService es = new EmployeeService();
				PolicyService ps = new PolicyService();
				DependentService ds = new DependentService();
				
				//initializing 
				ArrayList<String> list = new ArrayList<>();
				int employeeId = 0;
				try {
					//get employee details corresponding to username
					Employee employee = es.getEmployeeDetails(username);
					
					//retrieve employeeId
					employeeId = employee.getEmployeeId();
					
					//fetch healthInsuranceId of the employee
					int employeeHealthInsuranceId = ps.fetchPolicyId(employeeId,true);
					
					/*proceed further only if the health insurance policy is approved
					 * one cannot generate e-card if approval is pending
					 */
					if(employeeHealthInsuranceId != -1){
						list.add("self");
						
						//get the details of the dependents
						ArrayList<Dependent> dependentList = new ArrayList<>();
						dependentList = ds.fetchDependentDetails(employee.getEmployeeId());
						for(Dependent dependent : dependentList){
							//fetch health Insurance Id of dependents
							int dependentHealthInsuranceId = ps.fetchPolicyId(employeeId, dependent.getDependentId(),true);
							
							/*add only if the health insurance policy of dependent is 
							 * approved, one cannot generate e-card if approval is pending
							 */
							if(dependentHealthInsuranceId != -1)
								list.add(dependent.getRelation());
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("list", list);
				
				rd = request.getRequestDispatcher("/jsp/forms/e_CardForm.jsp");
			}
			else if("getUnapprovedEmployeePolicyList".equals(action)){
				PolicyService ps = new PolicyService();
				try {
					ArrayList<EmployeeApproval> unapprovedEmployeeList = ps.getUnapprovedEmployeePolicy();
					request.setAttribute("unapprovedEmployeeList", unapprovedEmployeeList);
				} catch (Exception e) {
					e.printStackTrace();
				}
				rd = request.getRequestDispatcher("/jsp/unapprovedEmployeePolicyList.jsp");
			}
			else if("getUnapprovedDependentPolicyList".equals(action)){
				PolicyService ps = new PolicyService();
				try {
					ArrayList<DependentApproval> unapprovedDependentList = ps.getUnapprovedDependentPolicy();
					request.setAttribute("unapprovedDependentList", unapprovedDependentList);
				} catch (Exception e) {
					e.printStackTrace();
				}
				rd = request.getRequestDispatcher("/jsp/unapprovedDependentPolicyList.jsp");
			}
		}
		
		System.out.println("Exiting doGet() in RegisterController Class");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entering doPost() in RegisterController Class");

		//service class references
		PolicyService ps = new PolicyService();
		EmployeeService es = new EmployeeService();
		DependentService ds = new DependentService();
		
		//this is retrieved from the hidden value passed while submitting the form
		String action = request.getParameter("action");
		System.out.println("The action retreived is " + action);
		
		//the heading to be displayed on the result page
		request.setAttribute("heading", "Registration Management");
		
		//if-else code block for action
		if("register_employee".equals(action)){
			System.out.println("In register_employee action if-else block");
			
			//retrieving input values from the employeeRegisterForm.jsp 
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String employeeName = request.getParameter("employeeName");
			String dateOfBirth = request.getParameter("dateOfBirth");
			String gender = request.getParameter("gender");
			String emailId = request.getParameter("emailId");
			String altEmailId = request.getParameter("altEmailId");
			String mobNo = request.getParameter("mobNo");
			String altMobNo = request.getParameter("altMobNo");
			double premiumAmount = Double.parseDouble(request.getParameter("premiumAmount"));
			String accountNo = request.getParameter("accountNo");
			String bankName = request.getParameter("bankName");
			String ifscCode = request.getParameter("ifscCode");
			
			//getting reference to existing session
			HttpSession session = request.getSession(false);
			String username = (String) session.getAttribute("username");
			
			String startDate = request.getParameter("policyStartDate");
			int policyPeriod = Integer.parseInt(request.getParameter("policyPeriod"));
			double totalSumInsured = Double.parseDouble(request.getParameter("totalSumInsured"));
						
			//making an employee bean
			Employee employee = new Employee();
			employee.setEmployeeId(employeeId);
			employee.setEmployeeName(employeeName);
			employee.setDateOfBirth(dateOfBirth);
			employee.setGender(gender);
			employee.setEmailId(emailId);
			employee.setAltEmailId(altEmailId);
			employee.setMobNo(mobNo);
			employee.setAltMobNo(altMobNo);
			employee.setPremiumAmount(premiumAmount);
			employee.setAccountNo(accountNo);
			employee.setBankName(bankName);
			employee.setIfscCode(ifscCode);
			employee.setUsername(username);
			
			//making a policy bean
			Policy policy = new Policy();
			policy.setEmployeeId(employeeId);
			policy.setStartDate(startDate);
			policy.setPolicyPeriod(policyPeriod);
			policy.setTotalSumInsured(totalSumInsured);
			
			try{
				String replyEmployee = es.addEmployee(employee);
				int healthInsuranceId = 0;
				/*case 1 - successfully inserted in employee table
				 *data need to be inserted in policy table
				 *displaying the healthInsuranceId generated 
				 */
				if("success".equals(replyEmployee)){
					String replyPolicy = ps.addPolicy(policy);
					if("success".equals(replyPolicy)){
						healthInsuranceId = ps.fetchPolicyId(employeeId, false);
						request.setAttribute("message", "Your details have been successfully noted."
							+ " The registration is pending admin approval."
							+ " The auto-generated health insurance id is "+healthInsuranceId);
					}
				}
				//case 2 - data couldn't be inserted in employee table
				else if("fail".equals(replyEmployee))
					request.setAttribute("message", "The details couldn't be added");
				/*case 3 - entry already exists in employee table
				 * fetching healthInsuranceId and displaying
				 */
				else if("already exists".equals(replyEmployee)){
					healthInsuranceId = ps.fetchPolicyId(employeeId, false);
					request.setAttribute("message", "Your details already exists "
							+ "The registration is pending admin approval."
							+ " The health insurance id is "+healthInsuranceId);
				}
			} catch (Exception e) {
			e.printStackTrace();
			}			
		}
		else if("register_dependent".equals(action)){
			System.out.println("In register_dependent action if-else block");
			
			//retrieving input values from the dependentRegisterForm.jsp			
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String beneficiaryName = request.getParameter("beneficiaryName");
			String dateOfBirth = request.getParameter("dateOfBirth");
			String gender = request.getParameter("gender");
			String relation = request.getParameter("relation");
			
			String startDate = request.getParameter("policyStartDate");
			int policyPeriod = Integer.parseInt(request.getParameter("policyPeriod"));
			double totalSumInsured = Double.parseDouble(request.getParameter("totalSumInsured"));
			double premiumAmount = Double.parseDouble(request.getParameter("premiumAmount"));
			
			//making a dependent bean
			Dependent dependent = new Dependent();
			dependent.setEmployeeId(employeeId);
			dependent.setBeneficiaryName(beneficiaryName);
			dependent.setDateOfBirth(dateOfBirth);
			dependent.setGender(gender);
			dependent.setRelation(relation);
			
			//making a policy bean
			Policy policy = new Policy();
			policy.setEmployeeId(employeeId);
			policy.setStartDate(startDate);
			policy.setPolicyPeriod(policyPeriod);
			policy.setTotalSumInsured(totalSumInsured);
			
			//retrieving the value of submit button Add/Update/Delete
			String submit = request.getParameter("submit");
			System.out.println("The submit button pressed is " + submit);
			
			if("Add".equals(submit)){
				try{
					String replyDependent = ds.addDependent(dependent);
					int healthInsuranceId = 0;
					/*case 1 - successfully inserted in dependent table
					 *data need to be inserted in policy table
					 *displaying the healthInsuranceId generated 
					 */
					if("success".equals(replyDependent)){
						int dependentId = ds.fetchDependentId(employeeId, relation);
						policy.setDependentId(dependentId);
						String replyPolicy = ps.addPolicy(policy);
						if("success".equals(replyPolicy)){
							healthInsuranceId = ps.fetchPolicyId(employeeId, dependentId, false);
							request.setAttribute("message", "Your dependent details have been successfully noted."
								+ " The registration is pending admin approval."
								+ " The auto-generated health insurance id is "+healthInsuranceId);
						}
					}
				    //case 2 - data couldn't be inserted in employee table
					else if("fail".equals(replyDependent))
						request.setAttribute("message", "The details couldn't be added");
					/*case 3 - entry already exists in dependent table
					 * fetching healthInsuranceId and displaying
					 */
					else if("already exists".equals(replyDependent)){
						int dependentId = ds.fetchDependentId(employeeId, relation);
						healthInsuranceId = ps.fetchPolicyId(employeeId, dependentId, false);
						request.setAttribute("message", "Your details already exists "
								+ "The registration is pending admin approval."
								+ " The health insurance id is "+healthInsuranceId);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}
		else if("approve_employee".equals(action)){
			System.out.println("In approve_employee action if-else block");
			
			//retrieving list of check marked healthInsuranceId
			String approvedHealthInsuranceId [] = request.getParameterValues("approved");
			System.out.println(approvedHealthInsuranceId.length);
			try {
				int count = ps.approvePolicy(approvedHealthInsuranceId);
				request.setAttribute("message", count+" policies have been approved");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if("approve_dependent".equals(action)){
			System.out.println("In approve_dependent action if-else block");
			
			//retrieving list of check marked healthInsuranceId
			String approvedHealthInsuranceId [] = request.getParameterValues("approved");
			System.out.println(approvedHealthInsuranceId.length);
			try {
				int count = ps.approvePolicy(approvedHealthInsuranceId);
				request.setAttribute("message", count+" policies have been approved");
				es.updatePremiumAmount(approvedHealthInsuranceId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if("generate_eCard".equals(action)){
			System.out.println("In generate_eCard action if-else block");
			
			String relation = request.getParameter("e-card");
			
			//get reference to existing session
			HttpSession session = request.getSession(false);
			String username = (String) session.getAttribute("username");
			
			ECard ecard = new ECard();
			ecard = null;
			try {
				ecard = ps.getECardDetails(username, relation);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("type", "report");
			request.setAttribute("ecard", ecard);			
		}
		
		System.out.println("Exiting doPost() in RegisterController Class");

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/result.jsp");
		rd.forward(request, response);
	}
}
