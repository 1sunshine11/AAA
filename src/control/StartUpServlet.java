package control;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Constant;

/**
 * Servlet implementation class StartUpServlet
 */

public class StartUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		String name = "jdbc";
		ResourceBundle rb = ResourceBundle.getBundle(name);
		Properties p = new Properties();
		Enumeration<String> e = rb.getKeys();
		while (e.hasMoreElements()) {
			String o = e.nextElement();
			p.put(o, rb.getObject(o));
		}
		Constant.DRIVERCLASS = p.getProperty("driverClass");
		Constant.URL = p.getProperty("url");
		Constant.USERNAME = p.getProperty("username");
		Constant.PASSWORD = p.getProperty("password");
		String propName = "prop";
		ResourceBundle pr = ResourceBundle.getBundle(propName);
		Properties pp = new Properties();
		Enumeration<String> pe = pr.getKeys();
		while (pe.hasMoreElements()) {
			String o = pe.nextElement();
			pp.put(o, pr.getObject(o));
		}
		Constant.SEX_Man=Integer.parseInt(pp.getProperty("SEX_Man"));
		Constant.SEX_Woman=Integer.parseInt(pp.getProperty("SEX_Woman"));
		Constant.ADMIN_Y=Integer.parseInt(pp.getProperty("ADMIN_Y"));
		Constant.ADMIN_N=Integer.parseInt(pp.getProperty("ADMIN_N"));
		Constant.DELETE_Y=Integer.parseInt(pp.getProperty("DELETE_Y"));
		Constant.DELETE_N=Integer.parseInt(pp.getProperty("DELETE_N"));
		Constant.EFFECT_Y=Integer.parseInt(pp.getProperty("EFFECT_Y"));
		Constant.EFFECT_N=Integer.parseInt(pp.getProperty("EFFECT_N"));
		
	}
}
