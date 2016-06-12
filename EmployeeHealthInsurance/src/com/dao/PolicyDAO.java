package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import com.bean.*;
import com.util.DBConnection;

public class PolicyDAO {
	
	private Connection connect = null;
	private PreparedStatement ps1 = null;
	private PreparedStatement ps2 = null;
	private ResultSet resultSet = null;
	
	public String addPolicy(Policy policy) throws Exception{
		System.out.println("Entering addPolicy(Policy) in PolicyDAO Class");
		
		connect = DBConnection.getConnection();
		
		//retrieving data from policy bean
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
		System.out.println("Exiting addPolicy(Policy) in PolicyDAO Class");
		
		return "success";
	}

	public int fetchPolicyId(int employeeId, boolean isApproved) throws Exception{
		System.out.println("Entering fetchPolicyId(int, boolean) in PolicyDAO Class");
		
		connect = DBConnection.getConnection();
		
		int healthInsuranceId = 0;
		
		if(isApproved == false){
			ps1 = connect.prepareStatement("SELECT healthInsuranceId FROM ehi.policy WHERE"
					+ " employeeId=? AND dependentId=0");
			ps1.setInt(1, employeeId);
			resultSet = ps1.executeQuery();
			while(resultSet.next()){
				healthInsuranceId = resultSet.getInt("healthInsuranceId");
			}
		}
		else{
			ps1 = connect.prepareStatement("SELECT healthInsuranceId,status FROM ehi.policy WHERE"
					+ " employeeId=? AND dependentId=0");
			ps1.setInt(1, employeeId);
			resultSet = ps1.executeQuery();
			
			while(resultSet.next()){
				int status = resultSet.getInt("status");
				if(status == 1)
					healthInsuranceId = resultSet.getInt("healthInsuranceId");
				else
					healthInsuranceId = -1;
			}
		}		
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting fetchPolicyId(int, boolean) in PolicyDAO Class");
		
		return healthInsuranceId;
	}
	
