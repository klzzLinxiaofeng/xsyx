package com.dev.code.Generator.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static String DB_USERNAME;
	private static String DB_PASSWORD;
	private static String DB_URL;
	private static String DB_DRIVER;
	
	public DBUtil () {
		PropertiestUtils propertiestUtils = new PropertiestUtils();
		DB_USERNAME = propertiestUtils.getValue("jdbc_username");
		DB_PASSWORD = propertiestUtils.getValue("jdbc_password");
		DB_URL = propertiestUtils.getValue("jdbc_url");
		DB_DRIVER = propertiestUtils.getValue("jdbc_driver");
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			throw (e);
		}
		return connection;
	}
	
	public void closeConnection(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
