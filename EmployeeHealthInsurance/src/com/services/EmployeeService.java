package com.services;

import java.sql.SQLException;

import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.dao.HospitalDAO;

public class EmployeeService {

	public String addEmployee(Employee employee) throws Exception {
		System.out.println("Entering addEmployee() in EmployeeService Class");
		EmployeeDAO edao = new EmployeeDAO();
		String reply = edao.addEmployee(employee);
		System.out.println("Exiting addEmployee() in EmployeeService Class");
		return reply;		
	}

}
