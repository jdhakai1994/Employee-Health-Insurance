package com.services;

import com.bean.Login;
import com.dao.LoginDAO;

public class LoginService {
	
	public String loginUser(Login input) throws Exception{
		System.out.println("Entering loginUser() in LoginService Class");
		LoginDAO ldao = new LoginDAO();
		String reply = ldao.loginUser(input);
		System.out.println("Exiting loginUser() in LoginService Class");
		return reply;
	}

	public String registerUser(Login input) throws Exception{
		System.out.println("Entering registerUser() in LoginService Class");
		LoginDAO ldao = new LoginDAO();
		String reply = ldao.registerUser(input);
		System.out.println("Exiting registerUser() in LoginService Class");
		return reply;
	}
}
