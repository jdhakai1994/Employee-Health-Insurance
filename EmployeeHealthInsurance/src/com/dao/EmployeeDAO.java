package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.bean.Employee;
import com.util.DBConnection;

public class EmployeeDAO {
	
	private Connection connect = null;
	private PreparedStatement ps1 = null;
	private PreparedStatement ps2 = null;
	private ResultSet resultSet = null;
	private String reply = null;

	public String addEmployee(Employee employee) throws Exception {
		System.out.println("Entering addEmployee() in EmployeeDAO Class");
		
		connect = DBConnection.getConnection();
		
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
		
		ps1 = connect.prepareStatement("SELECT status FROM ehi.employee WHERE employeeId=?");
		ps1.setInt(1, employeeId);
		resultSet = ps1.executeQuery();
		resultSet.last();
		int rownum = resultSet.getRow();
		if(rownum == 1){
			int status = resultSet.getInt("status");
			if(status == 0){
				ps2 = connect.prepareStatement("UPDATE ehi.employee SET status=1 WHERE employeeId=?");
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
			+ "mobNo,altMobNo,premiumAmount,accountNo,bankName,ifscCode) "
			+ "VALUES (?,UPPER(?),?,?,?,?,?,?,?,?,UPPER(?),?)");
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
			ps2.executeUpdate();
			reply = "success";
		}
		else
			reply ="fail";
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting addEmployee() in EmployeeDAO Class");
		
		return reply;
	}

}
