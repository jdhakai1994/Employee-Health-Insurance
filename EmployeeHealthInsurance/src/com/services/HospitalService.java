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

}
