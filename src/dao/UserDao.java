package dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Usermodel;
import util.JdbcUtil;
import util.Md5;
import util.Tools;

public class UserDao {
	PreparedStatement pst = null;
	Connection con = null;
	ResultSet rs = null;

	// 管理员、用户查询
	public List<Usermodel> selectUser(Usermodel um) throws UnsupportedEncodingException {
		if (um == null) {
			return null;
		}
		StringBuffer sql = new StringBuffer("select * from work.user where 1=1");

		Integer id = um.getId();
		String email = um.getEmail();
		String pass = um.getUserpass();
		String userpass=Md5.jiami(pass);
		String birth = um.getBirthdate();
		String name = um.getName();
		String descr = um.getDescr();
		String createtime = um.getCreatetime();
		Integer isadmin = um.getIsadmin();
		Integer iseffect = um.getIseffect();
		Integer isdelete = um.getIsdelete();
		Integer sex = um.getSex();
		String updatetime = um.getUpdatetime();
		String BE = um.getBirthEnd();
		String BS = um.getBirthStart();
		String CS = um.getCreatetimeStart();
		String CE = um.getCreatetimeEnd();
		String US = um.getUpdatetimeStart();
		String UE = um.getUpdatetimeEnd();
		 Integer pageSize = um.getPageSize();
		 Integer pageNumber = um.getPageNumber();
		List<Object> list = new ArrayList<Object>();// 集合的类型不定 有String 有数字
		if (id != null) {
			sql.append(" and id = ?");// and 前加空格
			list.add(id);
		}
		if (!Tools.isnull(email)) {

			sql.append(" and email = ?");
			list.add(email);

		}
		if (!Tools.isnull(userpass)) {
			sql.append(" and userpass = ?");
			list.add(userpass);
		}
		if (!Tools.isnull(name)) {
			sql.append(" and name like ?");
			list.add("%" + name + "%");

		}
		if (!Tools.isnull(descr)) {
			sql.append(" and descr like ?");
			list.add("%" + descr + "%");
		}
		if (Tools.isnull(CS)&&!Tools.isnull(CE)) {
		 CS = "0000-00-00 00:00:00";
		}
		if (!Tools.isnull(CS)&&Tools.isnull(CE)) {
	     CE = "9999-99-99 00:00:00";
		}
		if (Tools.isnull(US)&&!Tools.isnull(UE)) {
			US = "0000-00-00 00:00:00";
		}
		if (Tools.isnull(UE)&&!Tools.isnull(US)) {
			UE = "9999-99-99 00:00:00";
		}
		if (Tools.isnull(BS)&&!Tools.isnull(BE)) {
			BS = "0000-00-00 00:00:00";
		}
		if (Tools.isnull(BE)&&!Tools.isnull(BS)) {
			BE = "9999-99-99 00:00:00";
		} // 写在service层

		if (!Tools.isnull(CS)&&!Tools.isnull(CE)) {
			sql.append(" and createtime between ? and ?");
			list.add(CS);
			list.add(CE);
		}
		if (!Tools.isnull(US)&&!Tools.isnull(UE)) {
			sql.append(" and updatetime between ? and ?");
			list.add(US);
			list.add(UE);
		}
		if (!Tools.isnull(BS)&&!Tools.isnull(BE)) {
			sql.append(" and birthdate between ? and ?");
			list.add(BS);
			list.add(BE);
		}
		if (isadmin != null) {
			sql.append(" and isadmin = ?");
			list.add(isadmin);
		}
		if (iseffect != null) {
			sql.append(" and iseffect = ?");
			list.add(iseffect);
		}
		if (isdelete != null) {
			sql.append(" and isdelete= ?");
			list.add(isdelete);

		}
		if (sex != null) {
			sql.append(" and sex=?");
			list.add(sex);
		}
		if(pageNumber!=null && pageSize!=null) {
			sql.append(" limit ?,?");
			Integer rowsIndex=(pageNumber-1)*pageSize;
			Integer rowsSize=pageSize;
			list.add(rowsIndex);
			list.add(rowsSize);
		}
		String sql1 = sql.toString();
		List<Usermodel> userList = new ArrayList<Usermodel>();
		try {
			con = JdbcUtil.getConnection();
			pst = con.prepareStatement(sql1);
			for (int i = 0; i < list.size(); i++) {
				pst.setObject(i + 1, list.get(i));
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				userList.add(Tools.getModel(rs));
			}
			return userList;// 返回集合

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	// 管理员、用户点击保存将修改的信息保存到数据库
	public int UpdateAdmin(Usermodel um) {
		int id = um.getId();
		String email = um.getEmail();
		String name = um.getName();
		int sex = um.getSex();
		String descr = um.getDescr();
		String birth = um.getBirthdate();
		String ctime = um.getCreatetime();
		String utime = um.getUpdatetime();
		int isd = um.getIsdelete();
		int isf = um.getIseffect();
		int isa = um.getIsadmin();
		String sql = "update work.user set email=?,name=?,sex=?,descr=?,birthdate=?,createtime=?,updatetime=?,isdelete=?,iseffect=?,isadmin=? where id=?";
		try {
			con = JdbcUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, name);
			pst.setInt(3, sex);
			pst.setString(4, descr);
			pst.setString(5, birth);
			pst.setString(6, ctime);
			pst.setString(7, utime);
			pst.setInt(8, isd);
			pst.setInt(9, isf);
			pst.setInt(10, isa);
			pst.setInt(11, id);
			return pst.executeUpdate();// return 此处返回一下成功=1或失败=0的结果
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeResultSet(rs);
				JdbcUtil.closePreparedStatement(pst);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;// return=0; 一下返回出现异常的情况
	}

	// 管理员、用户删除信息
	public void DeleteAdmin(Usermodel um) {
		String sql = "update work.user set isdelete=1,iseffect=0 where id=?";// 管理员删除即将isdelete的值为一
		int id = um.getId();
		try {
			con = JdbcUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeResultSet(rs);
				JdbcUtil.closePreparedStatement(pst);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// 获得管理员信息
	public Usermodel SelectAdmined(Usermodel um) {

		String user = um.getEmail();
		String sql = "select email,name from work.user where email=? and isadmin=1";
		try {
			con = JdbcUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, user);
			rs = pst.executeQuery();
			while (rs.next()) {

				String email = rs.getString(1);

				String name = rs.getString(2);

				um.setEmail(email);

				um.setName(name);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeResultSet(rs);
				JdbcUtil.closePreparedStatement(pst);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return um;

	}

	public int add(Usermodel um) throws UnsupportedEncodingException {
		int added = 0;
		String email = um.getEmail();
		String userpassw = um.getUserpass();
		String name = um.getName();
		int sex = um.getSex();
		String descr = um.getDescr();
		String birth = um.getBirthdate();
		String ctime = um.getCreatetime();
		String userpass = Md5.jiami(userpassw);// 调用密码加密
		String sql = "insert into work.user values(null,?,?,?,?,?,?,0,?,null,0,1)";
		try {
			con = JdbcUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, userpass);
			pst.setString(3, name);
			pst.setInt(4, sex);
			pst.setString(5, descr);
			pst.setString(6, birth);
			pst.setString(7, ctime);
			return pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				JdbcUtil.closePreparedStatement(pst);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return 0;// 异常返回

	}

	// 用户自己删除信息
	public void DeleteUser(Usermodel um) {
		String sql = "update work.user set isdelete=1,iseffect=0 where id=?";
		int id = um.getId();
		try {
			con = JdbcUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeResultSet(rs);
				JdbcUtil.closePreparedStatement(pst);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	//查询 数据库 记录 总记录数
	public Integer selectRows() {
	
		String Sql="select count(1) from work.user where 1=1";
		try {
			con=JdbcUtil.getConnection();
			pst=con.prepareStatement(Sql);
			rs=pst.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
		
		
		
		
		
		
	}

}
