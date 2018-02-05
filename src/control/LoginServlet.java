package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.Tool;

import model.Usermodel;
import service.AdminService;
import service.UserService;
import util.Tools;

/**
 * Servlet implementation class loginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}





	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String action="";
		if(Tools.isnull(request.getParameter("action"))) {
			return;
		}else {
			action=request.getParameter("action");
		}
		if("userlogin".equals(action)) {
			try {
				userLogin(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		if("adminlogin".equals(action)) {
			System.out.println("5555444");
			adminLogin(request, response);
			return;
		}
		if("register".equals(action)) {
			userRegister(request, response);
		}
		if("adminlogout".equals(action)) {
			this.adminlogout(request, response);
		}
		if("logout".equals(action)) {
			this.logout(request, response);
			
		}
		
		
		
	}





	private void logout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.getSession().removeAttribute("user");
	}





	private void adminlogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.getSession().removeAttribute("admin");
	}





	private void userRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String yanz="";
		if(Tools.isnull(request.getParameter("yanz"))) {
			 request.getRequestDispatcher("index.jsp").forward(request, response);
	 		 return;
		}else {
			yanz=request.getParameter("yanz");
		}
		if (!yanz.equals(request.getSession().getAttribute("authCode"))) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		String email="";
		if(Tools.isnull(request.getParameter("username"))) {
			return;
		}else {
			email=request.getParameter("username");
		}
		String pass="";
		if(Tools.isnull(request.getParameter("userpass"))) {
			return;
		}else {
			pass=request.getParameter("userpass");
		}
		String name="";
		if(Tools.isnull(request.getParameter("name"))) {
			return;
		}else {
			name=request.getParameter("name");
		}
		String sexh="";
		if(Tools.isnull(request.getParameter("sex"))) {
			return;
		}else {
			sexh=request.getParameter("sex");
		}
		String descr="";
		if(Tools.isnull(request.getParameter("descr"))) {
			descr="";//后面别忘改判断
		}else {
			descr=request.getParameter("descr");
		}
		String birth="";
		if(Tools.isnull(request.getParameter("birth"))) {
			birth="";//后面别忘改判断
		}else {
	      birth=request.getParameter("birth");
		}
		Integer sex=Integer.parseInt(sexh);
		Usermodel userRegister=new Usermodel();
		userRegister.setEmail(email);
		userRegister.setUserpass(pass);
		userRegister.setName(name);
		userRegister.setSex(sex);
		userRegister.setDescr(descr);
		userRegister.setBirthdate(birth);
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
		Date da=new Date();
		String ctime=df.format(da);
		System.out.println(ctime);
		userRegister.setCreatetime(ctime);
		UserService us=new UserService();
		Boolean isRe = us.isRegisterSucceed(userRegister);
		if(isRe==null||isRe==false) {//判断是否为空
			return;
		}
		boolean isAdded = us.isaddsucceed(userRegister);
		if(isAdded) {//成功
			PrintWriter out = response.getWriter();
			out.print("1");
		}else {
			PrintWriter out = response.getWriter();
			out.print("0");
		}
		
	}





	private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String yanz="";
		if(Tools.isnull(request.getParameter("yanz"))) {
			 return;
		}else {
			yanz=request.getParameter("yanz");
		}
		if (!yanz.equals(request.getSession().getAttribute("authCode"))) {
			out.print("1");//验证码错误
			return;
		}
	
		String email="";
		if(Tools.isnull(request.getParameter("admname"))) {
			return;
		}
		else {
			email=request.getParameter("admname");
		}
		String pass="";
		if(Tools.isnull(request.getParameter("admpass"))) {
			return;
		}else {
			pass=request.getParameter("admpass");
		}
		Usermodel adminlogin=new Usermodel();
		adminlogin.setEmail(email);
		adminlogin.setUserpass(pass);
		AdminService as=new AdminService();
		int flag = as.isadminLoginSucceed(adminlogin);
		if(flag==1) {// 账号或密码错误
			out.print("2");
			return;
		}
        if(flag==2) {// 账号已删除
        	out.print("-1");
        	return;
		}
        if(flag==3) {// 账号已失效
        	out.print("3");
        	return;
         }
        if(flag==4) {// 账号成功登录
        	
        	
			out.print("0");
			request.getSession().setAttribute("admin", email);//登录成功 记录用户账号
			return;
        }
		
	}





	private void userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//验证码............
		PrintWriter out = response.getWriter();
		String yanz="";
		if(Tools.isnull(request.getParameter("yanz"))) {
			 request.getRequestDispatcher("index.jsp").forward(request, response);
	 		 return;
		}else {
			yanz=request.getParameter("yanz");
		}
		if (!yanz.equals(request.getSession().getAttribute("authCode"))) {

			out.print("0");
			return;
		}
		
		String email="";
		if(!Tools.isnull(request.getParameter("username"))) {
			email=request.getParameter("username");
		}else {
			
		}
		String pass="";
		if(!Tools.isnull(request.getParameter("userpass"))) {
			pass=request.getParameter("userpass");
		}
		Usermodel loginUser=new Usermodel();
		loginUser.setEmail(email);
		loginUser.setUserpass(pass);
		UserService Us=new UserService();
		int flag = Us.isLoginSucceed(loginUser);
		
	
		if(flag==2) {//账号已删除
			out.print("2");
			
			return;
		}
		if(flag==3) {//账号失效
			out.print("3");
			return;
		}
		if(flag==-1) {//账号用户名或密码错误
			out.print("4");
			return;
		}
		if(flag==4) {
			out.print("5");
			return;
		}
		if(flag==1) {//登录成功
			request.getSession().setAttribute("user", email);//登录成功 记录用户账号
			out.print("1");
			return;
		}
		
	}

}
