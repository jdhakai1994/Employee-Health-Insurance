package com.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Hospital;
import com.bean.ValueAddedServices;
import com.dao.HospitalDAO;

public class HospitalService {
	
	public String addHospital(Hospital hospital) throws SQLException{
		System.out.println("Entering addHospital(Hospital) in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		String reply = hdao.addHospital(hospital);
		System.out.println("Exiting addHospital(Hospital) in HospitalService Class");
		return reply;
	}

	public int fetchHospitalId() throws SQLException {
		System.out.println("Entering fetchHospitalId() in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		int hospitalId = hdao.fetchHospitalId();
		System.out.println("Exiting fetchHospitalId() in HospitalService Class");
		return hospitalId;
	}

	public Hospital searchHospital(int hospitalId) throws SQLException {
		System.out.println("Entering searchHospital(int) in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		Hospital hospital = hdao.searchHospital(hospitalId);
		System.out.println("Exiting searchHospital(int) in HospitalService Class");
		return hospital;
	}
	
	public Hospital searchHospital(String input) throws SQLException {
		System.out.println("Entering searchHospital(String) in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		Hospital hospital = hdao.searchHospital(input);
		System.out.println("Exiting searchHospital(String) in HospitalService Class");
		return hospital;
	}
	
	public List<Hospital> searchHospital(String stateName, String cityName) throws SQLException {
		System.out.println("Entering searchHospital(String, String) in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		List<Hospital> hospitalList = new ArrayList<Hospital>();
		hospitalList = hdao.searchHospital(stateName, cityName);
		System.out.println("Exiting searchHospital(String, String) in HospitalService Class");
		return hospitalList;
	}

	public String deleteHospital(int hospitalId) throws SQLException {
		System.out.println("Entering deleteHospital(int) in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		String reply = hdao.deleteHospital(hospitalId);
		System.out.println("Exiting deleteHospital(int) in HospitalService Class");
		return reply;
	}

	public String updateHospital(Hospital hospital) throws SQLException {
		System.out.println("Entering updateHospital(Hospital) in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		String reply = hdao.updateHospital(hospital);
		System.out.println("Exiting updateHospital(Hospital) in HospitalService Class");
		return reply;
	}

	public List<Integer> fetchHospitalIdList() throws SQLException{
		System.out.println("Entering fetchHospitalIdList() in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		List<Integer> hospitalIdList = new ArrayList<Integer>();
		hospitalIdList = hdao.fetchHospitalIdList();
		System.out.println("Exiting fetchHospitalIdList() in HospitalService Class");
		return hospitalIdList;
	}

	public List<String> getCityList(String state) throws SQLException {
		System.out.println("Entering getCityList(String) in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		List<String> cityList = new ArrayList<String>();
		cityList = hdao.getCityList(state);
		System.out.println("Exiting getCityList(String) in HospitalService Class");
		return cityList;
	}

	public List<String> getHospitalList(String state, String city) throws SQLException {
		System.out.println("Entering getHospitalList(String, String) in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		List<String> hospitalList = new ArrayList<String>();
		hospitalList = hdao.getHospitalList(state, city);
		System.out.println("Exiting getCityList(String, String) in HospitalService Class");
		return hospitalList;
	}

	public int addCheckUpRequest(ValueAddedServices vas) throws SQLException {
		System.out.println("Entering addCheckUpRequest(ValueAddedServices) in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		int checkUpId = hdao.addCheckUpRequest(vas);
		System.out.println("Exiting addCheckUpRequest(ValueAddedServices) in HospitalService Class");
		return checkUpId;
	}

	public List<ValueAddedServices> getUnapprovedAppointmentList() throws SQLException {
		System.out.println("Entering getUnapprovedAppointmentList() in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		List<ValueAddedServices> unapprovedAppointmentList = new ArrayList<ValueAddedServices>();
		unapprovedAppointmentList = hdao.getUnapprovedAppointmentList();
		System.out.println("Exiting getUnapprovedAppointmentList() in HospitalService Class");
		return unapprovedAppointmentList;
	}

	public int approveAppointment(String[] approvedClaimNo, String[] rejectedClaimNo) throws SQLException {
		System.out.println("Entering approveAppointment(String[], String[]) in ClaimsService Class");
		HospitalDAO hdao = new HospitalDAO();
		int count = hdao.approveAppointment(approvedClaimNo, rejectedClaimNo);
		System.out.println("Exiting approveAppointment(String[], String[]) in ClaimsService Class");
		return count;
	}

	

}
