package com.DAO;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection = null;

	public static Connection getActiveConnection() {
		/*
		 * String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST"); String port =
		 * System.getenv("OPENSHIFT_MYSQL_DB_PORT"); System.out.println(host);
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// connection = DriverManager
			// .getConnection("jdbc:mysql://127.8.100.2:3306/se2firstapp?"
			// +
			// "user=adminYKFs38v&password=QG9RmdNVFgmc&characterEncoding=utf8");

			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sbrb?user=root&password=root&characterEncoding=utf8");

			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("ClassException " + e.toString());
			// e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLException " + e.toString());
			// e.printStackTrace();
		}
		return null;
	}
}
