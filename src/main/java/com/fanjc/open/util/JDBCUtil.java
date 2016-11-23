package com.fanjc.open.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * JDBC连接工具类
 * 
 * @author fanjc
 *
 */
@SuppressWarnings("static-access")
public class JDBCUtil {
	private static DataSource ds = null;

	static {
		InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("config.properties");
		Properties prop = new Properties();
		BasicDataSourceFactory factory = new BasicDataSourceFactory();
		try {
			prop.load(in);
			ds = factory.createDataSource(prop);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static void release(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close(); // throw new
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			st = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *  main
	 * @param args
	 * @throws SQLException
	 */

	public static void main(String[] args) throws SQLException {

		System.out.println("测试123456");
		Statement st = null;
		ResultSet rs = null;

		try {
			st = JDBCUtil.getConnection().createStatement();
			rs = st.executeQuery("select * from User");
			if (rs.next()) {
				System.out.println(rs.getLong(1) + "-" + rs.getString(2) + "-" + rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
