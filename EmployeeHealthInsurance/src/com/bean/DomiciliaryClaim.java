package com.bean;

public class DomiciliaryClaim {
	private int employeeId;
	private String beneficiaryName;
	private int healthInsuranceId;
	private String mobNo;
	private String treatmentStartDate;
	private String treatmentEndDate;
	private String dateOfInjury;
	private String typeOfInjury;
	private double totalClaimAmount;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	public String getTreatmentStartDate() {
		return treatmentStartDate;
	}
	public void setTreatmentStartDate(String treatmentStartDate) {
		this.treatmentStartDate = treatmentStartDate;
	}
	public String getTreatmentEndDate() {
		return treatmentEndDate;
	}
	public void setTreatmentEndDate(String treatmentEndDate) {
		this.treatmentEndDate = treatmentEndDate;
	}
	public String getDateOfInjury() {
		return dateOfInjury;
	}
	public void setDateOfInjury(String dateOfInjury) {
		this.dateOfInjury = dateOfInjury;
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
}
