package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Login;
import com.util.DBConnection;

public class LoginDAO {
	
	private Connection connect = null;
	private PreparedStatement ps1 = null;
	private PreparedStatement ps2 = null;
	private ResultSet resultSet = null;
	private String reply = null;
	
	public String loginUser(Login input) throws Exception{
		
		System.out.println("In loginUser() in LoginDAO Class");
	
		connect = DBConnection.getConnection();
		
		String username = input.getUsername();
		String password = input.getPassword();
		
		ps1 = connect.prepareStatement("SELECT * FROM ehi.user_credentials WHERE username=?");
		ps1.setString(1, username);
		resultSet = ps1.executeQuery();
		
		System.out.println("Credentials fetched");
		
		resultSet.last();
		int noOfRows = resultSet.getRow();
		
		if(noOfRows == 0)
			reply = "not_registered";
		else{
				String uname = resultSet.getString("username");
				String pwd = resultSet.getString("password");
				
				if(username.equals(uname) && password.equals(pwd)){
					if("admin".equals(username))
						reply = "admin";
					else
						reply = "member";
				}
				else
					reply = "wrong_credentials";
			}
		
			System.out.println(reply);
			DBConnection.closeConnection(connect);
			System.out.println("Exiting loginUser() in LoginDAO Class");
			return reply;
	}

	public String registerUser(Login input) throws SQLException {
		
		System.out.println("Entering registerUser() in LoginDAO Class");
		
		connect = DBConnection.getConnection();
		
		String username = input.getUsername();
		String password = input.getPassword();
		
		ps1 = connect.prepareStatement("SELECT * FROM ehi.user_credentials WHERE username=?");
		ps1.setString(1, username);
		resultSet = ps1.executeQuery();
		resultSet.last();
		int noOfRows = resultSet.getRow();
		
		if(noOfRows > 0)
			reply = "fail";		
		else{
			ps2 = connect.prepareStatement("INSERT INTO ehi.user_credentials "
				+ "(username,password) VALUES (?,?)");
			ps2.setString(1, username);
			ps2.setString(2, password);
			ps2.executeUpdate();
			reply = "success";
		}
		
		DBConnection.closeConnection(connect);
		System.out.println("Exiting registerUser() in LoginDAO Class");
		return reply;
	}
}
