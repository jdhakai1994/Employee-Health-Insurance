package com.bean;

public class Policy {
	
	private int healthInsuranceId;
	private int employeeId;
	private int dependentId;
	private String startDate;
	private int policyPeriod;
	private double totalSumInsured;
	private int status;
	
	public int getHealthInsuranceId() {
		return healthInsuranceId;
	}
	public void getHealthInsuranceId(int healthInsuranceId) {
		this.healthInsuranceId = healthInsuranceId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getDependentId() {
		return dependentId;
	}
	public void setDependentId(int dependentId) {
		this.dependentId = dependentId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getPolicyPeriod() {
		return policyPeriod;
	}
	public void setPolicyPeriod(int policyPeriod) {
		this.policyPeriod = policyPeriod;
	}
	public double getTotalSumInsured() {
		return totalSumInsured;
	}
	public void setTotalSumInsured(double totalSumInsured) {
		this.totalSumInsured = totalSumInsured;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
