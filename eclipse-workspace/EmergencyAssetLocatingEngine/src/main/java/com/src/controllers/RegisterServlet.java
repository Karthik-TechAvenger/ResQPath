package com.src.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.daoImpl.UserDAOImpl;
import com.src.model.User;
import com.src.service.UserService;
import com.src.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private UserService userDAO = new UserServiceImpl();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		String email = request.getParameter("email");

		if (uname == null || pwd == null || email == null) {
			request.setAttribute("error", "All fields required");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}

//		User existing = userDAO.getUserByName(uname);
//		if (existing != null) {
//			request.setAttribute("error", "Username already exists");
//			request.getRequestDispatcher("register.jsp").forward(request, response);
//			return;
//		}

		if (userDAO.isEmailExists(email)) {
			request.setAttribute("error", "Email already registered, please use another one.");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}

		User newUser = new User(uname, pwd, "USER", 0, 0, email);
		userDAO.registerUser(newUser);
		request.setAttribute("createdId", newUser.getId());
		request.getRequestDispatcher("registerSuccess.jsp").forward(request, response);

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
