package com.services;

import com.bean.Dependent;
import com.dao.DependentDAO;

public class DependentService {

	public String addDependent(Dependent dependent) throws Exception{
		System.out.println("Entering addDependent(Dependent) in DependentService Class");
		DependentDAO ddao = new DependentDAO();
		String reply = ddao.addDependent(dependent);
		System.out.println("Exiting addDependent(Dependent) in DependentService Class");
		return reply;
	}

	public int fetchDependentId(int employeeId, String relation) throws Exception {
		System.out.println("Entering fetchDependentId(int,String) in DependentService Class");
		DependentDAO ddao = new DependentDAO();
		int dependentId = ddao.fetchDependentId(employeeId, relation);
		System.out.println("Exiting fetchDependentId(int,String) in DependentService Class");
		return dependentId;
	}

}
