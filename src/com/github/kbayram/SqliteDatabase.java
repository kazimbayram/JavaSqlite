package com.github.kbayram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqliteDatabase {
	private Connection	conn;
	private String		dbName;

	public SqliteDatabase(String dbName) {
		this.dbName = dbName;

	}

	/**
	 * This method for select statement
	 * 
	 * @param sql : sql query
	 * @param params Prepared statement parameters
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet query(String sql, Object... params) throws SQLException {
		PreparedStatement stmt = prepare(sql, params);
		stmt.execute();
		return stmt.getResultSet();
	}

	public ResultSet query(String sql) throws SQLException {
		return query(sql, new Object[] {});
	}
	
	
	

	/**
	 * This method for insert,delete,update statements
	 * 
	 * @param sql query sentence
	 * @param params Prepared statement parameters
	 * @return int count of affedted rows
	 * @throws SQLException
	 */
	public int execute(String sql, Object... params) throws SQLException {
		PreparedStatement stmt = prepare(sql, params);
		stmt.execute();
		return stmt.getUpdateCount();
	}

	public int execute(String sql) throws SQLException {
		return execute(sql, new Object[] {});
	}
	
	
	
	/**
	 * Create a preperared statement and add parameters to this statement.
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */

	private PreparedStatement prepare(String sql, Object... params) throws SQLException {
		connect();
		PreparedStatement stmt = conn.prepareStatement(sql);

		int i = 0;
		for (Object param : params) {
			stmt.setObject(++i, param);
		}
		return stmt;
	}
	
	/**
	 * Create connection to database.
	 * @throws SQLException
	 */
	protected void connect() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbName);
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Close connection from database;
	 * @throws SQLException
	 */
	protected void disconnect() throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
			conn = null;
		}
	}

	@Override
	protected void finalize() throws Throwable {
		disconnect();
		super.finalize();
	}

}
