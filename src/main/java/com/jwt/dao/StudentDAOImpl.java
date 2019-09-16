package com.jwt.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwt.controller.ExamController;
import com.jwt.model.Exam;
import com.jwt.model.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = Logger
			.getLogger(ExamController.class);

	public void addStudent(Student Student) {
		sessionFactory.getCurrentSession().save(Student);

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
	
	@Override
	public double getAvgMarks(int sid) {
		// select avg(mark) from exam where sid = sid;
		logger.debug(new Date() +"  : "+ this.getClass() + " Method name : getAvgMarks("+ sid+") sid  " + sid);
		Criteria cr =  sessionFactory.getCurrentSession().createCriteria(Exam.class);
		Projection p = Projections.avg("mark");
		cr.setProjection(p);
		Criterion crId = Restrictions.eq("sid", sid);
		cr.add(crId);
		double avgMarks = (double)cr.uniqueResult();
		logger.debug(new Date() +"  : "+ this.getClass() + " Method name : getAvgMarks("+ sid+") finalScore is  " + avgMarks);
		return avgMarks;
	}

}