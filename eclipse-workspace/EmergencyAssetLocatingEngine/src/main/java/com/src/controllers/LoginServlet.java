package com.src.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.src.model.User;
import com.src.service.UserService;
import com.src.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userDAO = new UserServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");

		User user = userDAO.getUserByName(uname);

		if (user == null) {
			request.setAttribute("error", "Username not found.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		if (!BCrypt.checkpw(pwd, user.getPassword())) {
			request.setAttribute("error", "Invalid password.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		HttpSession session = request.getSession();
		session.setAttribute("successMsg", "Login Successful!");
		session.setAttribute("user", user);
		if (user.getRole().equalsIgnoreCase("ADMIN")) {
			response.sendRedirect("adminDashboard.jsp");
		} else {
			response.sendRedirect("UserDashboard.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
