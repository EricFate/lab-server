package edu.whu.iss.utils;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	private static String url;
	private static String user;
	private static String password;
	private static String driver;
	static {
		try {
			Properties prop = new Properties();
			InputStream is =JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties")  ;
			prop.load(is);
			driver = prop.getProperty("driver");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			url = prop.getProperty("url");
			Class.forName(driver);
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
