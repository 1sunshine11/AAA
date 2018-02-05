package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

import model.Usermodel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.AdminService;
import service.UserService;
import util.Tools;

/**
 * Servlet implementation class UserServlet
 */

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
	private AdminService as = new AdminService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = "";
		if (Tools.isnull(request.getParameter("action"))) {
			return;
		} else {
			action = request.getParameter("action");
		}
		if ("showUser".equals(action)) {// �û���¼��ʾ�û���Ϣ
			showUser(request, response);
		}
		if ("showAllUser".equals(action)) {// ����Ա��¼��ʾ�����û���Ϣ �� ������ѯ�û���Ϣ
			showAllUser(request, response);
		}
		if ("userUpdate".equals(action)) {
			userUpdate(request, response);
		}
		if ("userDelete".equals(action)) {
			userDelete(request, response);
		}

	}

	private void userDelete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String idd = request.getParameter("id");
		if (Tools.isnull(idd)) {
			return;
		}
		int id = Integer.parseInt(idd);
		Usermodel userDelete = new Usermodel();
		userDelete.setId(id);
		UserDao dao = new UserDao();
		dao.DeleteUser(userDelete);// ȱservice

	}

	private void userUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		// һ�����Ӻ����ж�!!!!!!!!!!!
		String idd = request.getParameter("id");
		int id = Integer.parseInt(idd);
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String sexx = request.getParameter("sex");
		int sex = Integer.parseInt(sexx);
		String descr = request.getParameter("descr");
		String birthdate = request.getParameter("birthdate");
		String createtime = request.getParameter("createtime");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ��ʽ��ʱ��
		Date da = new Date();
		String updatetime = df.format(da);

		String isdeletee = request.getParameter("isdelete");
		int isdelete = Integer.parseInt(isdeletee);
		String iseffectt = request.getParameter("iseffect");
		int iseffect = Integer.parseInt(iseffectt);
		Integer isadmin = null;
		if (Tools.isnull(request.getParameter("isadmin"))) {
			isadmin = 0;// ����ǿ����û����޸ĵ� ��Ӧ�������м�¼����ʾ��ҳ����Ȼ�����أ��Ͳ����������ֵ�ˣ�
		} else {
			isadmin = Integer.parseInt(request.getParameter("isadmin"));
		}

		Usermodel userUpdate = new Usermodel();
		userUpdate.setId(id);
		userUpdate.setEmail(email);
		userUpdate.setName(name);
		userUpdate.setSex(sex);
		userUpdate.setDescr(descr);
		userUpdate.setBirthdate(birthdate);
		userUpdate.setCreatetime(createtime);
		userUpdate.setUpdatetime(updatetime);
		userUpdate.setIsdelete(isdelete);
		userUpdate.setIseffect(iseffect);
		userUpdate.setIsadmin(isadmin);
		List<Usermodel> data = us.updateUser(userUpdate);
		JSONArray json = JSONArray.fromObject(data);
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();

	}

	private void showAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException {// ��ѯ����ʾ
		// TODO Auto-generated method stub
		String name = "";
		if (request.getParameter("name") == null) {
			name = "";
		} else {
			name = request.getParameter("name");
		}

		String email = "";
		if (request.getParameter("email") == null) {
			email = "";
		} else {
			email = request.getParameter("email");
		}

		Integer sex = null;
		if (request.getParameter("sex") == null || "".equals(request.getParameter("sex"))) {
			sex = null;
		} else {
			sex = Integer.parseInt(request.getParameter("sex"));

		}
		Integer isdelete = null;
		if (request.getParameter("isdelete") == null || "".equals(request.getParameter("isdelete"))) {
			isdelete = null;
		} else {
			isdelete = Integer.parseInt(request.getParameter("isdelete"));
		}
		Integer iseffect = null;
		if (request.getParameter("iseffect") == null || "".equals(request.getParameter("iseffect"))) {
			iseffect = null;
		} else {
			iseffect = Integer.parseInt(request.getParameter("iseffect"));
		}
		String createtime = "";
		if (request.getParameter("createtime") == null) {
			createtime = "";
		} else {
			createtime = request.getParameter("createtime");
		}
		String updatetime = "";
		if (request.getParameter("updatetime") == null) {
			updatetime = "";
		} else {
			updatetime = request.getParameter("updatetime");
		}
		String descr = "";
		if (Tools.isnull(request.getParameter("descr"))) {
			descr = "";
		} else {
			descr = request.getParameter("descr");
		}
		Integer isadmin = null;
		if (Tools.isnull(request.getParameter("isadmin"))) {
			isadmin = null;
		} else {
			isadmin = Integer.parseInt(request.getParameter("isadmin"));
		}
		String BirthStart = request.getParameter("BirthStart");
		String BirthEnd = request.getParameter("BirthEnd");
		String CreatetimeStart = request.getParameter("CreatetimeStart");
		String CreatetimeEnd = request.getParameter("CreatetimeEnd");
		String UpdatetimeStart = request.getParameter("UpdatetimeStart");
		String UpdatetimeEnd = request.getParameter("UpdatetimeEnd");
		String pageNumber = request.getParameter("page");
		String pageSize = request.getParameter("rows");

		Usermodel showAllUser = new Usermodel();
		showAllUser.setName(name);
		showAllUser.setEmail(email);
		showAllUser.setSex(sex);
		showAllUser.setIsdelete(isdelete);
		showAllUser.setIseffect(iseffect);
		showAllUser.setCreatetime(createtime);
		showAllUser.setUpdatetime(updatetime);
		showAllUser.setDescr(descr);
		showAllUser.setIsadmin(isadmin);
		showAllUser.setBirthStart(BirthStart);
		showAllUser.setBirthEnd(BirthEnd);
		showAllUser.setCreatetimeStart(CreatetimeStart);
		showAllUser.setCreatetimeEnd(CreatetimeEnd);
		showAllUser.setUpdatetimeStart(UpdatetimeStart);
		showAllUser.setUpdatetimeEnd(UpdatetimeEnd);
		showAllUser.setPageNumber(Integer.parseInt(pageNumber));
		showAllUser.setPageSize(Integer.parseInt(pageSize));
		List<Usermodel> showAllUserList = as.selectAllUserSucceed(showAllUser);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
		UserDao ud = new UserDao();
		Integer count = ud.selectRows();
		System.out.println(count);
		jsonMap.put("total", count);// select count
		jsonMap.put("rows", showAllUserList);
		JSONObject result = JSONObject.fromObject(jsonMap);
		PrintWriter out = response.getWriter();
		out.print(result.toString());
		out.flush();
		out.close();

	}

	private void showUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		String user = "";
		if (Tools.isnull((String) request.getSession().getAttribute("user"))) {
			return;

		} else {
			user = (String) request.getSession().getAttribute("user");
		}
		Usermodel showUser = new Usermodel();
		showUser.setEmail(user);
		List<Usermodel> showUserList = us.selectUserSucceed(showUser);
		PrintWriter out = response.getWriter();
		JSONArray json = JSONArray.fromObject(showUserList);
		out.print(json.toString());
		out.flush();
		out.close();
	}

}
