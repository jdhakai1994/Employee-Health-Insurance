package com.services;

import com.bean.Employee;
import com.dao.EmployeeDAO;

public class EmployeeService {

	public String addEmployee(Employee employee) throws Exception {
		System.out.println("Entering addEmployee(Employee) in EmployeeService Class");
		EmployeeDAO edao = new EmployeeDAO();
		String reply = edao.addEmployee(employee);
		System.out.println("Exiting addEmployee(Employee) in EmployeeService Class");
		return reply;		
	}

	public void updatePremiumAmount(String[] approvedHealthInsuranceId) throws Exception {
		System.out.println("Entering updatePremiumAmount(String []) in EmployeeService Class");
		EmployeeDAO edao = new EmployeeDAO();
		edao.updatePremiumAmount(approvedHealthInsuranceId);
		System.out.println("Exiting updatePremiumAmount(String []) in EmployeeService Class");
	}

}
