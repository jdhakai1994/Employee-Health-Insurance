package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Hospital;
import com.util.DBConnection;

public class HospitalDAO {
	
	private Connection connect = null;
	private PreparedStatement ps1 = null;
	private PreparedStatement ps2 = null;
	private ResultSet resultSet = null;
	private String reply = null;

	public String addHospital(Hospital hospital) throws SQLException {
		
		System.out.println("Entering addHospital(Hospital) in HospitalDAO Class");
		
		connect = DBConnection.getConnection();
		
		//retrieving data from hospital bean
		String hospitalName = hospital.getHospitalName();
		String address = hospital.getAddress();
		String cityName = hospital.getCityName();
		String stateName = hospital.getStateName();
		String pincode = hospital.getPincode();
		String stdcode = hospital.getStdcode();
		String phNo = hospital.getPhNo();
		
		ps1 = connect.prepareStatement("SELECT status FROM ehi.hospital WHERE pincode=? "
				+ "and hospitalName=?");
		ps1.setString(1, pincode);
		ps1.setString(2, hospitalName);
		resultSet = ps1.executeQuery();
		resultSet.last();
		int rownum = resultSet.getRow();
		if(rownum == 1){
			int status = resultSet.getInt("status");
			if(status == 0){
				ps2 = connect.prepareStatement("UPDATE ehi.hospital SET status=1 WHERE"
						+ " pincode=? and hospitalName=?");
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
			+ "(hospitalName,address,city,state,pincode,stdcode,phoneNumber) "
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
		System.out.println("Exiting addHospital(Hospital) in HospitalDAO Class");
	
		return reply;
	}

	public int fetchHospitalId() throws SQLException {
		
		System.out.println("Entering fetchHospitalId() in HospitalDAO Class");
		
		connect = DBConnection.getConnection();
		
		int id = 0;
		ps1 = connect.prepareStatement("SELECT hospitalId FROM ehi.hospital ORDER BY hospitalId DESC LIMIT 1");
		resultSet = ps1.executeQuery();
		while(resultSet.next()){
			id = resultSet.getInt("hospitalId");
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting fetchHospitalId() in HospitalDAO Class");
		return id;
	}

	public Hospital searchHospital(int hospitalId) throws SQLException {
		
		System.out.println("Entering searchHospital(int) in HospitalDAO Class");
		
		connect = DBConnection.getConnection();
		
		Hospital hospital = null;
		ps1 = connect.prepareStatement("SELECT * FROM ehi.hospital WHERE hospitalId=? AND status=1");
		ps1.setInt(1, hospitalId);
		resultSet = ps1.executeQuery();
		if(resultSet.next()){
			//making a hospital bean 
			hospital = new Hospital();
			hospital.setHospitalId(resultSet.getInt("hospitalId"));
			hospital.setHospitalName(resultSet.getString("hospitalName"));
			hospital.setAddress(resultSet.getString("address"));
			hospital.setCityName(resultSet.getString("city"));
			hospital.setStateName(resultSet.getString("state"));
			hospital.setPincode(resultSet.getString("pincode"));
			hospital.setStdcode(resultSet.getString("stdcode"));
			hospital.setPhNo(resultSet.getString("phoneNumber"));

			return hospital;
		}
	
		DBConnection.closeConnection(connect);
		System.out.println("Exiting searchHospital(int) in HospitalDAO Class");
		
		return hospital;
	}
	

	public String deleteHospital(int hospitalId) throws SQLException {
		
		System.out.println("Entering deleteHospital(int) in HospitalDAO Class");
		
		connect = DBConnection.getConnection();
		
		ps1 = connect.prepareStatement("UPDATE ehi.hospital SET status=0 WHERE hospitalId=?");
		ps1.setInt(1, hospitalId);
		ps1.executeUpdate();
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting deleteHospital(int) in HospitalDAO Class");
		
		return "success";
		
	}

	public String updateHospital(Hospital hospital) throws SQLException {
		
		System.out.println("Entering updateHospital(Hospital) in HospitalDAO Class");
		
		connect = DBConnection.getConnection();
		
		//retrieving data from hospital bean
		int hospitalId = hospital.getHospitalId();
		String hospitalName = hospital.getHospitalName();
		String address = hospital.getAddress();
		String cityName = hospital.getCityName();
		String stateName = hospital.getStateName();
		String pincode = hospital.getPincode();
		String stdcode = hospital.getStdcode();
		String phNo = hospital.getPhNo();
		
		ps1 = connect.prepareStatement("UPDATE ehi.hospital SET hospitalName=?, "
				+ "address=?,city=?, state=?, pincode=?, stdcode=?, phoneNumber=? "
				+ "WHERE hospitalId=?");
		
		ps1.setString(1, hospitalName);
		ps1.setString(2, address);
		ps1.setString(3, cityName);
		ps1.setString(4, stateName);
		ps1.setString(5, pincode);
		ps1.setString(6, stdcode);
		ps1.setString(7, phNo);
		ps1.setInt(8, hospitalId);
		ps1.executeUpdate();
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting updateHospital(Hospital) in HospitalDAO Class");
		
		return "success";

	}

	public Hospital searchHospital(String input) throws SQLException {
		
		System.out.println("Entering searchHospital(input) in HospitalDAO Class");
		
		connect = DBConnection.getConnection();
		
		Hospital hospital = null;
		ps1 = connect.prepareStatement("SELECT * FROM ehi.hospital WHERE pincode=? OR "
				+ "hospitalName=? AND status=1");
		ps1.setString(1, input);
		ps1.setString(2, input);
		resultSet = ps1.executeQuery();
		if(resultSet.next()){
			//making a hospital bean
			hospital = new Hospital();
			hospital.setHospitalId(resultSet.getInt("hospitalId"));
			hospital.setHospitalName(resultSet.getString("hospitalName"));
			hospital.setAddress(resultSet.getString("address"));
			hospital.setCityName(resultSet.getString("city"));
			hospital.setStateName(resultSet.getString("state"));
			hospital.setPincode(resultSet.getString("pincode"));
			hospital.setStdcode(resultSet.getString("stdcode"));
			hospital.setPhNo(resultSet.getString("phoneNumber"));

		}
	
		DBConnection.closeConnection(connect);
		System.out.println("Exiting searchHospital(input) in HospitalDAO Class");
		
		return hospital;
	}
	
	public List<Hospital> searchHospital(String stateName, String cityName) throws SQLException {
		System.out.println("Entering searchHospital(String, String) in HospitalDAO Class");
		
		connect = DBConnection.getConnection();
		
		Hospital hospital = null;
		List<Hospital> hospitalList = new ArrayList<Hospital>();
		ps1 = connect.prepareStatement("SELECT * FROM ehi.hospital WHERE state=? OR "
				+ "city=? AND status=1");
		ps1.setString(1, stateName);
		ps1.setString(2, cityName);
		resultSet = ps1.executeQuery();
		while(resultSet.next()){
			//making a hospital bean
			hospital = new Hospital();
			hospital.setHospitalId(resultSet.getInt("hospitalId"));
			hospital.setHospitalName(resultSet.getString("hospitalName"));
			hospital.setAddress(resultSet.getString("address"));
			hospital.setCityName(resultSet.getString("city"));
			hospital.setStateName(resultSet.getString("state"));
			hospital.setPincode(resultSet.getString("pincode"));
			hospital.setStdcode(resultSet.getString("stdcode"));
			hospital.setPhNo(resultSet.getString("phoneNumber"));

			hospitalList.add(hospital);
		}
	
		DBConnection.closeConnection(connect);
		System.out.println("Exiting searchHospital(String, String) in HospitalDAO Class");
		
		return hospitalList;
	}

	public List<Integer> fetchHospitalIdList() throws SQLException {
		System.out.println("Entering fetchHospitalIdList() in HospitalDAO Class");
		
		connect = DBConnection.getConnection();
		
		List<Integer> hospitalIdList = new ArrayList<Integer>();
		int id = 0;
		ps1 = connect.prepareStatement("SELECT hospitalId FROM ehi.hospital WHERE status=1");
		resultSet = ps1.executeQuery();
		while(resultSet.next()){
			id = resultSet.getInt("hospitalId");
			hospitalIdList.add(id);
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting fetchHospitalId() in HospitalDAO Class");
		return hospitalIdList;
	}

	public List<String> getCityList(String state) throws SQLException {
		System.out.println("Entering getCityList() in HospitalDAO Class");
		
		connect = DBConnection.getConnection();
		
		List<String> cityList = new ArrayList<String>();
		String city;
		ps1 = connect.prepareStatement("SELECT DISTINCT city FROM ehi.hospital WHERE state=? AND status=1");
		ps1.setString(1, state);
		resultSet = ps1.executeQuery();
		while(resultSet.next()){
			city = resultSet.getString("city");
			cityList.add(city);
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting getCityList() in HospitalDAO Class");
		return cityList;
	}
}
