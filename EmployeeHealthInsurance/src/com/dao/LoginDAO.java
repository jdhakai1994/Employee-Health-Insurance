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
		
		System.out.println("Entering loginUser(Login) in LoginDAO Class");
	
		connect = DBConnection.getConnection();
		
		//retrieving data from login bean
		String username = input.getUsername();
		String password = input.getPassword();
		long logon = input.getLogon();
		
		ps1 = connect.prepareStatement("SELECT * FROM ehi.user_credentials "
				+ "WHERE username=?");
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
				long lastLogon = resultSet.getLong("lastLogon");
				
				if(logon > lastLogon){
					System.out.println(logon);
					System.out.println(lastLogon);
					if(username.equals(uname) && password.equals(pwd)){
						if("admin".equals(username))
							reply = "admin";
						else
							reply = "member";
						
						//modify the last logon time
						ps2 = connect.prepareStatement("UPDATE ehi.user_credentials SET "
								+ "lastLogon=? WHERE username=?");
						ps2.setLong(1, logon);
						ps2.setString(2, username);
						ps2.executeUpdate();
					}
					else
						reply = "wrong_credentials";
				}
				else
					reply = "session_expired";				
		}
		
		System.out.println(reply);
		DBConnection.closeConnection(connect);
		System.out.println("Exiting loginUser(Login) in LoginDAO Class");
		return reply;
	}

	public String registerUser(Login input) throws SQLException {
		
		System.out.println("Entering registerUser(Login) in LoginDAO Class");
		
		connect = DBConnection.getConnection();
		
		//retrieving data from login bean
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
		System.out.println("Exiting registerUser(Login) in LoginDAO Class");
		return reply;
	}
}
