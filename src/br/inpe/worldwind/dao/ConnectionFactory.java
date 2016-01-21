package br.inpe.worldwind.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String JDBC_DRIVER = "postgresql";
	private static final String IP = "localhost";
	private static final String DATA_BASE = "vegetation_scenario";
	private static final String USER = "postgres";
	private static final String PASSWORD = "11";

	public synchronized static Connection getSession() {
		StringBuffer driver = new StringBuffer();
		driver.append("jdbc:").append(JDBC_DRIVER).append("://").append(IP).append("/").append(DATA_BASE);
		try {
			return DriverManager.getConnection(driver.toString(), USER, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public synchronized static void shutdown(Connection session) {
		try {
			session.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
}
