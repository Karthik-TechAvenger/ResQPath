package com.src.model;

import java.util.ArrayList;
import java.util.List;

import com.src.anotations.Column;
import com.src.anotations.Table;

@Table(name = "users")
public class User {

	@Column(name = "id", type = "INT", primaryKey = true)
	private Integer id;

	@Column(name = "email", type = "VARCHAR(150)")
	private String email;

	@Column(name = "name", type = "VARCHAR(100)")
	private String name;

	@Column(name = "password", type = "VARCHAR(100)")
	private String password; // new

	@Column(name = "role", type = "VARCHAR(100)")
	private String role; // new: "ADMIN" or "USER"

	@Column(name = "x", type = "INT")
	private int x;

	@Column(name = "y", type = "INT")
	private int y;

	private List<QueryRecord> history = new ArrayList<>();

//    public User(String name, int x, int y) {
//        this.name = name;
//        this.x = x;
//        this.y = y;
//        this.role = "USER";   // default role
//    }
	public User(String name, int x, int y) {
		this(null, name, null, "USER", x, y);
	}

	public User() {
	}

	public User(String name, String password, String role, int x, int y) {
		this.name = name;
		this.password = password;
		this.role = role;
		this.x = x;
		this.y = y;
	}

	public User(Integer id, String name, String password, String role, int x, int y) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
		this.x = x;
		this.y = y;
	}

	public User(Integer id, String name, String password, String role, int x, int y, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
		this.x = x;
		this.y = y;
		this.email = email;
	}

	public User(String name, String password, String role, int x, int y, String email) {
		this(null, name, password, role, x, y, email);
	}

	// --- Getters and Setters ---

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void addQueryRecord(QueryRecord record) {
		history.add(record);
	}

	public void printHistory() {
		if (history.isEmpty()) {
			System.out.println(name + " has no query history.");
		} else {
			System.out.println("Query history for " + name + ":");
			for (QueryRecord record : history) {
				System.out.println("  " + record);
			}
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", role=" + role + ", x=" + x + ", y=" + y + "]";
	}
}
