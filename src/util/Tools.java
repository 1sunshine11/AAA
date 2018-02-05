package util;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usermodel;

public class Tools {
	//�жϱ����Ƿ�Ϊ��
	public static Boolean isnull(String str) {

		if (str == null || "".equals(str)) {
			return true;
		}
		return false;

	}
	//�Ӳ�ѯ�Ľ������getֵ set��ʵ������
	public static Usermodel getModel(ResultSet rs) throws SQLException {
		if(rs==null) {
			return null;
		}
		Usermodel um=new Usermodel();
		um.setId(rs.getInt("id"));
		um.setEmail(rs.getString("email"));
		um.setName(rs.getString("name"));
		um.setSex(rs.getInt("sex"));
		um.setDescr(rs.getString("descr"));
		um.setBirthdate(rs.getString("birthdate"));
		um.setIsadmin(rs.getInt("isadmin"));
		um.setCreatetime(rs.getString("createtime"));
		um.setUpdatetime(rs.getString("updatetime"));
		um.setIsdelete(rs.getInt("isdelete"));
		um.setIseffect(rs.getInt("iseffect"));
		return um;
		
	}
}
