package com.hrms.db;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBConnection {
	private static DataSource dataSource = null;

	static {
		dataSource = new ComboPooledDataSource("login");
	}

	// 获得数据库连接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭数据库连接
	public static void release(Connection conn) {

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// 关闭数据库连接
	public static void release(ResultSet rs, Connection conn) {
		try {
			if(rs != null){
				rs.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
