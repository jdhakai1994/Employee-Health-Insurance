package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {
	
	public static Connection getConnection() throws SQLException{
		System.out.println("Entering getConnection() in DBConnection Class");
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/ehi?"
              + "user=user&password=1234");			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		System.out.println("Exiting getConnection() in DBConnection Class");
		return con;
	}
	
	public static void closeConnection(Connection con){
		System.out.println("Entering closeConnection() in DBConnection Class");
		if(con != null){
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}			
		}
		System.out.println("Exiting closeConnection() in DBConnection Class");
	}

}
