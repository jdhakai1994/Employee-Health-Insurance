package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Hospital;
import com.util.DBConnection;

public class HospitalDAO {
	
	private Connection connect = null;
	private PreparedStatement ps1 = null;
	private PreparedStatement ps2 = null;
	private ResultSet resultSet = null;
	private String reply = null;

	public String addHospital(Hospital hospital) throws Exception {
		
		System.out.println("In addHospital() in HospitalDAO Class");
		
		connect = DBConnection.getConnection();
		
		String hospitalName = hospital.getHospitalName();
		String address = hospital.getAddress();
		String cityName = hospital.getCityName();
		String stateName = hospital.getStateName();
		String pincode = hospital.getPincode();
		String stdcode = hospital.getStdcode();
		String phNo = hospital.getPhNo();
		
		ps1 = connect.prepareStatement("SELECT status FROM ehi.hospital WHERE pincode=? and hospital_name=?");
		ps1.setString(1, pincode);
		ps1.setString(2, hospitalName);
		resultSet = ps1.executeQuery();
		resultSet.last();
		int rownum = resultSet.getRow();
		if(rownum == 1){
			int status = resultSet.getInt("status");
			if(status == 0){
				ps2 = connect.prepareStatement("UPDATE ehi.hospital SET status=1 WHERE pincode=? and hospital_name=?");
				ps2.setString(1, pincode);
				ps2.setString(2, hospitalName);
				ps2.executeUpdate();
				reply = "success";
			}
			else if(status == 1){
				reply = "already exists";
			}
		}
		else if(rownum == 0){
			ps2 = connect.prepareStatement("INSERT INTO ehi.hospital "
			+ "(hospital_name,address,city,state,pincode,stdcode,phone_number) "
			+ "VALUES (UPPER(?),UPPER(?),UPPER(?),UPPER(?),?,?,?)");
			ps2.setString(1, hospitalName);
			ps2.setString(2, address);
			ps2.setString(3, cityName);
			ps2.setString(4, stateName);
			ps2.setString(5, pincode);
			ps2.setString(6, stdcode);
			ps2.setString(7, phNo);
			ps2.executeUpdate();
			reply = "success";
		}
		else
			reply ="fail";
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting addHospital() in HospitalDAO Class");
	
		return reply;
	}

	public int fetchHospitalId() throws Exception {
		
		System.out.println("In fetchHospitalId() in HospitalDAO Class");
		
		connect = DBConnection.getConnection();
		
		int id = 0;
		ps1 = connect.prepareStatement("SELECT hid FROM ehi.hospital ORDER BY hid DESC LIMIT 1");
		resultSet = ps1.executeQuery();
		while(resultSet.next()){
			id = resultSet.getInt("hid");
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting fetchHospitalId() in HospitalDAO Class");
		return id;
	}

}
