package com.csc.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static String username;
	private static String password;

	private static String url;
	private static String driverName;
/*
 * Parameterized  constructor assigning default values
 */
	public ConnectionManager(String url, String driverName, String username,
			String password) {
		ConnectionManager.url = url;
		ConnectionManager.driverName = driverName;
		ConnectionManager.username = username;
		ConnectionManager.password = password;
	}
/*
 * method with return type Connection making connection with db and loading drivers
 */
	public static Connection getConnection() {
		Connection con = null;

		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");

		} catch (ClassNotFoundException e) {
			System.out.println("not found");

		} catch (SQLException e) {
			System.out.println("sql exception thrown");

		}
		return con;

	}

}