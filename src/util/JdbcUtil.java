package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	private static final String driveSname;
	private static final String url;
	private static final String user;
	private static final String password;
	private static Connection con;
	static {
		driveSname = Constant.DRIVERCLASS;
		url = Constant.URL;
		user = Constant.USERNAME;
		password = Constant.PASSWORD;
	}
	static {
		try {
			// 将加载驱动操作，放置在静态代码块中.这样就保证了只加载一次.
			Class.forName(driveSname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取链接
	public static Connection getConnection() throws SQLException {
		if (con == null || con.isClosed()) {  // 判断是否为空 是否连接  链接只连一次
			con = DriverManager.getConnection(url, user, password);
		}
		return con;

	}

	// 关闭操作
	public static void closeConnection() throws SQLException {
		if (con != null) {
			con.close();
		}
	}

	public static void closeStatement(Statement st) throws SQLException {
		if (st != null) {
			st.close();
		}
	}

	public static void closePreparedStatement(PreparedStatement pst) throws SQLException {
		if (pst != null) {
			pst.close();
		}
	}

	public static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}
}
