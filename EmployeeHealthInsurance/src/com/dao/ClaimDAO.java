package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.bean.DomiciliaryClaim;
import com.util.DBConnection;

public class ClaimDAO {

	private Connection connect = null;
	private PreparedStatement ps1 = null;
	private PreparedStatement ps2 = null;
	private ResultSet resultSet = null;
	private ResultSet resultSet1 = null;

	public int submitDomiciliaryClaim(DomiciliaryClaim domiciliaryClaim) throws Exception {
		System.out.println("Entering submitDomiciliaryClaim(DomiciliaryClaim) in ClaimDAO Class");

		connect = DBConnection.getConnection();

		int claimNo = 0;
		int dependentId = 0;
		String relation = "";
		
		// retrieving data from Domiciliary Claim bean
		int employeeId = domiciliaryClaim.getEmployeeId();
		int healthInsuranceId = domiciliaryClaim.getHealthInsuranceId();
		String beneficiaryName = domiciliaryClaim.getBeneficiaryName();
		String mobNo = domiciliaryClaim.getMobNo();
		String doi = domiciliaryClaim.getDateOfInjury();
		String dateOfInjury = doi.substring(6, 10) + "-" + doi.substring(3, 5) + "-" + doi.substring(0, 2);
		String tsd = domiciliaryClaim.getDateOfInjury();
		String treatmentStartDate = tsd.substring(6, 10) + "-" + tsd.substring(3, 5) + "-" + tsd.substring(0, 2);
		double totalClaimAmount = domiciliaryClaim.getTotalClaimAmount();
		String typeOfInjury = domiciliaryClaim.getTypeOfInjury();
		
		String typeOfClaim = "domiciliary";
		
		//to get the date when the claim was raised
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String claimRaisedDate = format.format(curDate);
				
		/*to get the relation
		 * case 1 - if dependentId is 0 relation = self
		 * case 2 - if dependentId is not 0 fetching relation from dependent table
		 */
		ps1 = connect.prepareStatement("SELECT dependentId FROM ehi.policy WHERE "
				+ "employeeId=? AND healthInsuranceId=?");
		ps1.setInt(1, employeeId);
		ps1.setInt(2, healthInsuranceId);
		resultSet = ps1.executeQuery();
		while(resultSet.next()){
			dependentId = resultSet.getInt("dependentId");
		}
		if(dependentId == 0)
			relation = "self";
		else{
			ps2 = connect.prepareStatement("SELECT relation FROM ehi.dependent WHERE "
					+ "dependentId=?");
			ps2.setInt(1, dependentId);
			resultSet1 = ps2.executeQuery();
			while(resultSet1.next())
				relation = resultSet1.getString("relation");
		}
//		System.out.println(relation);
		ps1 = connect.prepareStatement("INSERT INTO ehi.claim (healthInsuranceId,type,"
				+ "claimRaisedDate,patientName,relation,totalClaimAmount) "
				+ "VALUES (?,?,?,?,?,?)");
		ps1.setInt(1, healthInsuranceId);
		ps1.setString(2, typeOfClaim);
		ps1.setString(3, claimRaisedDate);
		ps1.setString(4, beneficiaryName);
		ps1.setString(5, relation);
		ps1.setDouble(6, totalClaimAmount);
		int result = ps1.executeUpdate();
		if(result != 0){
			ps2 = connect.prepareStatement("SELECT claimNo FROM ehi.claim ORDER BY "
					+ "claimNo DESC LIMIT 1");
			resultSet = ps2.executeQuery();
			while(resultSet.next()){
				claimNo = resultSet.getInt("claimNo");
				
				ps1 = connect.prepareStatement("INSERT INTO ehi.domiciliary_claim "
						+ "(claimNo,employeeId,mobNo,beneficiaryName,dateOfInjury,"
						+ "treatmentStartDate,totalClaimAmount,typeOfInjury) "
						+ "VALUES (?,?,?,?,?,?,?,UPPER(?))");
				ps1.setInt(1, claimNo);
				ps1.setInt(2, employeeId);
				ps1.setString(3, mobNo);
				ps1.setString(4, beneficiaryName);
				ps1.setString(5, dateOfInjury);
				ps1.setString(6, treatmentStartDate);
				ps1.setDouble(7, totalClaimAmount);
				ps1.setString(8, typeOfInjury);
				int result1 = ps1.executeUpdate();
				
				if(result1 == 0)
					claimNo = 0;
			}
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting submitDomiciliaryClaim(DomiciliaryClaim) in ClaimDAO Class");
		
		System.out.println(claimNo);
		return claimNo;
	}

}
