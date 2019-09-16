package com.jwt.service;

import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.controller.ExamController;
import com.jwt.dao.StudentDAO;
import com.jwt.model.Student;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDao;
	
	private static final Logger logger = Logger
			.getLogger(ExamController.class);


	@Override
	@Transactional
	public void addStudent(Student student) {
		studentDao.addStudent(student);
	}

	@Override
	@Transactional
	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	@Override
	@Transactional
	public void deleteStudent(Integer rollNum) {
		studentDao.deleteStudent(rollNum);
	}

	public Student getStudent(int empid) {
		return studentDao.getStudent(empid);
	}

	public Student updateStudent(Student student) {
		student =  studentDao.updateStudent(student);
		logger.debug(new Date() +"  : "+ this.getClass() + "updating student================================");
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : updateStudent("+ student.getId() +")");
		return student;
	}

	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public Student getStudentById(int id) {
		
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : getStudentById("+ id +")");	
		return studentDao.getStudentById(id);
	}

	@Override
	public double getAvgMarks(int sid) {
		return studentDao.getAvgMarks(sid);
	}


}
