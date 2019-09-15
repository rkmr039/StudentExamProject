package com.jwt.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwt.controller.ExamController;
import com.jwt.model.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = Logger
			.getLogger(ExamController.class);

	public void addStudent(Student Student) {
		sessionFactory.getCurrentSession().saveOrUpdate(Student);

	}

	@SuppressWarnings("unchecked")
	public List<Student> getAllStudents() {

		return sessionFactory.getCurrentSession().createQuery("from Student")
				.list();
	}

	@Override
	public void deleteStudent(Integer rollNum) {
		Student student = (Student) sessionFactory.getCurrentSession().load(
				Student.class, rollNum);
		if (null != student) {
			this.sessionFactory.getCurrentSession().delete(student);
		}

	}

	public Student getStudent(int rollNum) {
		return (Student) sessionFactory.getCurrentSession().get(
				Student.class, rollNum);
	}
	
	@Override
	public Student updateStudent(Student student) {
		sessionFactory.getCurrentSession().update(student);
		return student;
	}

	@Override
	public Student getStudentById(int id) {
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : getStudentById("+id+") ");
		return (Student) sessionFactory.getCurrentSession().get(
				Student.class, id);
	}

	/*@Override
	public void updateFinalScore(int id, double finalScore) {
		
		
	}*/

}