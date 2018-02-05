package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.Usermodel;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class AdminServlet
 */

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
	    String user=(String) request.getSession().getAttribute("admin");//获得管理员账户信息
	    
	   Usermodel um=new Usermodel();
	   um.setEmail(user);
	   UserDao sa=new UserDao();
	   Usermodel list = sa.SelectAdmined(um);
	   
	     ArrayList<Usermodel> List =new ArrayList<Usermodel>();
	    JSONArray json = JSONArray.fromObject(list);
	    System.out.println(json.toString());
        out.print(json.toString());
        out.flush();
        out.close();
	}

}
