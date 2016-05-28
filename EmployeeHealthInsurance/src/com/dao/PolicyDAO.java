package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.bean.*;
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

	public ArrayList<EmployeeApproval> getUnapprovedEmployeePolicy() throws Exception{
		System.out.println("Entering getUnapprovedEmployeePolicy() in PolicyDAO Class");
		
		connect = DBConnection.getConnection();
		
		ps1 = connect.prepareStatement("SELECT p.healthInsuranceId,e.employeeId,"
				+ "e.employeeName,e.dateofBirth,p.startDate,p.totalSumInsured,"
				+ "p.policyPeriod FROM ehi.policy as p,ehi.employee as e WHERE "
				+ "p.employeeId=e.employeeId AND p.dependentId=0 AND p.status=0");
		resultSet = ps1.executeQuery();
		
		ArrayList<EmployeeApproval> unapprovedEmployeeList = new ArrayList<EmployeeApproval>();

		while(resultSet.next()){
			int healthInsuranceId = resultSet.getInt("healthInsuranceId");
			int employeeId =  resultSet.getInt("employeeId");
			String employeeName = resultSet.getString("employeeName");
			String dateOfBirth = resultSet.getString("dateOfBirth");
			dateOfBirth = dateOfBirth.substring(8, 10) + "/" + dateOfBirth.substring(5, 7) + "/" + dateOfBirth.substring(0, 4);
			String startDate = resultSet.getString("startDate");
			startDate = startDate.substring(8, 10) + "/" + startDate.substring(5, 7) + "/" + startDate.substring(0, 4);
			int sumInsured = resultSet.getInt("totalSumInsured");
			int policyPeriod = resultSet.getInt("policyPeriod");
			
			EmployeeApproval ea = new EmployeeApproval();
			
			ea.setHealthInsuranceId(healthInsuranceId);
			ea.setEmployeeId(employeeId);
			ea.setEmployeeName(employeeName);
			ea.setDateOfBirth(dateOfBirth);
			ea.setStartDate(startDate);
			ea.setPolicyPeriod(policyPeriod);
			ea.setSumInsured(sumInsured);
			
			unapprovedEmployeeList.add(ea);
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting getUnapprovedEmployeePolicy() in PolicyDAO Class");
		
		return unapprovedEmployeeList;
	}

	public int approvePolicy(String[] approvedHealthInsuranceId) throws Exception {
		System.out.println("Entering approvePolicy(String []) in PolicyDAO Class");
		
		connect = DBConnection.getConnection();
		
		String approvedHealthInsuranceId1 = Arrays.toString(approvedHealthInsuranceId);
		approvedHealthInsuranceId1 = approvedHealthInsuranceId1.substring(1, approvedHealthInsuranceId1.length() - 1);

		ps1 = connect.prepareStatement("UPDATE ehi.policy SET status=1 WHERE "
				+ "healthInsuranceId IN (?)");
		ps1.setString(1, approvedHealthInsuranceId1);
		int count = ps1.executeUpdate();
				
		DBConnection.closeConnection(connect);
		System.out.println("Exiting approvePolicy(String []) in PolicyDAO Class");
		
		return count;
	}

}
