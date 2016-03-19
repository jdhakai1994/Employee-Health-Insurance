package com.services;

import com.bean.Hospital;
import com.dao.HospitalDAO;
import com.dao.LoginDAO;

public class HospitalService {
	
	public String addHospital(Hospital hospital) throws Exception{
		System.out.println("Entering addHospital() in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		String reply = hdao.addHospital(hospital);
		System.out.println("Exiting addHospital() in HospitalService Class");
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
		System.out.println("Entering searchHospital() in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		Hospital hospital = hdao.searchHospital(hospitalId);
		System.out.println("Exiting searchHospital() in HospitalService Class");
		return hospital;
	}

	public String deleteHospital(int hospitalId) throws Exception {
		System.out.println("Entering deleteHospital() in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		String reply = hdao.deleteHospital(hospitalId);
		System.out.println("Exiting deleteHospital() in HospitalService Class");
		return reply;
	}

	public String updateHospital(Hospital hospital) throws Exception {
		System.out.println("Entering updateHospital() in HospitalService Class");
		HospitalDAO hdao = new HospitalDAO();
		String reply = hdao.updateHospital(hospital);
		System.out.println("Exiting updateHospital() in HospitalService Class");
		return reply;
	}

}
