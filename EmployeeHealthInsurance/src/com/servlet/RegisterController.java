package com.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Dependent;
import com.bean.Employee;
import com.bean.EmployeeApproval;
import com.bean.Policy;
import com.services.DependentService;
import com.services.EmployeeService;
import com.services.HospitalService;
import com.services.PolicyService;

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
		
		String action = request.getParameter("action");
		System.out.println("The action retreived is " + action);
		
		//If/else block for action
		if("getRegisterEmployeeForm".equals(action))
			rd = request.getRequestDispatcher("/jsp/forms/employeeRegisterForm.jsp");
		else if("getDependentEmployeeForm".equals(action))
			rd = request.getRequestDispatcher("/jsp/forms/dependentRegisterForm.jsp");				
		else if("getECardForm".equals(action))
			rd = request.getRequestDispatcher("/jsp/forms/e_CardForm.jsp");
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
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entering doPost() in RegisterController Class");
		
		String action = request.getParameter("action");
		System.out.println("The action retreived is " + action);
		
		PolicyService ps = new PolicyService();
		
		if("register_employee".equals(action)){
			System.out.println("In register_employee if/else action block");
			
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
			
			String startDate = request.getParameter("policyStartDate");
			int policyPeriod = Integer.parseInt(request.getParameter("policyPeriod"));
			double totalSumInsured = Double.parseDouble(request.getParameter("totalSumInsured"));
						
			//Making a bean
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
			
			Policy policy = new Policy();
			policy.setEmployeeId(employeeId);
			policy.setStartDate(startDate);
			policy.setPolicyPeriod(policyPeriod);
			policy.setTotalSumInsured(totalSumInsured);
			
			try{
				EmployeeService es = new EmployeeService();
				String replyEmployee = es.addEmployee(employee);
				int healthInsuranceId = 0;
				if("success".equals(replyEmployee)){
					String replyPolicy = ps.addPolicy(policy);
					if("success".equals(replyPolicy)){
						healthInsuranceId = ps.fetchPolicyId(employeeId);
						request.setAttribute("message", "Your details have been successfully noted."
							+ " The registration is pending admin approval."
							+ " The auto-generated health insurance id is "+healthInsuranceId);
					}
				}
				else if("fail".equals(replyEmployee))
					request.setAttribute("message", "The details couldn't be added");
				else if("already exists".equals(replyEmployee)){
					healthInsuranceId = ps.fetchPolicyId(employeeId);
					request.setAttribute("message", "Your details already exists "
							+ "The registration is pending admin approval."
							+ " The health insurance id is "+healthInsuranceId);
				}
			} catch (Exception e) {
			e.printStackTrace();
			}			
		}
		else if("register_dependent".equals(action)){
			System.out.println("In register_dependent if/else action block");
			
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String beneficiaryName = request.getParameter("beneficiaryName");
			String dateOfBirth = request.getParameter("dateOfBirth");
			String gender = request.getParameter("gender");
			String relation = request.getParameter("relation");
			
			String startDate = request.getParameter("policyStartDate");
			int policyPeriod = Integer.parseInt(request.getParameter("policyPeriod"));
			double totalSumInsured = Double.parseDouble(request.getParameter("totalSumInsured"));
			double premiumAmount = Double.parseDouble(request.getParameter("premiumAmount"));
			
			Dependent dependent = new Dependent();
			dependent.setEmployeeId(employeeId);
			dependent.setBeneficiaryName(beneficiaryName);
			dependent.setDateOfBirth(dateOfBirth);
			dependent.setGender(gender);
			dependent.setRelation(relation);
			
			Policy policy = new Policy();
			policy.setEmployeeId(employeeId);
			policy.setStartDate(startDate);
			policy.setPolicyPeriod(policyPeriod);
			policy.setTotalSumInsured(totalSumInsured);
			
			String submit = request.getParameter("submit");
			System.out.println("The submit button pressed is " + submit);
			
			if("Add".equals(submit)){
				try{
					DependentService ds = new DependentService();
					String replyDependent = ds.addDependent(dependent);
					int healthInsuranceId = 0;
					if("success".equals(replyDependent)){
						int dependentId = ds.fetchDependentId(employeeId, relation);
						policy.setDependentId(dependentId);
						String replyPolicy = ps.addPolicy(policy);
						if("success".equals(replyPolicy)){
							healthInsuranceId = ps.fetchPolicyId(employeeId, dependentId);
							request.setAttribute("message", "Your dependent details have been successfully noted."
								+ " The registration is pending admin approval."
								+ " The auto-generated health insurance id is "+healthInsuranceId);
						}
					}
					else if("fail".equals(replyDependent))
						request.setAttribute("message", "The details couldn't be added");
					else if("already exists".equals(replyDependent)){
						int dependentId = ds.fetchDependentId(employeeId, relation);
						healthInsuranceId = ps.fetchPolicyId(employeeId, dependentId);
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
			System.out.println("In approve_employee if/else action block");
			
			String approvedHealthInsuranceId [] = request.getParameterValues("approved");
			System.out.println(approvedHealthInsuranceId.length);
			try {
				int count = ps.approvePolicy(approvedHealthInsuranceId);
				request.setAttribute("message", count+" policies have been approved");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		
		System.out.println("Exiting doPost() in RegisterController Class");

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/result.jsp");
		rd.forward(request, response);

	}

}
