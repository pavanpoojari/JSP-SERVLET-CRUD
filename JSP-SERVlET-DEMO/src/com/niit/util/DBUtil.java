package com.niit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static final String JDBC_URL ="jdbc:h2:tcp://localhost/~/test";
	private static final String DRIVER_CLASS ="org.h2.Driver";
	private static final String JDBC_USERNAME ="sa";
	private static final String JDBC_PASSWORD ="sa";
	
	private static Connection con = null;
	
	private DBUtil(){}
	
	public static Connection getConnection(){
		if(con==null){
			try{
				Class.forName(DRIVER_CLASS);
				System.out.println("Driver Loaded Successfully");
				con = DriverManager.getConnection(JDBC_URL,JDBC_USERNAME,JDBC_PASSWORD);
				System.out.println("Connection Establisted");
			}
			catch(ClassNotFoundException | SQLException sq){
				System.out.println("Driver Loading Or Connection Failed: "+sq.getMessage());
			}
		}
		return con;
	} 
}
