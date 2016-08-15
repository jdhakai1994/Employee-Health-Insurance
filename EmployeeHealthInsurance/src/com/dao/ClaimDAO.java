package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.bean.*;
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

	public int submitHospitalizationClaim(HospitalizationClaim hospitalizationClaim) throws Exception {
		System.out.println("Entering submitHospitalizationClaim(HospitalizationClaim) in ClaimDAO Class");

		connect = DBConnection.getConnection();
		
		int claimNo = 0;
		
		// retrieving data from Hospitalization Claim bean
		int employeeId = hospitalizationClaim.getEmployeeId();
		int healthInsuranceId = hospitalizationClaim.getHealthInsuranceId();
		String beneficiaryName = hospitalizationClaim.getBeneficiaryName();
		String hospitalName = hospitalizationClaim.getHospitalName();
		String mobNo = hospitalizationClaim.getMobNo();
		String ad = hospitalizationClaim.getAdmissionDate();
		String admissionDate = ad.substring(6, 10) + "-" + ad.substring(3, 5) + "-" + ad.substring(0, 2);
		String dd = hospitalizationClaim.getDischargeDate();
		String dischargeDate = dd.substring(6, 10) + "-" + dd.substring(3, 5) + "-" + dd.substring(0, 2);
		double totalClaimAmount = hospitalizationClaim.getTotalClaimAmount();
		String typeOfInjury = hospitalizationClaim.getTypeOfInjury();
		String alcoholInvolved = hospitalizationClaim.getAlcoholInvolved();
		String relation = hospitalizationClaim.getRelation();
		
		String typeOfClaim = "hospitalization";
		
		//to get the date when the claim was raised
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String claimRaisedDate = format.format(curDate);
		
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
				
				ps1 = connect.prepareStatement("INSERT INTO ehi.hospitalization_claim "
						+ "(claimNo,employeeId,mobNo,beneficiaryName,hospitalName,dateOfAdmission,"
						+ "dateOfDischarge,totalClaimAmount,alcoholInvolved,typeOfInjury) "
						+ "VALUES (?,?,?,?,UPPER(?),?,?,?,UPPER(?),UPPER(?))");
				ps1.setInt(1, claimNo);
				ps1.setInt(2, employeeId);
				ps1.setString(3, mobNo);
				ps1.setString(4, beneficiaryName);
				ps1.setString(5, hospitalName);
				ps1.setString(6, admissionDate);
				ps1.setString(7, dischargeDate);
				ps1.setDouble(8, totalClaimAmount);
				ps1.setString(9, alcoholInvolved);
				ps1.setString(10, typeOfInjury);
				int result1 = ps1.executeUpdate();
				
				if(result1 == 0)
					claimNo = 0;
			}
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting submitHospitalizationClaim(HospitalizationClaim) in ClaimDAO Class");
		
		return claimNo;
	}

	public ArrayList<Claim> searchClaim(String claimType, String relation, int[] healthInsuranceIdArray) throws Exception {

		System.out.println("Entering searchClaim(String,String,int []) in ClaimDAO Class");
		connect = DBConnection.getConnection();

		ArrayList<Claim> claimList = new ArrayList<Claim>();
		
		//converting the array to a comma separated string to be use in IN statement
		String healthInsuranceId = Arrays.toString(healthInsuranceIdArray);
		healthInsuranceId = healthInsuranceId.substring(1, healthInsuranceId.length() - 1);
		if("all".equals(claimType)){
			ps1 = connect.prepareStatement("SELECT * FROM ehi.claim WHERE relation=? AND "
					+ "healthInsuranceId IN ("+healthInsuranceId+")");
			ps1.setString(1, relation);
		}			
		else{
			ps1 = connect.prepareStatement("SELECT * FROM ehi.claim WHERE type=? AND relation=? "
					+ "AND healthInsuranceId IN ("+healthInsuranceId+")");
			ps1.setString(1, claimType);
			ps1.setString(2, relation);
		}			
		resultSet = ps1.executeQuery();
		while(resultSet.next()){
			
			int claimNo = resultSet.getInt("claimNo");
			String type = resultSet.getString("type");
			String claimRaisedDate = resultSet.getString("claimRaisedDate");
			claimRaisedDate = claimRaisedDate.substring(8, 10) + "/" + claimRaisedDate.substring(5, 7) + "/" + claimRaisedDate.substring(0, 4);
			String patientName = resultSet.getString("patientName");
			double claimAmount = resultSet.getDouble("totalClaimAmount");
			double approvedAmount = resultSet.getDouble("approvedAmount");
			int status = resultSet.getInt("status");
			
			Claim claim = new Claim();
			claim.setClaimNo(claimNo);
			claim.setClaimType(type);
			claim.setClaimRaisedDate(claimRaisedDate);
			claim.setPatientName(patientName);
			claim.setRelation(relation);
			claim.setClaimAmount(claimAmount);
			claim.setApprovedAmount(approvedAmount);
			claim.setStatus(status);
			
			claimList.add(claim);			
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting searchClaim(String,String,int []) in ClaimDAO Class");
		
		return claimList;
	}

	public ArrayList<Claim> searchClaimByHealthInsuranceId(int healthInsuranceId) throws Exception {
		System.out.println("Entering searchClaim(int) in ClaimDAO Class");
		connect = DBConnection.getConnection();

		ArrayList<Claim> claimList = new ArrayList<Claim>();
		
		ps1 = connect.prepareStatement("SELECT * FROM ehi.claim WHERE healthInsuranceId=?");
		ps1.setInt(1, healthInsuranceId);
		resultSet = ps1.executeQuery();
		while(resultSet.next()){
			
			int claimNo = resultSet.getInt("claimNo");
			String type = resultSet.getString("type");
			String claimRaisedDate = resultSet.getString("claimRaisedDate");
			claimRaisedDate = claimRaisedDate.substring(8, 10) + "/" + claimRaisedDate.substring(5, 7) + "/" + claimRaisedDate.substring(0, 4);
			String relation = resultSet.getString("relation");
			String patientName = resultSet.getString("patientName");
			double claimAmount = resultSet.getDouble("totalClaimAmount");
			double approvedAmount = resultSet.getDouble("approvedAmount");
			int status = resultSet.getInt("status");
			
			Claim claim = new Claim();
			claim.setClaimNo(claimNo);
			claim.setClaimType(type);
			claim.setClaimRaisedDate(claimRaisedDate);
			claim.setPatientName(patientName);
			claim.setRelation(relation);
			claim.setClaimAmount(claimAmount);
			claim.setApprovedAmount(approvedAmount);
			claim.setStatus(status);
			
			claimList.add(claim);			
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting searchClaim(int) in ClaimDAO Class");
		
		return claimList;
		
	}
	
	public List<DomiciliaryClaimApproval> getUnapprovedDomiciliaryClaimList() throws SQLException {
		System.out.println("Entering getUnapprovedDomiciliaryClaimList() in ClaimDAO Class");
		
		connect = DBConnection.getConnection();
		
		List<DomiciliaryClaimApproval> unapprovedDomiciliaryClaimList = new ArrayList<DomiciliaryClaimApproval>();
		
		ps1 = connect.prepareStatement("SELECT d.claimNo, c.healthInsuranceId, d.beneficiaryName, "
				+ "d.mobNo, c.claimRaisedDate, d.typeOfInjury, d.treatmentStartDate, "
				+ "d.totalClaimAmount, p.totalSumInsured FROM ehi.domiciliary_claim as d "
				+ "JOIN ehi.claim as c ON d.claimNo=c.claimNo JOIN ehi.policy as p ON "
				+ "c.healthInsuranceId=p.healthInsuranceId WHERE d.status=0");
		resultSet = ps1.executeQuery();
		while(resultSet.next()){
			int claimNo = resultSet.getInt("claimNo");
			int healthInsuranceId = resultSet.getInt("healthInsuranceId");
			String beneficiaryName = resultSet.getString("beneficiaryName");
			String mobNo = resultSet.getString("mobNo");
			String claimRaisedDate = resultSet.getString("claimRaisedDate");
			claimRaisedDate = claimRaisedDate.substring(8, 10) + "/" + claimRaisedDate.substring(5, 7) + "/" + claimRaisedDate.substring(0, 4);
			String treatmentStartDate = resultSet.getString("treatmentStartDate");
			treatmentStartDate = treatmentStartDate.substring(8, 10) + "/" + treatmentStartDate.substring(5, 7) + "/" + treatmentStartDate.substring(0, 4);
			String typeOfInjury = resultSet.getString("typeOfInjury");
			double totalClaimAmount = resultSet.getDouble("totalClaimAmount");
			double totalSumInsured = resultSet.getDouble("totalSumInsured");
			
			DomiciliaryClaimApproval domiciliaryClaimApproval = new DomiciliaryClaimApproval();
			domiciliaryClaimApproval.setClaimNo(claimNo);
			domiciliaryClaimApproval.setBeneficiaryName(beneficiaryName);
			domiciliaryClaimApproval.setHealthInsuranceId(healthInsuranceId);
			domiciliaryClaimApproval.setMobNo(mobNo);
			domiciliaryClaimApproval.setTreatmentStartDate(treatmentStartDate);
			domiciliaryClaimApproval.setClaimRaisedDate(claimRaisedDate);
			domiciliaryClaimApproval.setTypeOfInjury(typeOfInjury);
			domiciliaryClaimApproval.setTotalClaimAmount(totalClaimAmount);
			domiciliaryClaimApproval.setTotalSumInsured(totalSumInsured);
						
			unapprovedDomiciliaryClaimList.add(domiciliaryClaimApproval);
		}
		DBConnection.closeConnection(connect);
		System.out.println("Exiting getUnapprovedDomiciliaryClaimList() in ClaimDAO Class");
		
		return unapprovedDomiciliaryClaimList;
	}

	public int approveDomiciliaryClaim(Map<Integer, Double> combinations, String[] rejectedClaimNo) throws SQLException {
		System.out.println("Entering approveDomiciliaryClaim(Map<Integer, Double>, String[]) in ClaimDAO Class");
		
		connect = DBConnection.getConnection();
		
		int count1 = 0;
		int count2 = 0;
		
		//converting the array to a comma separated string to be use in IN statement
		if(rejectedClaimNo != null){
			String rejectedClaimNo1 = Arrays.toString(rejectedClaimNo);
			rejectedClaimNo1 = rejectedClaimNo1.substring(1, rejectedClaimNo1.length() - 1);
		
			//updating two tables with rejected claims
			ps1 = connect.prepareStatement("UPDATE ehi.domiciliary_claim SET status=2 WHERE "
				+ "claimNo IN ("+rejectedClaimNo1+")");
			ps2 = connect.prepareStatement("UPDATE ehi.claim SET status=2 WHERE "
				+ "claimNo IN ("+rejectedClaimNo1+")");
			count1 = ps1.executeUpdate();
			ps2.executeUpdate();
		}
		
		//iterating over every entry in the hash map and updating both tables
		for(Map.Entry<Integer, Double> entry: combinations.entrySet()){
			int claimNo = entry.getKey();
			double approvedAmount = entry.getValue();
			ps1 = connect.prepareStatement("UPDATE ehi.domiciliary_claim SET status=1 WHERE claimNo=?");
			ps1.setInt(1, claimNo);
			ps1.executeUpdate();
			ps2 = connect.prepareStatement("UPDATE ehi.claim SET status=1, approvedAmount=? WHERE claimNo=?");
			ps2.setDouble(1, approvedAmount);
			ps2.setInt(2, claimNo);
			int count = ps2.executeUpdate();
			count2 += count;
		}
		DBConnection.closeConnection(connect);
		System.out.println("Exiting approveDomiciliaryClaim(Map<Integer, Double>, String[]) in ClaimDAO Class");
		
		return count1+count2;
	}

	public List<HospitalizationClaimApproval> getUnapprovedHospitalizationClaimList() throws SQLException {
		System.out.println("Entering getUnapprovedHospitalizationClaimList() in ClaimDAO Class");
		
		connect = DBConnection.getConnection();
		
		List<HospitalizationClaimApproval> unapprovedHospitalizationClaimList = new ArrayList<HospitalizationClaimApproval>();
		
		ps1 = connect.prepareStatement("SELECT h.claimNo, c.healthInsuranceId, h.beneficiaryName, "
				+ "h.mobNo, c.claimRaisedDate, h.typeOfInjury, h.dateOfAdmission, h.dateOfDischarge,"
				+ " h.totalClaimAmount, h.alcoholInvolved, p.totalSumInsured FROM "
				+ "ehi.hospitalization_claim as h JOIN ehi.claim as c ON h.claimNo=c.claimNo "
				+ "JOIN ehi.policy as p ON c.healthInsuranceId=p.healthInsuranceId WHERE h.status=0");
		resultSet = ps1.executeQuery();
		while(resultSet.next()){
			int claimNo = resultSet.getInt("claimNo");
			int healthInsuranceId = resultSet.getInt("healthInsuranceId");
			String beneficiaryName = resultSet.getString("beneficiaryName");
			String mobNo = resultSet.getString("mobNo");
			String claimRaisedDate = resultSet.getString("claimRaisedDate");
			claimRaisedDate = claimRaisedDate.substring(8, 10) + "/" + claimRaisedDate.substring(5, 7) + "/" + claimRaisedDate.substring(0, 4);
			String dateOfAdmission = resultSet.getString("dateOfAdmission");
			dateOfAdmission = dateOfAdmission.substring(8, 10) + "/" + dateOfAdmission.substring(5, 7) + "/" + dateOfAdmission.substring(0, 4);
			String dateOfDischarge = resultSet.getString("dateOfDischarge");
			dateOfDischarge = dateOfDischarge.substring(8, 10) + "/" + dateOfDischarge.substring(5, 7) + "/" + dateOfDischarge.substring(0, 4);
			String typeOfInjury = resultSet.getString("typeOfInjury");
			double totalClaimAmount = resultSet.getDouble("totalClaimAmount");
			double totalSumInsured = resultSet.getDouble("totalSumInsured");
			String alcoholInvolved = resultSet.getString("alcoholInvolved");
			
			HospitalizationClaimApproval hospitalizationClaimApproval = new HospitalizationClaimApproval();
			hospitalizationClaimApproval.setClaimNo(claimNo);
			hospitalizationClaimApproval.setBeneficiaryName(beneficiaryName);
			hospitalizationClaimApproval.setHealthInsuranceId(healthInsuranceId);
			hospitalizationClaimApproval.setMobNo(mobNo);
			hospitalizationClaimApproval.setDateOfAdmission(dateOfAdmission);
			hospitalizationClaimApproval.setDateOfDischarge(dateOfDischarge);
			hospitalizationClaimApproval.setClaimRaisedDate(claimRaisedDate);
			hospitalizationClaimApproval.setTypeOfInjury(typeOfInjury);
			hospitalizationClaimApproval.setTotalClaimAmount(totalClaimAmount);
			hospitalizationClaimApproval.setTotalSumInsured(totalSumInsured);
			hospitalizationClaimApproval.setAlcoholInvolved(alcoholInvolved);
						
			unapprovedHospitalizationClaimList.add(hospitalizationClaimApproval);
		}
		DBConnection.closeConnection(connect);
		System.out.println("Exiting getUnapprovedHospitalizationClaimList() in ClaimDAO Class");
		
		return unapprovedHospitalizationClaimList;
	}

	public int approveHospitalizationClaim(Map<Integer, Double> combinations, String[] rejectedClaimNo) throws SQLException {
		System.out.println("Entering approveHospitalizationClaim(Map<Integer, Double>, String[]) in ClaimDAO Class");
		
		connect = DBConnection.getConnection();
		
		int count1 = 0;
		int count2 = 0;
		
		//converting the array to a comma separated string to be use in IN statement
		if(rejectedClaimNo != null){
			String rejectedClaimNo1 = Arrays.toString(rejectedClaimNo);
			rejectedClaimNo1 = rejectedClaimNo1.substring(1, rejectedClaimNo1.length() - 1);
		
			//updating two tables with rejected claims
			ps1 = connect.prepareStatement("UPDATE ehi.hospitalization_claim SET status=2 WHERE "
				+ "claimNo IN ("+rejectedClaimNo1+")");
			ps2 = connect.prepareStatement("UPDATE ehi.claim SET status=2 WHERE "
				+ "claimNo IN ("+rejectedClaimNo1+")");
			count1 = ps1.executeUpdate();
			ps2.executeUpdate();
		}
		
		//iterating over every entry in the hash map and updating both tables
		for(Map.Entry<Integer, Double> entry: combinations.entrySet()){
			int claimNo = entry.getKey();
			double approvedAmount = entry.getValue();
			ps1 = connect.prepareStatement("UPDATE ehi.hospitalization_claim SET status=1 WHERE claimNo=?");
			ps1.setInt(1, claimNo);
			ps1.executeUpdate();
			ps2 = connect.prepareStatement("UPDATE ehi.claim SET status=1, approvedAmount=? WHERE claimNo=?");
			ps2.setDouble(1, approvedAmount);
			ps2.setInt(2, claimNo);
			int count = ps2.executeUpdate();
			count2 += count;
		}
		DBConnection.closeConnection(connect);
		System.out.println("Exiting approveHospitalizationClaim(Map<Integer, Double>, String[]) in ClaimDAO Class");
		
		return count1+count2;
	}

}
