package com.services;

import java.sql.SQLException;
import java.util.*;

import com.bean.*;
import com.dao.ClaimDAO;

public class ClaimsService {

	public int submitDomiciliaryClaim(DomiciliaryClaim domiciliaryClaim) throws Exception {
		System.out.println("Entering submitDomiciliaryClaim(DomiciliaryClaim) in ClaimsService Class");
		ClaimDAO cdao = new ClaimDAO();
		int claimNo = cdao.submitDomiciliaryClaim(domiciliaryClaim);
		System.out.println("Exiting submitDomiciliaryClaim(DomiciliaryClaim) in ClaimsService Class");
		return claimNo;
	}

	public int submitHospitalizationClaim(HospitalizationClaim hospitalizationClaim) throws Exception {
		System.out.println("Entering submitHospitalizationClaim(HospitalizationClaim) in ClaimsService Class");
		ClaimDAO cdao = new ClaimDAO();
		int claimNo = cdao.submitHospitalizationClaim(hospitalizationClaim);
		System.out.println("Exiting submitHospitalizationClaim(HospitalizationClaim) in ClaimsService Class");
		return claimNo;
	}

	public List<Claim> searchClaim(String claimType, String relation, int[] healthInsuranceIdArray) throws Exception {
		System.out.println("Entering searchClaim(String,String) in ClaimsService Class");
		ClaimDAO cdao = new ClaimDAO();
		List<Claim> claimList = cdao.searchClaim(claimType, relation, healthInsuranceIdArray);
		System.out.println("Exiting searchClaim(String,String) in ClaimsService Class");
		return claimList;
	}

	public List<Claim> searchClaimByHealthInsuranceId(int healthInsuranceId) throws Exception {
		System.out.println("Entering searchClaim(int) in ClaimsService Class");
		ClaimDAO cdao = new ClaimDAO();
		List<Claim> claimList = cdao.searchClaimByHealthInsuranceId(healthInsuranceId);
		System.out.println("Exiting searchClaim(int) in ClaimsService Class");
		return claimList;
	}
	
	public List<DomiciliaryClaimApproval> getUnapprovedDomiciliaryClaimList() throws Exception {
		System.out.println("Entering getUnapprovedDomiciliaryClaimList() in ClaimsService Class");
		ClaimDAO cdao = new ClaimDAO();
		List<DomiciliaryClaimApproval> unapprovedDomiciliaryClaimList = cdao.getUnapprovedDomiciliaryClaimList();
		System.out.println("Exiting getUnapprovedDomiciliaryClaimList() in ClaimsService Class");
		return unapprovedDomiciliaryClaimList;
	}

	public int approveDomiciliaryClaim(Map<Integer, Double> combinations, String[] rejectedClaimNo) throws SQLException {
		System.out.println("Entering approveDomiciliaryClaim(Map<Integer, Double>, String[]) in ClaimsService Class");
		ClaimDAO cdao = new ClaimDAO();
		int count = cdao.approveDomiciliaryClaim(combinations, rejectedClaimNo);
		System.out.println("Exiting approveDomiciliaryClaim(Map<Integer, Double>, String[]) in ClaimsService Class");
		return count;
	}

	public List<HospitalizationClaimApproval> getUnapprovedHospitalizationClaimList() throws SQLException {
		System.out.println("Entering getUnapprovedHospitalizationClaimList() in ClaimsService Class");
		ClaimDAO cdao = new ClaimDAO();
		List<HospitalizationClaimApproval> unapprovedHospitalizationClaimList = cdao.getUnapprovedHospitalizationClaimList();
		System.out.println("Exiting getUnapprovedHospitalizationClaimList() in ClaimsService Class");
		return unapprovedHospitalizationClaimList;
	}

	public int approveHospitalizationClaim(Map<Integer, Double> combinations, String[] rejectedClaimNo) throws SQLException {
		System.out.println("Entering approveHospitalizationClaim(Map<Integer, Double>, String[]) in ClaimsService Class");
		ClaimDAO cdao = new ClaimDAO();
		int count = cdao.approveHospitalizationClaim(combinations, rejectedClaimNo);
		System.out.println("Exiting approveHospitalizationClaim(Map<Integer, Double>, String[]) in ClaimsService Class");
		return count;
	}

}
