package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Employee;
import com.util.DBConnection;

public class EmployeeDAO {
	
	private Connection connect = null;
	private PreparedStatement ps1 = null;
	private PreparedStatement ps2 = null;
	private ResultSet resultSet = null;
	private String reply = null;

	public String addEmployee(Employee employee) throws Exception {
		System.out.println("Entering addEmployee(Employee) in EmployeeDAO Class");
		
		connect = DBConnection.getConnection();
		
		//retrieving data from employee bean
		int employeeId = employee.getEmployeeId();
		String employeeName = employee.getEmployeeName();
		String gender = employee.getGender();
		String dob = employee.getDateOfBirth();
		String dateOfBirth = dob.substring(6, 10) + "-" + dob.substring(3, 5) + "-" + dob.substring(0, 2);
		String emailId = employee.getEmailId();
		String altEmailId = employee.getAltEmailId();
		String mobNo = employee.getMobNo();
		String altMobNo = employee.getAltMobNo();
		double premiumAmount = employee.getPremiumAmount();
		String accountNo = employee.getAccountNo();
		String bankName = employee.getBankName();
		String ifscCode = employee.getIfscCode();
		String username = employee.getUsername();
		
		ps1 = connect.prepareStatement("SELECT status FROM ehi.employee WHERE "
				+ "employeeId=?");
		ps1.setInt(1, employeeId);
		resultSet = ps1.executeQuery();
		resultSet.last();
		int rownum = resultSet.getRow();
		if(rownum == 1){
			int status = resultSet.getInt("status");
			if(status == 0){
				ps2 = connect.prepareStatement("UPDATE ehi.employee SET status=1 WHERE"
						+ " employeeId=?");
				ps2.setInt(1, employeeId);
				ps2.executeUpdate();
				reply = "success";
			}
			else if(status == 1){
				reply = "already exists";
			}
		}
		else if(rownum == 0){
			ps2 = connect.prepareStatement("INSERT INTO ehi.employee "
			+ "(employeeId,employeeName,dateOfBirth,gender,emailId,altEmailId,"
			+ "mobNo,altMobNo,premiumAmount,accountNo,bankName,ifscCode,username) "
			+ "VALUES (?,UPPER(?),?,?,?,?,?,?,?,?,UPPER(?),?,?)");
			ps2.setInt(1, employeeId);
			ps2.setString(2, employeeName);
			ps2.setString(3, dateOfBirth);
			ps2.setString(4, gender);
			ps2.setString(5, emailId);
			ps2.setString(6, altEmailId);
			ps2.setString(7, mobNo);
			ps2.setString(8, altMobNo);
			ps2.setDouble(9, premiumAmount);
			ps2.setString(10, accountNo);
			ps2.setString(11, bankName);
			ps2.setString(12, ifscCode);
			ps2.setString(13, username);
			ps2.executeUpdate();
			reply = "success";
		}
		else
			reply ="fail";
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting addEmployee(Employee) in EmployeeDAO Class");
		
		return reply;
	}

	public void updatePremiumAmount(String[] approvedHealthInsuranceId) throws SQLException {
		System.out.println("Entering updatePremiumAmount(String []) in EmployeeDAO Class");
		
		connect = DBConnection.getConnection();
		
		for (int i = 0;i <= approvedHealthInsuranceId.length-1;i++){
			ps1 = connect.prepareStatement("UPDATE ehi.employee JOIN policy as p ON "
				+ "employee.employeeId=p.employeeId SET "
				+ "premiumAmount=premiumAmount+0.02*totalSumInsured WHERE "
				+ "healthInsuranceId=?");
			ps1.setInt(1, Integer.parseInt(approvedHealthInsuranceId[i]));
			ps1.executeUpdate();
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting updatePremiumAmount(String []) in EmployeeDAO Class");
	}

	public Employee getEmployeeDetails(String username) throws SQLException {
		System.out.println("Entering getEmployeeDetails(String) in EmployeeDAO Class");
		
		connect = DBConnection.getConnection();
		
		Employee employee = new Employee();
		
		ps1 = connect.prepareStatement("SELECT employeeId,employeeName,mobNo,dateofBirth,"
				+ "altEmailId,emailId FROM ehi.employee WHERE username=?");
		ps1.setString(1, username);
		resultSet = ps1.executeQuery();
		while(resultSet.next()){
			int employeeId = resultSet.getInt("employeeId");
			String employeeName = resultSet.getString("employeeName");
			String mobNo = resultSet.getString("mobNo");
			String dateOfBirth = resultSet.getString("dateofBirth");
			String altEmailId = resultSet.getString("altEmailId");
			String emailId = resultSet.getString("emailId");
			
			employee.setEmployeeId(employeeId);
			employee.setEmployeeName(employeeName);
			employee.setMobNo(mobNo);
			employee.setDateOfBirth(dateOfBirth);
			employee.setEmailId(emailId);
			employee.setAltEmailId(altEmailId);
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting getEmployeeDetails(String) in EmployeeDAO Class");
		return employee;
	}

	public Boolean checkIsEnrolled(String username) throws SQLException {
		System.out.println("Entering checkIsEnrolled(String) in EmployeeDAO Class");
		
		connect = DBConnection.getConnection();
		
		Boolean isEnrolled = false;
		ps1 = connect.prepareStatement("SELECT count(*) FROM ehi.employee as e JOIN ehi.policy as p"
				+ " ON e.employeeId=p.employeeId WHERE p.dependentId=0 AND p.status=1 AND e.username=?");
		ps1.setString(1, username);
		resultSet = ps1.executeQuery();
		while(resultSet.next()){
			int count = resultSet.getInt(1);
			if(count == 0)
				isEnrolled = false;
			else
				isEnrolled = true;
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting checkIsEnrolled(String) in EmployeeDAO Class");
		return isEnrolled;
	}

}
