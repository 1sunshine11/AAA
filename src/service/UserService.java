package service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import dao.UserDao;
import model.Usermodel;

public class UserService {
	// ��ͨ�û��жϵ�¼�Ƿ�ɹ� selectUser
	private UserDao dao = new UserDao();

	public int isLoginSucceed(Usermodel user) throws UnsupportedEncodingException{// �˴���um ��servlet��װ��um��һ��
		if (user == null) {
			return 0;
		}
		Usermodel LoginUser = new Usermodel();
		LoginUser.setEmail(user.getEmail());
		LoginUser.setUserpass(user.getUserpass());
		List<Usermodel> CanLoginList = dao.selectUser(LoginUser);
		if (CanLoginList == null || CanLoginList.isEmpty()) {
			return -1;// ������ֵ ��¼���ɹ� -1�����û��������벻��ȷ
		}
		if (CanLoginList.size() > 1) {
			return 0;// ֻ�ܲ��һ�� �ж���û��������
		}
		Usermodel userdb = CanLoginList.get(0);
		Integer isdelete = userdb.getIsdelete();
		Integer iseffect = userdb.getIseffect();
		Integer isadmin = userdb.getIsadmin();
		if (isdelete == 1) {
			return 2;// 2 �����˺�״̬Ϊ ɾ�� ���ܵ�¼
		}
		if (iseffect == 0) {
			return 3;// 3���� �˺���ʧЧ
		}
		if (isadmin == 1) {
			return 4;// 4���� �˺�Ϊ����Ա�˺�
		}

		return 1; // 1�����¼�ɹ�

	}

	// ����(�����Ƿ�Ϊ��) ע���û��Ƿ��ظ��ж� selectUser(�����һ�� ajax)
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

	// �ж�ע����ӳɹ�
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
			return true;//ע��ɹ�
		}

		return false;

	}

	// �û��޸���Ϣ
	// ������ʱ ����newһ��ʵ����
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
			backUserData = dao.selectUser(backUser);//�޸���� �ٴβ�ѯ ���޸ĺ�Ľ������
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return backUserData;
		
		}

		return null;

	}

	// �鿴�û��Լ���Ϣ ��¼ʱ���û�����Ϣ����浽session�� ȡ������Ϊ������ѯ ���Ǹ�ƴ�ӵ�select ֱ�ӷ��ؼ��� selectUser
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
