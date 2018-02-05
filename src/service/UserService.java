package service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import dao.UserDao;
import model.Usermodel;

public class UserService {
	// 普通用户判断登录是否成功 selectUser
	private UserDao dao = new UserDao();

	public int isLoginSucceed(Usermodel user) throws UnsupportedEncodingException{// 此处的um 和servlet封装的um不一样
		if (user == null) {
			return 0;
		}
		Usermodel LoginUser = new Usermodel();
		LoginUser.setEmail(user.getEmail());
		LoginUser.setUserpass(user.getUserpass());
		List<Usermodel> CanLoginList = dao.selectUser(LoginUser);
		if (CanLoginList == null || CanLoginList.isEmpty()) {
			return -1;// 集合无值 登录不成功 -1代表用户名或密码不正确
		}
		if (CanLoginList.size() > 1) {
			return 0;// 只能查出一条 判断有没有脏数据
		}
		Usermodel userdb = CanLoginList.get(0);
		Integer isdelete = userdb.getIsdelete();
		Integer iseffect = userdb.getIseffect();
		Integer isadmin = userdb.getIsadmin();
		if (isdelete == 1) {
			return 2;// 2 代表账号状态为 删除 不能登录
		}
		if (iseffect == 0) {
			return 3;// 3代表 账号已失效
		}
		if (isadmin == 1) {
			return 4;// 4代表 账号为管理员账号
		}

		return 1; // 1代表登录成功

	}

	// 合理化(输入是否为空) 注册用户是否重复判断 selectUser(这个试一下 ajax)
	public Boolean isRegisterSucceed(Usermodel user) throws UnsupportedEncodingException {

		if (user == null) {
			return null;
		}
		Usermodel RegisterUser = new Usermodel();
		RegisterUser.setEmail(user.getEmail());
		List<Usermodel> RegisterUserList = dao.selectUser(RegisterUser);
		if (RegisterUserList != null && !RegisterUserList.isEmpty()) {
			return false;
		}

		return true;

	}

	// 判断注册添加成功
	public boolean isaddsucceed(Usermodel user) throws UnsupportedEncodingException {
		if (user == null) {
			return false;
		}
		Usermodel RegisterUser = new Usermodel();
		RegisterUser.setEmail(user.getEmail());
		RegisterUser.setUserpass(user.getUserpass());
		RegisterUser.setName(user.getName());
		RegisterUser.setSex(user.getSex());
		RegisterUser.setDescr(user.getDescr());
		RegisterUser.setBirthdate(user.getBirthdate());
		RegisterUser.setCreatetime(user.getCreatetime());
		int addSucceed = dao.add(RegisterUser);
		if (addSucceed != 0) {
			return true;//注册成功
		}

		return false;

	}

	// 用户修改信息
	// 传参数时 重新new一个实体类
	public List<Usermodel> updateUser(Usermodel user) {
		if (user == null) {
			return null;
		}
		List<Usermodel> backUserData = new ArrayList<Usermodel>();
		Usermodel updateUser = new Usermodel();
		updateUser.setEmail(user.getEmail());
		updateUser.setId(user.getId());
		updateUser.setName(user.getName());
		updateUser.setSex(user.getSex());
		updateUser.setDescr(user.getDescr());
		updateUser.setBirthdate(user.getBirthdate());
		updateUser.setCreatetime(user.getCreatetime());
		updateUser.setUpdatetime(user.getUpdatetime());
		updateUser.setIsdelete(user.getIsdelete());
		updateUser.setIseffect(user.getIseffect());
		updateUser.setIsadmin(user.getIsadmin());
		int canUpdat = dao.UpdateAdmin(updateUser);
		if (canUpdat != 0) {
			Usermodel backUser=new Usermodel();
			backUser.setId(user.getId());
			try {
			backUserData = dao.selectUser(backUser);//修改完毕 再次查询 将修改后的结果返回
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return backUserData;
		
		}

		return null;

	}

	// 查看用户自己信息 登录时将用户的信息对象存到session里 取出来作为条件查询 用那个拼接的select 直接返回集合 selectUser
	public List<Usermodel> selectUserSucceed(Usermodel user) throws UnsupportedEncodingException {
		if (user == null) {
			return null;
		}
		Usermodel selectUser = new Usermodel();
		selectUser.setEmail(user.getEmail());
		List<Usermodel> selectUserList = dao.selectUser(selectUser);
		if (selectUserList == null || selectUserList.isEmpty()) {
			return null;
		}
		if (selectUserList.size() > 1) {
			return null;
		}
		return selectUserList;

	}




}
