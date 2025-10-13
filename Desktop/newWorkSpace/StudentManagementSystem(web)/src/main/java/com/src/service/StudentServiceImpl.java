package com.src.service;

import java.util.ArrayList;

import com.src.dao.StudentDaoImplementation;
import com.src.dao.StudentDaoInterface;
import com.src.model.Student;

public class StudentServiceImpl implements StudentServiceInterface {

	StudentDaoInterface sdao = new StudentDaoImplementation();
	@Override
	public int add(Student s) {	
		
		return sdao.add(s);
	}

	@Override
	public int delete(Student s) {
		
		return sdao.delete(s);
	}

	@Override
	public int update(Student s) {	
		
		return sdao.update(s);
	}

	@Override
	public Student display(int id) {		
		return sdao.display(id);
	}

	@Override
	public ArrayList<Student> display() {		
		return sdao.display();
	}

	@Override
	public int totalNoOfStudents() {
		// TODO Auto-generated method stub
		return sdao.totalNoOfStudents();
	}

}
