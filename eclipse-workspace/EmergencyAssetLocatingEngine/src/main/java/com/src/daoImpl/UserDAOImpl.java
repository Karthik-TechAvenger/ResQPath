package com.src.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.src.dao.UserDAO;
import com.src.model.User;
import com.src.util.DBUtil;

public class UserDAOImpl implements UserDAO {

	// saveUser -> include email, return generated id (optional)
	@Override
	public void saveUser(User user) {
		String sql = "INSERT INTO users (name, password, role, x, y, email) VALUES (?,?,?,?,?,?)";
		try (Connection con = DBUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRole());
			ps.setInt(4, user.getX());
			ps.setInt(5, user.getY());
			ps.setString(6, user.getEmail());

			ps.executeUpdate();
			try (ResultSet keys = ps.getGeneratedKeys()) {
				if (keys.next()) {
					int id = keys.getInt(1);
					user.setId(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUserByName(String username) {
		String sql = "SELECT * FROM users WHERE name = ?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"),
							rs.getString("role"), rs.getInt("x"), rs.getInt("y"), rs.getString("email"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserById(int id) {
		String sql = "SELECT * FROM users WHERE id=?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("role"),
						rs.getInt("x"), rs.getInt("y"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM users";

		try (Connection con = DBUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"),
						rs.getString("role"), rs.getInt("x"), rs.getInt("y")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void updateUserLocation(String username, int x, int y) {
		String sql = "UPDATE users SET x=?, y=? WHERE name=?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, x);
			ps.setInt(2, y);
			ps.setString(3, username);

			ps.executeUpdate();
			System.out.println("Location updated for " + username);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(String username) {
		String sql = "DELETE FROM users WHERE name=?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.executeUpdate();
			System.out.println("User " + username + " deleted.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createDefaultAdminIfNotExists() {
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE role = 'ADMIN'")) {

			ResultSet rs = ps.executeQuery();
			if (!rs.next()) { // no admin exists
				try (PreparedStatement insert = conn
						.prepareStatement("INSERT INTO users (name, password, role, x, y) VALUES (?, ?, ?, ?, ?)")) {
					String defaultPassword = "admin123";
					// Hash the password before saving
					String hashedPassword = BCrypt.hashpw(defaultPassword, BCrypt.gensalt(12));
					insert.setString(1, "admin");
					insert.setString(2, hashedPassword);
					insert.setString(3, "ADMIN");
					insert.setInt(4, 0);
					insert.setInt(5, 0);
					insert.executeUpdate();
					System.out.println("Default admin created: admin/admin123");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User findByNameAndPassword(String username, String password) {
		String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"),
							rs.getString("role"), rs.getInt("x"), rs.getInt("y"), rs.getString("email"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isEmailExists(String email) {
		String sql = "SELECT 1 FROM users WHERE email = ?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			return rs.next(); // returns true if email exists
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
