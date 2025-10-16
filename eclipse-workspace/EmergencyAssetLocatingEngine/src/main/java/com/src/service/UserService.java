package com.src.service;

import com.src.model.User;
import java.util.List;

public interface UserService {

	void registerUser(User user);

	User getUserByName(String username);

	User getUserById(int id);

	List<User> getAllUsers();

	void updateUserLocation(String username, int x, int y);

	void deleteUser(String username);

	void createDefaultAdminIfNotExists();

	User loginUser(String username, String password);

	boolean isEmailExists(String email);
}
