package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Dependent;
import com.bean.Employee;
import com.bean.Hospital;
import com.google.gson.Gson;
import com.services.DependentService;
import com.services.EmployeeService;
import com.services.HospitalService;
import com.services.PolicyService;

/**
 * Servlet implementation class HospitalController
 */
@WebServlet("/HospitalController")
public class HospitalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entering doGet() in HospitalController Class");
		
		String state[] = {"Andaman and Nicobar Islands","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chandigarh",
				"Chhattisgarh","Dadra and Nagar Haveli","Daman and Diu","Delhi","Goa","Gujarat","Haryana","Himachal Pradesh",
				"Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Lakshadweep","Madhya Pradesh","Maharashtra","Manipur",
				"Meghalaya","Mizoram","Nagaland","Orissa","Pondicherry","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana",
				"Tripura","Uttaranchal","Uttar Pradesh","West Bengal"};
		
		List<String> stateList = new ArrayList<String>(Arrays.asList(state));
		
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
			
			//the heading to be displayed on the page
			request.setAttribute("heading", "Hospital Management");
		
			//retrieving action from URL
			String action = request.getParameter("action");
			System.out.println("The action retreived is " + action);
		
			//reference to service classes			
			HospitalService hs = new HospitalService();
			EmployeeService es = new EmployeeService();
			PolicyService ps = new PolicyService();
			DependentService ds = new DependentService();
			
			//if-else code block for action
			if("getAddHospitalForm".equals(action)){
			
				System.out.println("In add_hospital action if-else block");
			
				request.setAttribute("stateList", stateList);
			
				//to fetch the hospitalId
				try {
					int hospitalId = hs.fetchHospitalId();
					request.setAttribute("hospitalId", hospitalId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				rd = request.getRequestDispatcher("/jsp/forms/addHospitalForm.jsp");
				rd.forward(request, response);
			}
			else if("getModifyHospitalForm".equals(action)){
				System.out.println("In modify_hospital action if-else block");
				
				List<Integer> hospitalIdList = new ArrayList<Integer>();
				try {
					hospitalIdList = hs.fetchHospitalIdList();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("hospitalIdList", hospitalIdList);
				rd = request.getRequestDispatcher("/jsp/forms/searchHospitalForm1.jsp");
				rd.forward(request, response);
			}
			else if("getSearchHospitalForm".equals(action)){
				System.out.println("In search_hospital action if-else block");
				String stateName = request.getParameter("state");
				if(stateName != null){
					List<String> cityList = new ArrayList<>();
				    try {
						cityList = hs.getCityList(stateName);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				    String json = new Gson().toJson(cityList);

				    response.setContentType("application/json");
				    response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(json); 
				}
				else{
					request.setAttribute("stateList", stateList);
					rd = request.getRequestDispatcher("/jsp/forms/searchHospitalForm.jsp");
					rd.forward(request, response);
				}
			}
			else if("getValueAddedServicesForm".equals(action)){
				System.out.println("In value_added_services action if-else block");
				
				String beneficiaryName = request.getParameter("beneficiaryName");
				String stateName = request.getParameter("state");
				if(stateName != null && beneficiaryName == null){
					List<String> cityList = new ArrayList<String>();
				    try {
						cityList = hs.getCityList(stateName);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				    String json = new Gson().toJson(cityList);

				    response.setContentType("application/json");
				    response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(json); 
				}
				if(beneficiaryName != null && stateName == null){
					int employeeId = Integer.parseInt(request.getParameter("employeeId"));
					int healthInsuranceId = 0;
					try {
						healthInsuranceId = ps.fetchPolicyId(employeeId, beneficiaryName);
					} catch (Exception e) {
					
						e.printStackTrace();
					}					
					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(Integer.toString(healthInsuranceId));					
				}
				else if(beneficiaryName == null && stateName == null){
					//initializing 
					List<String> beneficiaryNameList = new ArrayList<String>();
					String mobNo = "";
					String emailId = "";
					String employeeName = "";
					int employeeId = 0;
					try {
						//get employee details corresponding to username
						Employee employee = es.getEmployeeDetails(username);
				
						/*retrieve mobile number, employeeName, employeeId and email-ID 
						 * which is supposed to be auto-populated in the form
						 */
						mobNo = employee.getMobNo();
						employeeName = employee.getEmployeeName();
						employeeId = employee.getEmployeeId();
						emailId = employee.getEmailId();
				
						//fetch healthInsuranceId of the employee
						int employeeHealthInsuranceId = ps.fetchPolicyId(employeeId,true);
				
						/*proceed further only if the health insurance policy is approved,
						 * one cannot place check-up request if approval is pending
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
								 * approved, one cannot place check-up request if approval is pending
								 */
								if(dependentHealthInsuranceId != -1)
									beneficiaryNameList.add(dependent.getBeneficiaryName());
							}
						}
					} catch (Exception e) {					
						e.printStackTrace();
					}
			
					request.setAttribute("employeeId", employeeId);
					request.setAttribute("employeeName", employeeName);
					request.setAttribute("beneficiaryNameList", beneficiaryNameList);
					request.setAttribute("mobNo", mobNo);
					request.setAttribute("emailId", emailId);
					request.setAttribute("stateList", stateList);
				
					rd = request.getRequestDispatcher("/jsp/forms/valueAddedServicesForm.jsp");
					rd.forward(request, response);
				}
			}
		}
		System.out.println("Exiting doGet() in HospitalController Class");				
	}

	/**
	 * @param "message" 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entering doPost() in HospitalController Class");
		
		//this is retrieved from the hidden value passed while submitting the form
		String action = request.getParameter("action");
		System.out.println("The action retreived is " + action);
		
		//the heading to be displayed on the result page
		request.setAttribute("heading", "Hospital Management");
		RequestDispatcher rd = null;
		HospitalService hs = new HospitalService();
				
		//if-else code block for action		
		if("add_hospital".equals(action)){
			System.out.println("In add_hospital action if-else block");
			
			//retrieving data from addHospitalForm.jsp
			String hospitalName = request.getParameter("hospitalName");
			String address = request.getParameter("address");
			String cityName = request.getParameter("cityName");
			String stateName = request.getParameter("stateName");
			String pincode = request.getParameter("pincode");
			String stdcode = request.getParameter("stdcode");
			String phNo = request.getParameter("phNo");
			
			//making a hospital bean
			Hospital hospital = new Hospital();
			hospital.setHospitalName(hospitalName);
			hospital.setAddress(address);
			hospital.setCityName(cityName);
			hospital.setStateName(stateName);
			hospital.setPincode(pincode);
			hospital.setStdcode(stdcode);
			hospital.setPhNo(phNo);
			
			try {
				String reply = hs.addHospital(hospital);
				if("success".equals(reply)){
					request.setAttribute("type", "success_message");
					request.setAttribute("message", "The hospital details have been successfully added.");
					rd = request.getRequestDispatcher("/jsp/report/hospitalReport.jsp");
				}else if("fail".equals(reply)){
					request.setAttribute("type", "failure_message");
					request.setAttribute("message", "The hospital details couldn't be added.");
					rd = request.getRequestDispatcher("/jsp/report/hospitalReport.jsp");
				}else if("already exists".equals(reply)){
					request.setAttribute("type", "failure_message");
					request.setAttribute("message", "The hospital details already exists.");
					rd = request.getRequestDispatcher("/jsp/report/hospitalReport.jsp");
				}
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		else if("search_hospital".equals(action)){
			System.out.println("In search_hospital action if-else block");
			
			//this is retrieved from the hidden value passed while submitting the form
			String action1 = request.getParameter("action1");
			System.out.println("The action1 retreived is " + action1);
			
			//if-else code block for action1
			if("searchHospitalById".equals(action1)){
				int hospitalId = Integer.parseInt(request.getParameter("hospitalId"));
				try {
					Hospital hospital = hs.searchHospital(hospitalId);
					if(hospital == null){
						request.setAttribute("type", "failure_message");
						request.setAttribute("message", "The hospital details doesn't exist.");
						rd = request.getRequestDispatcher("/jsp/report/hospitalReport.jsp");
					}else{
						request.setAttribute("hospitaldetails", hospital);
						rd = request.getRequestDispatcher("/jsp/forms/modifyHospitalForm.jsp");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if("searchHospitalByLocation".equals(action1)){
				String stateName = request.getParameter("stateName");
				String cityName = request.getParameter("cityName");
				List<Hospital> hospitalList = new ArrayList<Hospital>();
				try {
					hospitalList = hs.searchHospital(stateName, cityName);
					if(hospitalList == null){
						request.setAttribute("type", "failure_message");
						request.setAttribute("message", "There is no registered hospital in that city.");
						rd = request.getRequestDispatcher("/jsp/report/hospitalReport.jsp");
					}else
						request.setAttribute("type", "list");
						request.setAttribute("hospitalList", hospitalList);
						rd = request.getRequestDispatcher("/jsp/report/hospitalReport.jsp");
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
			else if("searchHospitalByPin".equals(action1)){
				String pincode = request.getParameter("pincode");
				try {
					Hospital hospital = hs.searchHospital(pincode);
					System.out.println(hospital);
					if(hospital == null){
						request.setAttribute("type", "failure_message");
						request.setAttribute("message", "There is no registered hospital under that pincode.");
						rd = request.getRequestDispatcher("/jsp/report/hospitalReport.jsp");
					}else
						request.setAttribute("type", "report");
						request.setAttribute("hospitaldetails", hospital);
						rd = request.getRequestDispatcher("/jsp/report/hospitalReport.jsp");
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
			else if("searchHospitalByName".equals(action1)){
				String hospitalName = request.getParameter("hospitalName");
				try {
					Hospital hospital = hs.searchHospital(hospitalName);
					if(hospital == null){
						request.setAttribute("type", "failure_message");
						request.setAttribute("message", "There is no registered hospital by that name.");
						rd = request.getRequestDispatcher("/jsp/report/hospitalReport.jsp");
					}else{
						request.setAttribute("type", "report");
						request.setAttribute("hospitaldetails", hospital);
						rd = request.getRequestDispatcher("/jsp/report/hospitalReport.jsp");
					}
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		else if("modify_hospital".equals(action)){
			System.out.println("In modify_hospital action if-else block");
			
			//retrieving the value of submit button Update/Delete
			String submit = request.getParameter("submit");
			System.out.println("The submit button pressed is " + submit);
			
			if("Delete".equals(submit)){
				System.out.println("In delete submit if-else block");
				int hospitalId = Integer.parseInt(request.getParameter("hospitalId"));
				try {
					String reply = hs.deleteHospital(hospitalId);
					if("success".equals(reply)){
						request.setAttribute("type", "success_message");
						request.setAttribute("message", "The hospital details have been deleted successfully.");
						rd = request.getRequestDispatcher("/jsp/report/hospitalReport.jsp");
					}
					else{
						request.setAttribute("type", "failure_message");
						request.setAttribute("message", "The hospital details couldn't be deleted.");
						rd = request.getRequestDispatcher("/jsp/report/hospitalReport.jsp");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if("Update".equals(submit)){
				System.out.println("In update submit if-else block");
				
				//retrieving data from the Form
				int hospitalId = Integer.parseInt(request.getParameter("hospitalId"));
				String hospitalName = request.getParameter("hospitalName");
				String address = request.getParameter("address");
				String cityName = request.getParameter("cityName");
				String stateName = request.getParameter("stateName");
				String pincode = request.getParameter("pincode");
				String stdcode = request.getParameter("stdcode");
				String phNo = request.getParameter("phNo");
				
				//making a hospital bean
				Hospital hospital = new Hospital();
				hospital.setHospitalId(hospitalId);
				hospital.setHospitalName(hospitalName);
				hospital.setAddress(address);
				hospital.setCityName(cityName);
				hospital.setStateName(stateName);
				hospital.setPincode(pincode);
				hospital.setStdcode(stdcode);
				hospital.setPhNo(phNo);
				
				try {
					String reply = hs.updateHospital(hospital);
					if("success".equals(reply)){
						request.setAttribute("type", "success_message");
						request.setAttribute("message", "The hospital details have been updated successfully.");
						rd = request.getRequestDispatcher("/jsp/report/hospitalReport.jsp");
					}
					else{
						request.setAttribute("type", "failure_message");
						request.setAttribute("message", "The hospital details couldn't be updated.");
						rd = request.getRequestDispatcher("/jsp/report/hospitalReport.jsp");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		}
		
		System.out.println("Exiting doPost() in HospitalController Class");
		rd.forward(request, response);
	}
}
