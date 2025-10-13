package com.src.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.src.model.Student;

import oracle.jdbc.driver.OracleDriver;

public interface StudentDaoInterface {

	
	public static Statement getMyStatement()
	{
		Driver drive = new OracleDriver();
		String url = "jdbc:oracle:thin:@//localhost:1521/xe";
		String username = "system";
		String password = "root";
		Statement st=null;			
		try {
			DriverManager.registerDriver(drive);
			Connection con = DriverManager.getConnection(url, username, password);
			st = con.createStatement();
			return st;					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;		
	}	
	public int add(Student s);
	public int delete(Student s);	
	public int update(Student s);
	public Student display(int id);
	public ArrayList<Student> display();
	public int totalNoOfStudents();
}
