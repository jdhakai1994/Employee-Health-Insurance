package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Dependent;
import com.util.DBConnection;

public class DependentDAO {
	
	private Connection connect = null;
	private PreparedStatement ps1 = null;
	private PreparedStatement ps2 = null;
	private ResultSet resultSet = null;
	private String reply = null;

	public String addDependent(Dependent dependent) throws Exception{
		System.out.println("Entering addDependent(Dependent) in DependentDAO Class");
		
		connect = DBConnection.getConnection();
		
		//retrieving data from dependent bean
		int employeeId = dependent.getEmployeeId();
		String beneficiaryName = dependent.getBeneficiaryName();
		String gender = dependent.getGender();
		String dob = dependent.getDateOfBirth();
		String dateOfBirth = dob.substring(6, 10) + "-" + dob.substring(3, 5) + "-" + dob.substring(0, 2);
		String relation = dependent.getRelation();
		
		ps1 = connect.prepareStatement("SELECT status FROM ehi.dependent WHERE "
				+ "employeeId=? AND relation=?");
		ps1.setInt(1, employeeId);
		ps1.setString(2, relation);
		resultSet = ps1.executeQuery();
		resultSet.last();
		int rownum = resultSet.getRow();
		if(rownum == 1){
			int status = resultSet.getInt("status");
			if(status == 0){
				ps2 = connect.prepareStatement("UPDATE ehi.dependent SET status=1 WHERE"
						+ " employeeId=? AND relation=?");
				ps2.setInt(1, employeeId);
				ps2.setString(2, relation);
				ps2.executeUpdate();
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
		}
		else
			reply ="fail";
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting addDependent(Dependent) in DependentDAO Class");
		
		return reply;
	}

	public int fetchDependentId(int employeeId, String relation) throws Exception {
		System.out.println("Entering fetchDependentId(int,String) in DependentDAO Class");
		
		connect = DBConnection.getConnection();
		
		ps1 = connect.prepareStatement("SELECT dependentId FROM ehi.dependent "
				+ "WHERE employeeId=? AND relation=? AND status=1");
		ps1.setInt(1, employeeId);
		ps1.setString(2, relation);
		resultSet = ps1.executeQuery();
		int id = 0;
		while(resultSet.next()){
			id = resultSet.getInt("dependentId");
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting fetchDependentId(int,String) in DependentDAO Class");
		
		return id;
	}

	public List<Dependent> fetchDependentDetails(int employeeId) throws Exception {
		System.out.println("Entering fetchDependentDetails(int) in DependentDAO Class");
		
		connect = DBConnection.getConnection();
		
		ps1 = connect.prepareStatement("SELECT * FROM ehi.dependent WHERE employeeId=? AND status=1");
		ps1.setInt(1, employeeId);
		resultSet = ps1.executeQuery();
		List<Dependent> dependentList = new ArrayList<Dependent>();
		while(resultSet.next()){
			int dependentId = resultSet.getInt("dependentId");
			String beneficiaryName = resultSet.getString("beneficiaryName");
			String relation = resultSet.getString("relation");
			
			Dependent dependent = new Dependent();
			dependent.setDependentId(dependentId);
			dependent.setBeneficiaryName(beneficiaryName);
			dependent.setRelation(relation);
			
			dependentList.add(dependent);
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting fetchDependentDetails(int) in DependentDAO Class");
		
		return dependentList;		
	}

	public String fetchRelation(int employeeId, String name) throws Exception {
		System.out.println("Entering fetchRelation(int,String) in DependentDAO Class");
		
		connect = DBConnection.getConnection();
		
		String relation = "";
		
		ps1 = connect.prepareStatement("SELECT relation FROM ehi.dependent WHERE employeeId=? "
				+ "AND beneficiaryName=? AND status=1");
		ps1.setInt(1, employeeId);
		ps1.setString(2, name);
		resultSet = ps1.executeQuery();
		resultSet.last();
		int noOfRows = resultSet.getRow();
		
		if(noOfRows == 0)
			relation = "Self";
		else
			relation = resultSet.getString("relation");
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting fetchRelation(int,String) in DependentDAO Class");
		
		return relation;
	}

	public int deleteDependent(int employeeId, String relation) throws SQLException {
		System.out.println("Entering deleteDependent(int, String) in DependentDAO Class");
		
		connect = DBConnection.getConnection();
		
		int dependentId = 0;
		ps1 = connect.prepareStatement("UPDATE ehi.dependent SET status=0 WHERE"
				+ " employeeId=? AND relation=? AND status=1");
		ps1.setInt(1, employeeId);
		ps1.setString(2, relation);
		int rowcount = ps1.executeUpdate();
		if(rowcount == 0){
			dependentId = -1;
		}
		else{
			ps2 = connect.prepareStatement("SELECT dependentId FROM ehi.dependent WHERE"
					+ " employeeId=? AND relation=? AND status=0");
			ps2.setInt(1, employeeId);
			ps2.setString(2, relation);
			resultSet = ps2.executeQuery();
			while(resultSet.next()){
				dependentId = resultSet.getInt("dependentId");
			}			
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting deleteDependent(int,String) in DependentDAO Class");
		return dependentId;
	}
}