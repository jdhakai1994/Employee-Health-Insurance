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
		
		RequestDispatcher rd = null;
		
		//Retrieving action from URL
		String action = request.getParameter("action");
		System.out.println("The action retreived is " + action);
		
		//If/else block for action
		if("add_hospital".equals(action)){
			
			System.out.println("In add_hospital if/else action block");
			String state[] = {"Andaman and Nicobar Islands","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chandigarh",
					"Chhattisgarh","Dadra and Nagar Haveli","Daman and Diu","Delhi","Goa","Gujarat","Haryana","Himachal Pradesh",
					"Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Lakshadweep","Madhya Pradesh","Maharashtra","Manipur",
					"Meghalaya","Mizoram","Nagaland","Orissa","Pondicherry","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana",
					"Tripura","Uttaranchal","Uttar Pradesh","West Bengal"};
			
			ArrayList<String> stateList = new ArrayList<String>(Arrays.asList(state));
			request.setAttribute("stateList", stateList);
			
			//To fetch the hospitalId
			HospitalService hs = new HospitalService();
			try {
				int hospitalId = hs.fetchHospitalId();
				request.setAttribute("hospitalId", hospitalId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			rd = request.getRequestDispatcher("/jsp/addhospital.jsp");
		}
		else if("modify_hospital".equals(action)){
			System.out.println("In modify_hospital if/else action block");
			rd = request.getRequestDispatcher("/jsp/modifyhospital.jsp");
		}
		else if("search_hospital".equals(action)){
			System.out.println("In search_hospital if/else action block");
			rd = request.getRequestDispatcher("/jsp/searchhospital.jsp");
		}
				
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
		
		//Retrieving data
		String hospitalName = request.getParameter("hospitalName");
		String address = request.getParameter("address");
		String cityName = request.getParameter("cityName");
		String stateName = request.getParameter("stateName");
		String pincode = request.getParameter("pincode");
		String stdcode = request.getParameter("stdcode");
		String phNo = request.getParameter("phNo");
		
//		System.out.println(hospitalName);
//		System.out.println(address);
//		System.out.println(cityName);
//		System.out.println(stateName);
//		System.out.println(pincode);
//		System.out.println(stdcode);
//		System.out.println(phNo);
		
		//Making a bean
		Hospital hospital = new Hospital();
		hospital.setHospitalName(hospitalName);
		hospital.setAddress(address);
		hospital.setCityName(cityName);
		hospital.setStateName(stateName);
		hospital.setPincode(pincode);
		hospital.setStdcode(stdcode);
		hospital.setPhNo(phNo);
		
		HospitalService hs = new HospitalService();
		String reply = null;
		
		if("add_hospital".equals(action)){
			System.out.println("In add_hospital if/else action block");
			try {
				reply = hs.addHospital(hospital);
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
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/success.jsp");
		rd.forward(request, response);
		System.out.println(reply);
		
		
	}
}
