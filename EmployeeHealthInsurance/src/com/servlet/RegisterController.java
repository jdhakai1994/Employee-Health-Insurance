package com.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Employee;
import com.bean.Policy;
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
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entering doPost() in RegisterController Class");
		
		String action = request.getParameter("action");
		System.out.println("The action retreived is " + action);
		
		EmployeeService es = new EmployeeService();
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
			
//			System.out.println(employeeId);
//			System.out.println(employeeName);
//			System.out.println(dateOfBirth);
//			System.out.println(gender);
//			System.out.println(emailId);
//			System.out.println(altEmailID);
//			System.out.println(mobNo);
//			System.out.println(altMobNo);
//			System.out.println(premiumAmount);
//			System.out.println(accountNo);
//			System.out.println(bankName);
//			System.out.println(ifscCode);
			
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
				String replyEmployee = es.addEmployee(employee);
				int hiId = 0;
				if("success".equals(replyEmployee)){
					String replyPolicy = ps.addPolicy(policy);
					if("success".equals(replyPolicy)){
						hiId = ps.fetchPolicyId(employeeId);
						request.setAttribute("message", "Your details have been successfully noted."
							+ " The registration is pending admin approval."
							+ " The auto-generated health insurance id is "+hiId);
					}
				}
				else if("fail".equals(replyEmployee))
					request.setAttribute("message", "The employee details couldn't be added");
				else if("already exists".equals(replyEmployee)){
					hiId = ps.fetchPolicyId(employeeId);
					request.setAttribute("message", "Your details already exists "
							+ "The registration is pending admin approval."
							+ " The health insurance id is "+hiId);
				}
			} catch (Exception e) {
			e.printStackTrace();
			}			
		}

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/result.jsp");
		rd.forward(request, response);

	}

}
