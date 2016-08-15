package com.bean;

public class DomiciliaryClaimApproval {
	private int claimNo;
	private String beneficiaryName;
	private int healthInsuranceId;
	private String mobNo;
	private String claimRaisedDate;
	private String treatmentStartDate;
	private String typeOfInjury;
	private double totalClaimAmount;
	private double totalSumInsured;
	public int getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(int claimNo) {
		this.claimNo = claimNo;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public int getHealthInsuranceId() {
		return healthInsuranceId;
	}
	public void setHealthInsuranceId(int healthInsuranceId) {
		this.healthInsuranceId = healthInsuranceId;
	}
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	public String getClaimRaisedDate() {
		return claimRaisedDate;
	}
	public void setClaimRaisedDate(String claimRaisedDate) {
		this.claimRaisedDate = claimRaisedDate;
	}
	public String getTreatmentStartDate() {
		return treatmentStartDate;
	}
	public void setTreatmentStartDate(String treatmentStartDate) {
		this.treatmentStartDate = treatmentStartDate;
	}
	public String getTypeOfInjury() {
		return typeOfInjury;
	}
	public void setTypeOfInjury(String typeOfInjury) {
		this.typeOfInjury = typeOfInjury;
	}
	public double getTotalClaimAmount() {
		return totalClaimAmount;
	}
	public void setTotalClaimAmount(double totalClaimAmount) {
		this.totalClaimAmount = totalClaimAmount;
	}
	public double getTotalSumInsured() {
		return totalSumInsured;
	}
	public void setTotalSumInsured(double totalSumInsured) {
		this.totalSumInsured = totalSumInsured;
	}	
}