package com.services;

import java.util.ArrayList;

import com.bean.*;
import com.dao.PolicyDAO;

public class PolicyService {

	public String addPolicy(Policy policy) throws Exception{
		System.out.println("Entering addPolicy(Policy) in PolicyService Class");
		PolicyDAO pdao = new PolicyDAO();
		String reply = pdao.addPolicy(policy);
		System.out.println("Exiting addPolicy(Policy) in PolicyService Class");
		return reply;
	}

	public int fetchPolicyId(int employeeId,boolean isApproved) throws Exception {
		System.out.println("Entering fetchPolicyId(int, boolean) in PolicyService Class");
		PolicyDAO pdao = new PolicyDAO();
		int healthInsuranceId = pdao.fetchPolicyId(employeeId, isApproved);
		System.out.println("Exiting fetchPolicyId(int, boolean) in PolicyService Class");
		return healthInsuranceId;
	}
	
	public int fetchPolicyId(int employeeId, int dependentId,boolean isApproved) throws Exception {
		System.out.println("Entering fetchPolicyId(int, int, boolean) in PolicyService Class");
		PolicyDAO pdao = new PolicyDAO();
		int healthInsuranceId = pdao.fetchPolicyId(employeeId, dependentId, isApproved);
		System.out.println("Exiting fetchPolicyId(int, int, boolean) in PolicyService Class");
		return healthInsuranceId;
	}

	public ArrayList<EmployeeApproval> getUnapprovedEmployeePolicy() throws Exception{
		System.out.println("Entering getUnapprovedEmployeePolicy() in PolicyService Class");
		PolicyDAO pdao = new PolicyDAO();
		ArrayList<EmployeeApproval> unapprovedEmployeeList = pdao.getUnapprovedEmployeePolicy();
		System.out.println("Exiting getUnapprovedEmployeePolicy() in PolicyService Class");
		return unapprovedEmployeeList;		
	}

	public int approvePolicy(String[] approvedHealthInsuranceId) throws Exception {
		System.out.println("Entering approvePolicy(String []) in PolicyService Class");
		PolicyDAO pdao = new PolicyDAO();
		int count = pdao.approvePolicy(approvedHealthInsuranceId);
		System.out.println("Exiting approvePolicy(String []) in PolicyService Class");
		return count;
	}

	public ArrayList<DependentApproval> getUnapprovedDependentPolicy() throws Exception {
		System.out.println("Entering getUnapprovedDependentPolicy() in PolicyService Class");
		PolicyDAO pdao = new PolicyDAO();
		ArrayList<DependentApproval> unapprovedDependentList = pdao.getUnapprovedDependentPolicy();
		System.out.println("Exiting getUnapprovedDependentPolicy() in PolicyService Class");
		return unapprovedDependentList;
	}

	public ECard getECardDetails(String username, String relation) throws Exception {
		System.out.println("Entering getECardDetails(String, String) in PolicyService Class");
		PolicyDAO pdao = new PolicyDAO();
		ECard ecard = pdao.getECardDetails(username, relation);
		System.out.println("Exiting getECardDetails(String, String) in PolicyService Class");
		return ecard;		
	}

	public int fetchPolicyId(int employeeId, String beneficiaryName) throws Exception {
		System.out.println("Entering fetchPolicyId(int, String) in PolicyService Class");
		PolicyDAO pdao = new PolicyDAO();
		int healthInsuranceId = pdao.fetchPolicyId(employeeId, beneficiaryName);
		System.out.println("Exiting fetchPolicyId(int, String) in PolicyService Class");
		return healthInsuranceId;
	}

	public ArrayList<Integer> fetchPolicyIdList(int employeeId) throws Exception {
		System.out.println("Entering fetchPolicyIdList(int) in PolicyService Class");
		PolicyDAO pdao = new PolicyDAO();
		ArrayList<Integer> list = pdao.fetchPolicyIdList(employeeId);
		System.out.println("Exiting fetchPolicyIdList(int) in PolicyService Class");
		return list;
	}
}
