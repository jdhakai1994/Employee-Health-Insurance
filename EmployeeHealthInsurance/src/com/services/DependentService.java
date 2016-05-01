package com.services;

import com.bean.Dependent;
import com.dao.DependentDAO;
import com.dao.EmployeeDAO;

public class DependentService {

	public String addDependent(Dependent dependent) throws Exception{
		System.out.println("Entering addDependent() in DependentService Class");
		DependentDAO ddao = new DependentDAO();
		String reply = ddao.addDependent(dependent);
		System.out.println("Exiting addDependent() in DependentService Class");
		return reply;
	}

	public int fetchDependentId(int employeeId, String relation) throws Exception {
		System.out.println("Entering fetchDependentId() in DependentService Class");
		DependentDAO ddao = new DependentDAO();
		int dependentId = ddao.fetchDependentId(employeeId, relation);
		System.out.println("Exiting fetchDependentId() in DependentService Class");
		return dependentId;
	}

}
