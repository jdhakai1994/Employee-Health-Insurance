package com.services;

import java.util.ArrayList;
import java.util.List;

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

	public List<Dependent> fetchDependentDetails(int employeeId) throws Exception {
		System.out.println("Entering fetchDependentDetails(int) in DependentService Class");
		DependentDAO ddao = new DependentDAO();
		List<Dependent> dependentList = new ArrayList<Dependent>();
		dependentList = ddao.fetchDependentDetails(employeeId);
		System.out.println("Exiting fetchDependentDetails(int) in DependentService Class");
		return dependentList;
		
	}

	public String fetchRelation(int employeeId, String name) throws Exception {
		System.out.println("Entering fetchRelation(int,String) in DependentService Class");
		DependentDAO ddao = new DependentDAO();
		String relation = ddao.fetchRelation(employeeId, name);
		System.out.println("Exiting fetchRelation(int,String) in DependentService Class");
		return relation;
	}

}
