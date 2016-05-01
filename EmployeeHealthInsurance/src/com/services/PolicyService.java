package com.services;

import com.bean.Policy;
import com.dao.EmployeeDAO;
import com.dao.PolicyDAO;

public class PolicyService {

	public String addPolicy(Policy policy) throws Exception{
		System.out.println("Entering addPolicy() in PolicyService Class");
		PolicyDAO pdao = new PolicyDAO();
		String reply = pdao.addPolicy(policy);
		System.out.println("Exiting addPolicy() in PolicyService Class");
		return reply;
	}

	public int fetchPolicyId(int employeeId) throws Exception {
		System.out.println("Entering fetchPolicyId() in PolicyService Class");
		PolicyDAO pdao = new PolicyDAO();
		int hiId = pdao.fetchPolicyId(employeeId);
		System.out.println("Exiting fetchPolicyId() in PolicyService Class");
		return hiId;
	}

}
