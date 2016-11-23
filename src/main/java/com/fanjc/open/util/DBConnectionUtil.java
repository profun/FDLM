package com.fanjc.open.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 获取数据库连接
 */
public class DBConnectionUtil {

	/** mysql数据库连接URL */
	private final static String DB_URL = "jdbc:mysql://localhost:3306/open??useUnicode=true&characterEncoding=UTF8";

	/** mysql数据库连接驱动 */
	private final static String DB_DRIVER = "com.mysql.jdbc.Driver";

	/** 数据库用户名 */
	private final static String DB_USERNAME = "root";

	/** 数据库密码 */
	private final static String DB_PASSWORD = "abcd1234";

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		/** 声明Connection连接对象 */
		Connection conn = null;
		try {
			/** 使用Class.forName()方法自动创建这个驱动程序的实例且自动调用DriverManager来注册它 */
			Class.forName(DB_DRIVER);
			/** 通过DriverManager的getConnection()方法获取数据库连接 */
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param connect
	 */
	public void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				/** 判断当前连接连接对象如果没有被关闭就调用关闭方法 */
				if (!conn.isClosed()) {
					conn.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		Statement  st = null;
		ResultSet rs = null;
		try {
			st=	DBConnectionUtil.getConnection().createStatement();
			rs = st.executeQuery("select * from User");
			if(rs.next()){
				System.out.println(rs.getLong(1)+"-"+rs.getString(2)+"-"+rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}