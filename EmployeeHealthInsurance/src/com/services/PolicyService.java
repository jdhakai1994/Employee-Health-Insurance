package com.services;

import java.util.ArrayList;

import com.bean.DependentApproval;
import com.bean.EmployeeApproval;
import com.bean.Policy;
import com.dao.PolicyDAO;

public class PolicyService {

	public String addPolicy(Policy policy) throws Exception{
		System.out.println("Entering addPolicy(Policy) in PolicyService Class");
		PolicyDAO pdao = new PolicyDAO();
		String reply = pdao.addPolicy(policy);
		System.out.println("Exiting addPolicy(Policy) in PolicyService Class");
		return reply;
	}

	public int fetchPolicyId(int employeeId) throws Exception {
		System.out.println("Entering fetchPolicyId(int) in PolicyService Class");
		PolicyDAO pdao = new PolicyDAO();
		int healthInsuranceId = pdao.fetchPolicyId(employeeId);
		System.out.println("Exiting fetchPolicyId(int) in PolicyService Class");
		return healthInsuranceId;
	}
	
	public int fetchPolicyId(int employeeId, int dependentId) throws Exception {
		System.out.println("Entering fetchPolicyId(int, int) in PolicyService Class");
		PolicyDAO pdao = new PolicyDAO();
		int healthInsuranceId = pdao.fetchPolicyId(employeeId, dependentId);
		System.out.println("Exiting fetchPolicyId(int, int) in PolicyService Class");
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

}
