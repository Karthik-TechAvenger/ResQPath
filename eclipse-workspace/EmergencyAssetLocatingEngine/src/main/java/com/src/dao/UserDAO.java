package com.src.dao;

import java.util.List;

import com.src.model.User;

public interface UserDAO {
	void saveUser(User user);

	User getUserByName(String username);

	User getUserById(int id);

	List<User> getAllUsers();

	void updateUserLocation(String username, int x, int y);

	void deleteUser(String username);

	void createDefaultAdminIfNotExists();

	User findByNameAndPassword(String username, String password);

	boolean isEmailExists(String email);
}
