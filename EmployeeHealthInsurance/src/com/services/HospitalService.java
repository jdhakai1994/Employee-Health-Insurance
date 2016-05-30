package com.services;

import com.bean.Hospital;
import com.dao.HospitalDAO;
import com.dao.LoginDAO;

public class HospitalService {
	
	public String addHospital(Hospital hospital) throws Exception{
		System.out.println("Entering addHospital(Hospital) in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		String reply = hdao.addHospital(hospital);
		System.out.println("Exiting addHospital(Hospital) in HospitalService Class");
		return reply;
	}

	public int fetchHospitalId() throws Exception {
		System.out.println("Entering fetchHospitalId() in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		int hospitalId = hdao.fetchHospitalId();
		System.out.println("Exiting fetchHospitalId() in HospitalService Class");
		return hospitalId;
	}

	public Hospital searchHospital(int hospitalId) throws Exception {
		System.out.println("Entering searchHospital(int) in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		Hospital hospital = hdao.searchHospital(hospitalId);
		System.out.println("Exiting searchHospital(int) in HospitalService Class");
		return hospital;
	}
	
	public Hospital searchHospital(String input) throws Exception {
		System.out.println("Entering searchHospital(String) in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		Hospital hospital = hdao.searchHospital(input);
		System.out.println("Exiting searchHospital(String) in HospitalService Class");
		return hospital;
	}

	public String deleteHospital(int hospitalId) throws Exception {
		System.out.println("Entering deleteHospital(int) in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		String reply = hdao.deleteHospital(hospitalId);
		System.out.println("Exiting deleteHospital(int) in HospitalService Class");
		return reply;
	}

	public String updateHospital(Hospital hospital) throws Exception {
		System.out.println("Entering updateHospital(Hospital) in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		String reply = hdao.updateHospital(hospital);
		System.out.println("Exiting updateHospital(Hospital) in HospitalService Class");
		return reply;
	}

}
