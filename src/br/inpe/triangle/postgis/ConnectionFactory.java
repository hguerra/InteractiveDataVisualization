package br.inpe.triangle.postgis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private SGBD sgdb;
	private String ip;
	private String database;
	private String user;
	private String password;

	public ConnectionFactory(SGBD sgdb, String ip, String database, String user, String password) {
		this.sgdb = sgdb;
		this.ip = ip;
		this.database = database;
		this.user = user;
		this.password = password;
	}

	public synchronized Connection getSession() {
		StringBuffer driver = new StringBuffer();
		driver.append("jdbc:").append(getSgdb().toString()).append("://").append(getIp()).append("/")
				.append(getDatabase());
		try {
			return DriverManager.getConnection(driver.toString(), getUser(), getPassword());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public synchronized void shutdown(Connection session) {
		try {
			session.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public SGBD getSgdb() {
		return sgdb;
	}

	public String getIp() {
		return ip;
	}

	public String getDatabase() {
		return database;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

}
