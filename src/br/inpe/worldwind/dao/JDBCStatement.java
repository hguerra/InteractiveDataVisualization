package br.inpe.worldwind.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class JDBCStatement {
	protected final Connection session;

	public JDBCStatement() {
		this.session = ConnectionFactory.getSession();
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

	protected enum DDL_Statement {
		CREATE_TABLE, CREATE_INDEX, CREATE_VIEW, ALTER_TABLE, ALTER_INDEX, DROP_INDEX, DROP_VIEW;
	}

	protected enum DML_Statement {
		INSERT, UPDATE, DELETE;
	}

	protected enum DQL_Statement {
		SELECT, ALL, FROM, WHERE, GROUP_BY, HAVING, ORDER_BY, DISTINCT, UNION;
	}

	protected enum LogicalOperators {
		AND, OR, NOT;
	}

	protected enum RelationalOperators {
		LESS_THAN, GREATER_THAN, LESS_OR_EQUAL, GREATER_OR_EQUAL, EQUALS, NOT_EQUALS;
	}

}
