package br.inpe.worldwind.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.inpe.triangle.defaultproperties.DefaultTriangleProperties;

abstract class JDBCStatement {
	protected final DefaultTriangleProperties properties;
	protected final Connection session;

	public JDBCStatement() {
		this.properties = DefaultTriangleProperties.getInstance();
		this.session = properties.getSession();
		try {
			((org.postgresql.PGConnection) session).addDataType("geometry", Class.forName("org.postgis.PGgeometry"));
			((org.postgresql.PGConnection) session).addDataType("box3d", Class.forName("org.postgis.PGbox3d"));
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
	}

	protected ResultSet getResultSet(String sql) {
		/*
		 * Create a statement and execute a select query.
		 */
		ResultSet r = null;
		try {
			Statement s = session.createStatement();
			r = s.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println(e);
		}

		return r;
	}

}
