package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AdminFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String admin = (String) ((HttpServletRequest) request).getSession().getAttribute("admin");// session范围广,关闭浏览器之前
		if (admin == null || "".equals(admin)) {

			request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
		}
		chain.doFilter(request, response);
	}

}
