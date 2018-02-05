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
			// ���������������������ھ�̬�������.�����ͱ�֤��ֻ����һ��.
			Class.forName(driveSname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ��ȡ����
	public static Connection getConnection() throws SQLException {
		if (con == null || con.isClosed()) {  // �ж��Ƿ�Ϊ�� �Ƿ�����  ����ֻ��һ��
			con = DriverManager.getConnection(url, user, password);
		}
		return con;

	}

	// �رղ���
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
