package com.bean;

public class Claim {
	
	private String claimType;
	private int claimNo;
	private String claimRaisedDate;
	private String patientName;
	private String relation;
	private double claimAmount;
	private double approvedAmount;
	private int status;
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public int getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(int claimNo) {
		this.claimNo = claimNo;
	}
	public String getClaimRaisedDate() {
		return claimRaisedDate;
	}
	public void setClaimRaisedDate(String claimRaisedDate) {
		this.claimRaisedDate = claimRaisedDate;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
	public double getApprovedAmount() {
		return approvedAmount;
	}
	public void setApprovedAmount(double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
