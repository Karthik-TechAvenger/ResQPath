package com.src.service;

import java.util.ArrayList;

import com.src.model.Student;

public interface StudentServiceInterface {

	
	public int add(Student s);
	public int delete(Student s);	
	public int update(Student s);
	public Student display(int id);
	public ArrayList<Student> display();
	public int totalNoOfStudents();
}
