package com.services;

import com.bean.Login;
import com.dao.LoginDAO;

public class LoginService {
	
	public String loginUser(Login input) throws Exception{
		System.out.println("Entering loginUser(Login) in LoginService Class");
		LoginDAO ldao = new LoginDAO();
		String reply = ldao.loginUser(input);
		System.out.println("Exiting loginUser(Login) in LoginService Class");
		return reply;
	}

	public String registerUser(Login input) throws Exception{
		System.out.println("Entering registerUser(Login) in LoginService Class");
		LoginDAO ldao = new LoginDAO();
		String reply = ldao.registerUser(input);
		System.out.println("Exiting registerUser(Login) in LoginService Class");
		return reply;
	}
}
