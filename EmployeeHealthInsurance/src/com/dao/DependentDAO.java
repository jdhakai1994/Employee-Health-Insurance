package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Dependent;
import com.util.DBConnection;

public class DependentDAO {
	
	private Connection connect = null;
	private PreparedStatement ps1 = null;
	private PreparedStatement ps2 = null;
	private ResultSet resultSet = null;
	private String reply = null;

	public String addDependent(Dependent dependent) throws Exception{
		System.out.println("Entering addDependent() in DependentDAO Class");
		
		connect = DBConnection.getConnection();
		
		int employeeId = dependent.getEmployeeId();
		String beneficiaryName = dependent.getBeneficiaryName();
		String gender = dependent.getGender();
		String dob = dependent.getDateOfBirth();
		String dateOfBirth = dob.substring(6, 10) + "-" + dob.substring(3, 5) + "-" + dob.substring(0, 2);
		String relation = dependent.getRelation();
		
		ps1 = connect.prepareStatement("SELECT status FROM ehi.dependent WHERE employeeId=? AND relation=?");
		ps1.setInt(1, employeeId);
		ps1.setString(2, relation);
		resultSet = ps1.executeQuery();
		System.out.println("Done");
		resultSet.last();
		int rownum = resultSet.getRow();
		if(rownum == 1){
			int status = resultSet.getInt("status");
			if(status == 0){
				ps2 = connect.prepareStatement("UPDATE ehi.dependent SET status=1 WHERE employeeId=? AND relation=?");
				ps2.setInt(1, employeeId);
				ps2.setString(2, relation);
				ps2.executeUpdate();
				System.out.println("Done");
				reply = "success";
			}
			else if(status == 1){
				reply = "already exists";
			}
		}
		else if(rownum == 0){
			ps2 = connect.prepareStatement("INSERT INTO ehi.dependent "
			+ "(employeeId,beneficiaryName,dateOfBirth,gender,relation)"
			+ "VALUES (?,UPPER(?),?,?,?)");
			ps2.setInt(1, employeeId);
			ps2.setString(2, beneficiaryName);
			ps2.setString(3, dateOfBirth);
			ps2.setString(4, gender);
			ps2.setString(5, relation);
			ps2.executeUpdate();
			reply = "success";
			System.out.println("Done");
		}
		else
			reply ="fail";
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting addDependent() in DependentDAO Class");
		
		return reply;
	}

	public int fetchDependentId(int employeeId, String relation) throws Exception {
		System.out.println("Entering fetchDependentId() in DependentDAO Class");
		
		connect = DBConnection.getConnection();
		
		ps1 = connect.prepareStatement("SELECT dependentId FROM ehi.dependent WHERE employeeId=? and relation=?");
		ps1.setInt(1, employeeId);
		ps1.setString(2, relation);
		resultSet = ps1.executeQuery();
		int id = 0;
		while(resultSet.next()){
			id = resultSet.getInt("dependentId");
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting fetchDependentId() in DependentDAO Class");
		
		return id;
	}

	
}
