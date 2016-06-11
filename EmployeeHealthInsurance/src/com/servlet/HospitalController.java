package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Hospital;
import com.services.HospitalService;

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
        // TODO Auto-generated constructor stub
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
		
		ArrayList<String> stateList = new ArrayList<String>(Arrays.asList(state));
		
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
		
			//retrieving action from URL
			String action = request.getParameter("action");
			System.out.println("The action retreived is " + action);
		
			//if-else code block for action
			if("getAddHospitalForm".equals(action)){
			
				System.out.println("In add_hospital action if-else block");
			
				request.setAttribute("stateList", stateList);
			
				//to fetch the hospitalId
				HospitalService hs = new HospitalService();
				try {
					int hospitalId = hs.fetchHospitalId();
					request.setAttribute("hospitalId", hospitalId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				rd = request.getRequestDispatcher("/jsp/forms/addHospitalForm.jsp");
			}
			else if("getModifyHospitalForm".equals(action)){
				System.out.println("In modify_hospital action if-else block");
				rd = request.getRequestDispatcher("/jsp/forms/searchHospitalForm1.jsp");
			}
			else if("getSearchHospitalForm".equals(action)){
				System.out.println("In search_hospital action if-else block");
				request.setAttribute("stateList", stateList);
				rd = request.getRequestDispatcher("/jsp/forms/searchHospitalForm.jsp");
			}
		}
		
		System.out.println("Exiting doGet() in HospitalController Class");				
		rd.forward(request, response);
		
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
				if("success".equals(reply))
					request.setAttribute("message", "The hospital details have been successfully added");
				else if("fail".equals(reply))
					request.setAttribute("message", "The hospital details couldn't be added");
				else if("already exists".equals(reply))
					request.setAttribute("message", "The hospital details already exists");
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
				System.out.println(hospitalId);
				try {
					Hospital hospital = hs.searchHospital(hospitalId);
					if(hospital == null){
						request.setAttribute("message", "The hospital details doesn't exist");
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/forms/searchHospitalForm1.jsp");
						rd.forward(request, response);
					}
					else{
						request.setAttribute("hospitaldetails", hospital);
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/forms/modifyHospitalForm.jsp");
						rd.forward(request, response);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if("searchHospitalByPin".equals(action1)){
				String pincode = request.getParameter("pincode");
				System.out.println(pincode);
				try {
					Hospital hospital = hs.searchHospital(pincode);
					if(hospital == null)
						request.setAttribute("message", "The hospital details doesn't exist");
					else
						request.setAttribute("hospitaldetails", hospital);
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
			else if("searchHospitalByName".equals(action1)){
				String hospitalName = request.getParameter("hospitalName");
				System.out.println(hospitalName);
				try {
					Hospital hospital = hs.searchHospital(hospitalName);
					if(hospital == null)
						request.setAttribute("message", "The hospital details doesn't exist");
					else
						request.setAttribute("hospitaldetails", hospital);
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
				System.out.println(hospitalId);
				try {
					String reply = hs.deleteHospital(hospitalId);
					if("success".equals(reply)){
						request.setAttribute("message", "The hospital details have been deleted");
					}
					else
						request.setAttribute("message", "The hospital details couldn't be deleted");
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
						request.setAttribute("message", "The hospital details have been updated");
					}
					else
						request.setAttribute("message", "The hospital details couldn't be updated");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		}
		
		System.out.println("Exiting doPost() in HospitalController Class");
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/result.jsp");
		rd.forward(request, response);	
	}
}
