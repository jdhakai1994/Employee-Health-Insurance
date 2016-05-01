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
		
		//Retrieving action from URL
		String action = request.getParameter("action");
		System.out.println("The action retreived is " + action);
		
		//If/else block for action
		if("getAddHospitalForm".equals(action)){
			
			System.out.println("In add_hospital if/else action block");
			
			request.setAttribute("stateList", stateList);
			
			//To fetch the hospitalId
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
			System.out.println("In modify_hospital if/else action block");
			rd = request.getRequestDispatcher("/jsp/forms/searchHospitalForm1.jsp");
		}
		else if("getSearchHospitalForm".equals(action)){
			System.out.println("In search_hospital if/else action block");
			request.setAttribute("stateList", stateList);
			rd = request.getRequestDispatcher("/jsp/forms/searchHospitalForm.jsp");
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
		
		String action = request.getParameter("action");
		System.out.println("The action retreived is " + action);
		
		HospitalService hs = new HospitalService();
				
		if("add_hospital".equals(action)){
			System.out.println("In add_hospital if/else action block");
			
			//Retrieving data
			String hospitalName = request.getParameter("hospitalName");
			String address = request.getParameter("address");
			String cityName = request.getParameter("cityName");
			String stateName = request.getParameter("stateName");
			String pincode = request.getParameter("pincode");
			String stdcode = request.getParameter("stdcode");
			String phNo = request.getParameter("phNo");
			
//			System.out.println(hospitalName);
//			System.out.println(address);
//			System.out.println(cityName);
//			System.out.println(stateName);
//			System.out.println(pincode);
//			System.out.println(stdcode);
//			System.out.println(phNo);
			
			//Making a bean
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
			System.out.println("In search_hospital if/else action block");
			String action1 = request.getParameter("action1");
			System.out.println("The action1 retreived is " + action1);
			
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
			System.out.println("In modify_hospital if/else action block");
			String submit = request.getParameter("submit");
			System.out.println("The submit button pressed is " + submit);
			
			if("Delete".equals(submit)){
				System.out.println("In delete if/else action block");
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
				System.out.println("In update if/else action block");
				int hospitalId = Integer.parseInt(request.getParameter("hospitalId"));
				String hospitalName = request.getParameter("hospitalName");
				String address = request.getParameter("address");
				String cityName = request.getParameter("cityName");
				String stateName = request.getParameter("stateName");
				String pincode = request.getParameter("pincode");
				String stdcode = request.getParameter("stdcode");
				String phNo = request.getParameter("phNo");
				
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
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/result.jsp");
		rd.forward(request, response);
				
	}
}
