package com.bean;

public class HospitalizationClaim {
	
	private int employeeId;
	private String mobNo;
	private String beneficiaryName;
	private int healthInsuranceId;
	private String hospitalName;
	private String admissionDate;
	private String dischargeDate;
	private double totalClaimAmount;
	private String typeOfInjury;
	private String alcoholInvolved;
	private String relation;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
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
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public double getTotalClaimAmount() {
		return totalClaimAmount;
	}
	public void setTotalClaimAmount(double totalClaimAmount) {
		this.totalClaimAmount = totalClaimAmount;
	}
	public String getTypeOfInjury() {
		return typeOfInjury;
	}
	public void setTypeOfInjury(String typeOfInjury) {
		this.typeOfInjury = typeOfInjury;
	}
	public String getAlcoholInvolved() {
		return alcoholInvolved;
	}
	public void setAlcoholInvolved(String alcoholInvolved) {
		this.alcoholInvolved = alcoholInvolved;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
}
