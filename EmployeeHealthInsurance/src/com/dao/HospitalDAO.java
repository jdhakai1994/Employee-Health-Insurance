package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.bean.Hospital;
import com.bean.ValueAddedServices;
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
		
		/* check if an entry with same hospital name and pincode already exist (may be previously deleted)
		 * case 1 - if it exists updating the status to 1
		 * case 2 - if it doesn't exist inserting a new row
		 */
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
		// find the last entered hospitalId
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
		// get details of an active hospital with a particular hospital id
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
		// deleting (updating the status to 0) the hospital details with a particular hospitalId
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

		// updating the hospital details of a particular hospitalId		
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

		// get hospital details corresponding to a particular pincode or hospital name
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
		// get hospital details list corresponding to a particular state and city
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
		// get list of hospitalIDs 
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
		// get list of cities corresponding to a state
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

	public List<String> getHospitalList(String state, String city) throws SQLException {
		System.out.println("Entering getHospitalList(String, String) in HospitalDAO Class");
		
		connect = DBConnection.getConnection();
		
		List<String> hospitalList = new ArrayList<String>();
		String hospital;
		// get list of hospital names corresponding to a state and city
		ps1 = connect.prepareStatement("SELECT hospitalName FROM ehi.hospital WHERE state=? AND city=? AND status=1");
		ps1.setString(1, state);
		ps1.setString(2, city);
		resultSet = ps1.executeQuery();
		while(resultSet.next()){
			hospital = resultSet.getString("hospitalName");
			hospitalList.add(hospital);
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting getHospitalList(String, String) in HospitalDAO Class");
		return hospitalList;
	}

	public int addCheckUpRequest(ValueAddedServices vas) throws SQLException {
		System.out.println("Entering getHospitalList(String, String) in HospitalDAO Class");
		
		connect = DBConnection.getConnection();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int checkUpId = 0;
		
		//retrieving data from ValueAddedServices bean
		int employeeId = vas.getEmployeeId();
		String employeeName = vas.getEmployeeName();
		String mobNo = vas.getMobNo();
		String emailId = vas.getEmailId();
		String beneficiaryName = vas.getBeneficiaryName();
		int healthInsuranceId = vas.getHealthInsuranceId();
		String gender = vas.getGender();
		int age = vas.getAge();
		String hospitalName = vas.getHospitalName();
		String date = vas.getAppointmentDate();
		String appointmentDate = date.substring(6, 10) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2);
		/* get last approved appointment date for a personnel
		 * case 1 - if it exists compare if it is more than 3 months and then enter a new row else ignore
		 * case 2 - if it doesn't exist enter a new row
		 */
		ps1 = connect.prepareStatement("SELECT appointmentDate FROM ehi.value_added_services WHERE healthInsuranceId=? AND status=1"
				+ " ORDER BY appointmentDate DESC LIMIT 1");
		ps1.setInt(1, healthInsuranceId);
		resultSet = ps1.executeQuery();
		if(resultSet.next()){
			String lastAppointmentDate = resultSet.getString("appointmentDate");
			Date date1 = null;
			try {
				date1 = format.parse(lastAppointmentDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date date2 = null;
			try {
				date2 = format.parse(appointmentDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int diffInDays = (int) ((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
			if(diffInDays > 90){
				ps1 = connect.prepareStatement("INSERT INTO ehi.value_added_services (employeeId,employeeName,mobNo,emailId,"
						+ "beneficiaryName,healthInsuranceId,gender,age,hospitalName,appointmentDate) VALUES "
						+ "(?,?,?,?,?,?,?,?,?,?)");
				ps1.setInt(1, employeeId);
				ps1.setString(2, employeeName);
				ps1.setString(3, mobNo);
				ps1.setString(4, emailId);
				ps1.setString(5, beneficiaryName);
				ps1.setInt(6, healthInsuranceId);
				ps1.setString(7, gender);
				ps1.setInt(8, age);
				ps1.setString(9, hospitalName);
				ps1.setString(10, appointmentDate);
				int result = ps1.executeUpdate();
				if(result != 0){
					ps2 = connect.prepareStatement("SELECT checkUpId FROM ehi.value_added_services ORDER BY "
							+ "checkUpId DESC LIMIT 1");
					resultSet = ps2.executeQuery();
					while(resultSet.next()){
						checkUpId = resultSet.getInt("checkUpId");
					}
				}
			}
			else
				checkUpId = -1;
		}
		else{
			ps1 = connect.prepareStatement("INSERT INTO ehi.value_added_services (employeeId,employeeName,mobNo,emailId,"
					+ "beneficiaryName,healthInsuranceId,gender,age,hospitalName,appointmentDate) VALUES "
					+ "(?,?,?,?,?,?,?,?,?,?)");
			ps1.setInt(1, employeeId);
			ps1.setString(2, employeeName);
			ps1.setString(3, mobNo);
			ps1.setString(4, emailId);
			ps1.setString(5, beneficiaryName);
			ps1.setInt(6, healthInsuranceId);
			ps1.setString(7, gender);
			ps1.setInt(8, age);
			ps1.setString(9, hospitalName);
			ps1.setString(10, appointmentDate);
			int result = ps1.executeUpdate();
			if(result != 0){
				ps2 = connect.prepareStatement("SELECT checkUpId FROM ehi.value_added_services ORDER BY "
						+ "checkUpId DESC LIMIT 1");
				resultSet = ps2.executeQuery();
				while(resultSet.next()){
					checkUpId = resultSet.getInt("checkUpId");
				}
			}
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting getHospitalList(String, String) in HospitalDAO Class");
		return checkUpId;
	}

	public List<ValueAddedServices> getUnapprovedAppointmentList() throws SQLException {
		System.out.println("Entering getUnapprovedAppointmentList() in HospitalDAO Class");
		
		connect = DBConnection.getConnection();
		
		List<ValueAddedServices> unapprovedAppointmentList = new ArrayList<ValueAddedServices>();

		// get the details of unapproved appointment requests
		ps1 = connect.prepareStatement("SELECT * FROM ehi.value_added_services WHERE status=0");
		resultSet = ps1.executeQuery();
		while(resultSet.next()){
			int checkUpId = resultSet.getInt("checkUpId");
			int healthInsuranceId = resultSet.getInt("healthInsuranceId");
			String mobNo = resultSet.getString("mobNo");
			String beneficiaryName = resultSet.getString("beneficiaryName");
			String gender = resultSet.getString("gender");
			int age = resultSet.getInt("age");
			String hospitalName = resultSet.getString("hospitalName");
			String date = resultSet.getString("appointmentDate");
			String appointmentDate = date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
			
			//making a Value Added Services bean
			ValueAddedServices vas = new ValueAddedServices();
			vas.setCheckUpId(checkUpId);
			vas.setMobNo(mobNo);
			vas.setBeneficiaryName(beneficiaryName);
			vas.setHealthInsuranceId(healthInsuranceId);
			vas.setGender(gender);
			vas.setAge(age);
			vas.setHospitalName(hospitalName);
			vas.setAppointmentDate(appointmentDate);
			
			unapprovedAppointmentList.add(vas);
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting getUnapprovedAppointmentList() in HospitalDAO Class");
		return unapprovedAppointmentList;
	}

	public int approveAppointment(String[] approvedClaimNo, String[] rejectedClaimNo) throws SQLException {
		System.out.println("Entering approveAppointment(String[], String[]) in ClaimDAO Class");
		
		connect = DBConnection.getConnection();
		
		int count1 = 0;
		int count2 = 0;
		
		//converting the array to a comma separated string to be use in IN statement
		if(approvedClaimNo != null){
			String approvedClaimNo1 = Arrays.toString(approvedClaimNo);
			approvedClaimNo1 = approvedClaimNo1.substring(1, approvedClaimNo1.length() - 1);
		
			//updating table with approved claims
			ps1 = connect.prepareStatement("UPDATE ehi.value_added_services SET status=1 WHERE "
				+ "checkUpId IN ("+approvedClaimNo1+")");
			count1 = ps1.executeUpdate();
		}
		
		//converting the array to a comma separated string to be use in IN statement
		if(rejectedClaimNo != null){
			String rejectedClaimNo1 = Arrays.toString(rejectedClaimNo);
			rejectedClaimNo1 = rejectedClaimNo1.substring(1, rejectedClaimNo1.length() - 1);
		
			//updating table with rejected claims
			ps1 = connect.prepareStatement("UPDATE ehi.value_added_services SET status=2 WHERE "
				+ "checkUpId IN ("+rejectedClaimNo1+")");
			count2 = ps1.executeUpdate();
		}
		
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting approveAppointment(String[], String[]) in ClaimDAO Class");
		
		return count1+count2;
	}
}