	public int fetchPolicyId(int employeeId, int dependentId, boolean isApproved) throws Exception{
		System.out.println("Entering fetchPolicyId(int, int, boolean) in PolicyDAO Class");
		
		connect = DBConnection.getConnection();

		int healthInsuranceId = 0;

		if(isApproved == false){
			ps1 = connect.prepareStatement("SELECT healthInsuranceId FROM ehi.policy WHERE"
					+ " employeeId=? AND dependentId=?");
			ps1.setInt(1, employeeId);
			ps1.setInt(2, dependentId);
			resultSet = ps1.executeQuery();
			while(resultSet.next()){
				healthInsuranceId = resultSet.getInt("healthInsuranceId");
			}
		}
		else{
			ps1 = connect.prepareStatement("SELECT healthInsuranceId,status FROM ehi.policy WHERE"
					+ " employeeId=? AND dependentId=?");
			ps1.setInt(1, employeeId);
			ps1.setInt(2, dependentId);
			resultSet = ps1.executeQuery();
			while(resultSet.next()){
				int status = resultSet.getInt("status");
				if(status == 1)
					healthInsuranceId = resultSet.getInt("healthInsuranceId");
				else
					healthInsuranceId = -1;
			}
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting fetchPolicyId(int, int, boolean) in PolicyDAO Class");
		
		return healthInsuranceId;
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
			
			//making an employee approval bean
			EmployeeApproval ea = new EmployeeApproval();
			ea.setHealthInsuranceId(healthInsuranceId);
			ea.setEmployeeId(employeeId);
			ea.setEmployeeName(employeeName);
			ea.setDateOfBirth(dateOfBirth);
			ea.setStartDate(startDate);
			ea.setPolicyPeriod(policyPeriod);
			ea.setSumInsured(sumInsured);
			
			//adding the beans to an ArrayList
			unapprovedEmployeeList.add(ea);
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting getUnapprovedEmployeePolicy() in PolicyDAO Class");
		
		return unapprovedEmployeeList;
	}

	public int approvePolicy(String[] approvedHealthInsuranceId) throws Exception {
		System.out.println("Entering approvePolicy(String []) in PolicyDAO Class");
		
		connect = DBConnection.getConnection();
		
		//converting the array to a comma separated string to be use in IN statement
		String approvedHealthInsuranceId1 = Arrays.toString(approvedHealthInsuranceId);
		approvedHealthInsuranceId1 = approvedHealthInsuranceId1.substring(1, approvedHealthInsuranceId1.length() - 1);
		
		ps1 = connect.prepareStatement("UPDATE ehi.policy SET status=1 WHERE "
				+ "healthInsuranceId IN ("+approvedHealthInsuranceId1+")");
		int count = ps1.executeUpdate();
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting approvePolicy(String []) in PolicyDAO Class");
		
		return count;
	}

	public ArrayList<DependentApproval> getUnapprovedDependentPolicy() throws Exception {
		System.out.println("Entering getUnapprovedDependentPolicy() in PolicyDAO Class");
		
		connect = DBConnection.getConnection();
		
		ps1 = connect.prepareStatement("SELECT p.healthInsuranceId,e.employeeName,"
				+ "d.beneficiaryName,d.relation,d.dateofBirth,"
				+ "p.startDate,p.totalSumInsured,p.policyPeriod FROM "
				+ "ehi.policy as p INNER JOIN ehi.dependent as d ON "
				+ "p.dependentId=d.dependentId INNER JOIN ehi.employee as e ON "
				+ "e.employeeId=p.employeeId WHERE p.status=0 AND p.employeeId IN "
				+ "(SELECT employeeId FROM ehi.policy WHERE status=1)");
		resultSet = ps1.executeQuery();
		
		ArrayList<DependentApproval> unapprovedDependentList = new ArrayList<DependentApproval>();

		while(resultSet.next()){
			int healthInsuranceId = resultSet.getInt("healthInsuranceId");
			String employeeName = resultSet.getString("employeeName");
			String beneficiaryName = resultSet.getString("beneficiaryName");
			String relation = resultSet.getString("relation");
			String dateOfBirth = resultSet.getString("dateOfBirth");
			dateOfBirth = dateOfBirth.substring(8, 10) + "/" + dateOfBirth.substring(5, 7) + "/" + dateOfBirth.substring(0, 4);
			String startDate = resultSet.getString("startDate");
			startDate = startDate.substring(8, 10) + "/" + startDate.substring(5, 7) + "/" + startDate.substring(0, 4);
			int sumInsured = resultSet.getInt("totalSumInsured");
			int policyPeriod = resultSet.getInt("policyPeriod");
			
			//making an employee approval bean
			DependentApproval da = new DependentApproval();
			da.setHealthInsuranceId(healthInsuranceId);
			da.setEmployeeName(employeeName);
			da.setBeneficiaryName(beneficiaryName);
			da.setRelation(relation);
			da.setDateOfBirth(dateOfBirth);
			da.setStartDate(startDate);
			da.setPolicyPeriod(policyPeriod);
			da.setSumInsured(sumInsured);
			
			//adding the beans to an ArrayList
			unapprovedDependentList.add(da);
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting getUnapprovedDependentPolicy() in PolicyDAO Class");
		
		return unapprovedDependentList;
	}

	public ECard getECardDetails(String username, String relation) throws Exception {
		System.out.println("Entering getECardDetails(String, String) in PolicyDAO Class");
		
		connect = DBConnection.getConnection();
		
		ECard ecard = new ECard();
		ecard.setRelation(relation);
		
		if(relation.equals("self")){
			ps1 = connect.prepareStatement("SELECT p.healthInsuranceId,e.employeeName,"
				+ "e.employeeId,e.dateofBirth FROM ehi.policy as p JOIN ehi.employee as e "
				+ "ON e.employeeId=p.employeeId WHERE e.username=? AND p.dependentId=0");
			ps1.setString(1, username);
			resultSet = ps1.executeQuery();
			while(resultSet.next()){
				int healthInsuranceId = resultSet.getInt("healthInsuranceId");
				String employeeName = resultSet.getString("employeeName");
				int employeeId = resultSet.getInt("employeeId");
				String dateOfBirth = resultSet.getString("dateOfBirth");
				
				ecard.setBeneficiaryName(employeeName);
				ecard.setHealthInsuranceId(healthInsuranceId);
				ecard.setEmployeeCode(employeeId);
				ecard.setDateOfBirth(dateOfBirth);
				ecard.setPrimaryInsured(employeeName);
			}
		}
		else{
			ps2 = connect.prepareStatement("SELECT d.dependentId FROM ehi.employee as e "
					+ "JOIN ehi.dependent as d ON e.employeeId=d.employeeId WHERE "
					+ "e.username=? AND d.relation=?");
			ps2.setString(1, username);
			ps2.setString(2, relation);
			resultSet = ps2.executeQuery();
			int dependentId = 0;
			while(resultSet.next()){
				dependentId = resultSet.getInt("dependentId");
			}
			ps1 = connect.prepareStatement("SELECT p.healthInsuranceId,d.beneficiaryName,"
					+ "d.employeeId,d.dateofBirth,e.employeeName FROM ehi.policy as p JOIN "
					+ "ehi.dependent as d ON d.dependentId=p.dependentId JOIN ehi.employee "
					+ "as e ON e.employeeId=p.employeeId WHERE e.username=? AND "
					+ "p.dependentId=?");
			ps1.setString(1, username);
			ps1.setInt(2, dependentId);
			resultSet = ps1.executeQuery();
			while(resultSet.next()){
				int healthInsuranceId = resultSet.getInt("healthInsuranceId");
				String beneficiaryName = resultSet.getString("beneficiaryName");
				int employeeId = resultSet.getInt("employeeId");
				String dateOfBirth = resultSet.getString("dateOfBirth");
				String employeeName = resultSet.getString("employeeName");
				
				ecard.setBeneficiaryName(beneficiaryName);
				ecard.setHealthInsuranceId(healthInsuranceId);
				ecard.setEmployeeCode(employeeId);
				ecard.setDateOfBirth(dateOfBirth);
				ecard.setPrimaryInsured(employeeName);
			}
		}
		
		DBConnection.closeConnection(connect);
		
		System.out.println("Exiting getECardDetails(String, String) in PolicyDAO Class");
		return ecard;
	}
}
