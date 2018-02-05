package service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDao;

import model.Usermodel;

public class AdminService {
	private UserDao dao = new UserDao();

	// 判断管理员登录是否成功
	public int isadminLoginSucceed(Usermodel user) throws UnsupportedEncodingException {
		if (user == null) {
			return 0;

		}
		Usermodel adminLogin = new Usermodel();
		adminLogin.setEmail(user.getEmail());
		adminLogin.setUserpass(user.getUserpass());
		List<Usermodel> adminLoginList = dao.selectUser(adminLogin);
		if (adminLoginList == null || adminLoginList.isEmpty()) {
			return 1;// 账号或密码错误

		}

		Usermodel adminLoginsucceed = adminLoginList.get(0);
		Integer isdelete = adminLoginsucceed.getIsdelete();
		Integer iseffect = adminLoginsucceed.getIseffect();
		Integer isadmin = adminLoginsucceed.getIsadmin();
		if (isdelete == 1) {
			return 2;// 账号已删除
		}
		if (iseffect == 0) {
			return 3; // 账号已失效
		}
		if (isadmin == 1) {
			return 4;// 账号成功登录
		}

		return 1;
	}
	//管理员查看全部用户信息 和查询
	public List<Usermodel> selectAllUserSucceed(Usermodel user) {
		if (user == null) {
			return null;
		}
		List<Usermodel> selectAllUserList= new ArrayList<Usermodel>();
		Usermodel selectAllUser = new Usermodel();
		selectAllUser.setName(user.getName());
		selectAllUser.setEmail(user.getEmail());
		selectAllUser.setSex(user.getSex());
		selectAllUser.setIsdelete(user.getIsdelete());
		selectAllUser.setIseffect(user.getIseffect());
		selectAllUser.setCreatetime(user.getCreatetime());
		selectAllUser.setUpdatetime(user.getUpdatetime());
		selectAllUser.setDescr(user.getDescr());
		selectAllUser.setIsadmin(user.getIsadmin());
		selectAllUser.setBirthStart(user.getBirthStart());
		selectAllUser.setBirthEnd(user.getBirthEnd());
		selectAllUser.setCreatetimeStart(user.getCreatetimeStart());
		selectAllUser.setCreatetimeEnd(user.getCreatetimeEnd());
		selectAllUser.setUpdatetimeStart(user.getUpdatetimeStart());
		selectAllUser.setUpdatetimeEnd(user.getUpdatetimeEnd());
		selectAllUser.setPageNumber(user.getPageNumber());
		selectAllUser.setPageSize(user.getPageSize());
		try {
		        selectAllUserList = dao.selectUser(selectAllUser);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return selectAllUserList;
		
	}

}
