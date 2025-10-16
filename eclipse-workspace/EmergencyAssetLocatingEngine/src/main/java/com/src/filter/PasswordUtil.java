package com.src.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet Filter implementation class PasswordUtil
 */
@WebFilter("/register")
public class PasswordUtil extends HttpFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public PasswordUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String pwd = req.getParameter("pwd");

		if (pwd == null) {
			req.setAttribute("error", "Password is required.");
			req.getRequestDispatcher("register.jsp").forward(req, res);
			return;
		}

		if (!isValidPassword(pwd)) {
			req.setAttribute("error",
					"Password must be at least 8 characters long, contain one uppercase letter and one number.");
			req.getRequestDispatcher("register.jsp").forward(req, res);
			return;
		}

		String hashedPassword = hashPassword(pwd);

		// pass the request along the filter chain
		chain.doFilter(new PasswordRequestWrapper(req, hashedPassword), res);
	}

	private boolean isValidPassword(String password) {
		if (password.length() < 8)
			return false;
		if (!password.matches(".*[A-Z].*"))
			return false;
		if (!password.matches(".*[0-9].*"))
			return false;
		return true;
	}

	private String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

}
