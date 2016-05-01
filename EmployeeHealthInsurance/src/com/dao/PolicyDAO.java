package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.Policy;
import com.util.DBConnection;

public class PolicyDAO {
	
	private Connection connect = null;
	private PreparedStatement ps1 = null;
	private PreparedStatement ps2 = null;
	private ResultSet resultSet = null;
	private String reply = null;

	public String addPolicy(Policy policy) throws Exception{
		System.out.println("Entering addPolicy() in PolicyDAO Class");
		
		connect = DBConnection.getConnection();
		
		int employeeId = policy.getEmployeeId();
		int dependentId = policy.getDependentId();
		String date = policy.getStartDate();
		String startDate = date.substring(6, 10) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2);
		int policyPeriod = policy.getPolicyPeriod();
		double totalSumInsured = policy.getTotalSumInsured();
		
		ps1 = connect.prepareStatement("INSERT INTO ehi.policy "
				+ "(employeeId,dependentId,startDate,policyPeriod,totalSumInsured)"
				+ "VALUES (?,?,?,?,?)");
		ps1.setInt(1, employeeId);
		ps1.setInt(2, dependentId);
		ps1.setString(3, startDate);
		ps1.setInt(4, policyPeriod);
		ps1.setDouble(5, totalSumInsured);
		ps1.executeUpdate();
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting addPolicy() in PolicyDAO Class");
		
		return "success";
	}

	public int fetchPolicyId(int employeeId) throws Exception{
		System.out.println("Entering fetchPolicyId() in PolicyDAO Class");
		
		connect = DBConnection.getConnection();
		
		ps1 = connect.prepareStatement("SELECT healthInsuranceId FROM ehi.policy WHERE employeeId=? and dependentId=0");
		ps1.setInt(1, employeeId);
		resultSet = ps1.executeQuery();
		int id = 0;
		while(resultSet.next()){
			id = resultSet.getInt("healthInsuranceId");
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting fetchPolicyId() in PolicyDAO Class");
		
		return id;
	}
	
	public int fetchPolicyId(int employeeId, int dependentId) throws Exception{
		System.out.println("Entering fetchPolicyId(int, int) in PolicyDAO Class");
		
		connect = DBConnection.getConnection();
		
		ps1 = connect.prepareStatement("SELECT healthInsuranceId FROM ehi.policy WHERE employeeId=? and dependentId=?");
		ps1.setInt(1, employeeId);
		ps1.setInt(2, dependentId);
		resultSet = ps1.executeQuery();
		int id = 0;
		while(resultSet.next()){
			id = resultSet.getInt("healthInsuranceId");
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting fetchPolicyId(int, int) in PolicyDAO Class");
		
		return id;
	}

}
