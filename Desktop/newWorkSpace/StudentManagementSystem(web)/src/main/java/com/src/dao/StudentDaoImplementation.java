package com.src.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.src.model.Student;

public class StudentDaoImplementation implements StudentDaoInterface {
	Statement st;
	@Override
	public int add(Student s) {	
		st=StudentDaoInterface.getMyStatement();		
		try {
			return st.executeUpdate("insert into student values("+s.getId()+",'"+
		s.getName()+"',"+s.getMobileno()+",'"+s.getEmailid()+
		"',"+s.getMarks1()+","+s.getMarks2()+","+s.getMarks3()+
		","+s.getTotalmarkssecured()+","+s.getPercentage()+",'"+s.getGrade()+"')");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(Student s) {
		st=StudentDaoInterface.getMyStatement();		
		try {
			return st.executeUpdate("delete from student where id ="+s.getId());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Student s) {
		st=StudentDaoInterface.getMyStatement();		
		try {
			return st.executeUpdate("update student set name='"+
		s.getName()+"',mobileno = "+s.getMobileno()+",emailid = '"+s.getEmailid()+
		"',marks1 = "+s.getMarks1()+",marks2 = "+s.getMarks2()+",marks3 = "+s.getMarks3()+
		",totalmarkssecured="+s.getTotalmarkssecured()+",percentage = "+s.getPercentage()+",grade = '"+s.getGrade()+"' where id = "+s.getId());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Student display(int id) {
		st=StudentDaoInterface.getMyStatement();		
		try {
			ResultSet rs = st.executeQuery("select * from student where id ="+id);
			Student s =null;
			if(rs.next())
			{
				
		s= new Student(rs.getInt("id"),rs.getString("name"),
					    rs.getLong("mobileno"),
					    rs.getString("emailid"),
					    rs.getInt("marks1") ,
					    rs.getInt("marks2") ,
					    rs.getInt("marks3") ,
					    rs.getInt("totalmarkssecured") ,
					    rs.getDouble("percentage") ,
					    rs.getString("grade").charAt(0));
			return s;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<Student> display() {
		st=StudentDaoInterface.getMyStatement();
		
		ArrayList<Student> stds = new ArrayList();
		Student s =null;
		try {
			ResultSet rs = st.executeQuery("select * from student");
			while(rs.next())
			{
				s= new Student(rs.getInt("id"),rs.getString("name"),
					    rs.getLong("mobileno"),
					    rs.getString("emailid"),
					    rs.getInt("marks1") ,
					    rs.getInt("marks2") ,
					    rs.getInt("marks3") ,
					    rs.getInt("totalmarkssecured") ,
					    rs.getDouble("percentage") ,
					    rs.getString("grade").charAt(0));
				stds.add(s);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return stds;
	}

	@Override
	public int totalNoOfStudents() {
		st=StudentDaoInterface.getMyStatement();	
		int count=0;
		try {
			ResultSet rs = st.executeQuery("select count(*) from student");
			if(rs.next())
			{
				count = rs.getInt(1);				
			}
			return count;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}

}
