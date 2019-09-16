package com.jwt.service;

import java.util.List;

import com.jwt.model.Student;

public interface StudentService {
	
	public void addStudent(Student student);
	public List<Student> getAllStudents();
	public void deleteStudent(Integer rollNum);
	public Student getStudent(int rollNum);
	public Student updateStudent(Student student);
	public Student getStudentById(int id);
	double getAvgMarks(int sid);

}
