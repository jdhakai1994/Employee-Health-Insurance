package com.services;

import com.bean.DomiciliaryClaim;
import com.bean.HospitalizationClaim;
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

}
