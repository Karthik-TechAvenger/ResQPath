package com.src.service.impl;

import com.src.dao.UserDAO;
import com.src.daoImpl.UserDAOImpl;
import com.src.model.User;
import com.src.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

	private final UserDAO userDAO;

	// Constructor injection for DAO
	public UserServiceImpl() {
		this.userDAO = new UserDAOImpl();
	}

	@Override
	public void registerUser(User user) {
		if (!userDAO.isEmailExists(user.getEmail())) {
			userDAO.saveUser(user);
		} else {
			throw new IllegalArgumentException("Email already exists: " + user.getEmail());
		}
	}

	@Override
	public User getUserByName(String username) {
		return userDAO.getUserByName(username);
	}

	@Override
	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public void updateUserLocation(String username, int x, int y) {
		userDAO.updateUserLocation(username, x, y);
	}

	@Override
	public void deleteUser(String username) {
		userDAO.deleteUser(username);
	}

	@Override
	public void createDefaultAdminIfNotExists() {
		userDAO.createDefaultAdminIfNotExists();
	}

	@Override
	public User loginUser(String username, String password) {
		return userDAO.findByNameAndPassword(username, password);
	}

	@Override
	public boolean isEmailExists(String email) {
		return userDAO.isEmailExists(email);
	}
}
