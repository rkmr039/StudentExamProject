package com.jwt.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwt.controller.ExamController;
import com.jwt.model.Exam;
import com.jwt.model.Student;
import com.jwt.service.StudentService;

@Repository
public class ExamDAOImpl implements ExamDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = Logger
			.getLogger(ExamController.class);

	public void addExam(Exam exam) {
		sessionFactory.getCurrentSession().saveOrUpdate(exam);
		// updateStudentFinalScore(exam.getSid());
	}

	@SuppressWarnings("unchecked")
	public List<Exam> getAllExams() {

		return sessionFactory.getCurrentSession().createQuery("from Exam")
				.list();
	}

	@Override
	public void deleteExam(int eid, int sid) {
		Exam exam = (Exam) sessionFactory.getCurrentSession().load(
				Exam.class, eid);
		if (null != exam) {
			this.sessionFactory.getCurrentSession().delete(exam);
		}

	}

	public List<Exam> getExamsBySid(int sid) {
		Session s = sessionFactory.getCurrentSession();
		List<Exam> examList = new ArrayList<Exam>();
		Criteria cr = s.createCriteria(Exam.class);
		Criterion crId = Restrictions.eq("sid", sid);
		cr.add(crId);
		List list = cr.list();
		for(Object obj : list) {
			Exam exam = (Exam)obj;
			examList.add(exam);
		}
		return examList;
	}

	@Override
	public Exam updateExam(Exam exam) {
		sessionFactory.getCurrentSession().update(exam);
		
		return exam;
	}

	@Override
	public Exam getExamById(int eid) {
		return (Exam) sessionFactory.getCurrentSession().get(
				Exam.class, eid);
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

	@Override
	public Student getStudentById(int id) {
		logger.debug(new Date() +"  : "+ this.getClass() + " Method name : getStudentById("+id+") ");
		return (Student) sessionFactory.getCurrentSession().get(
				Student.class, id);
	}

	@Override
	public void updateStudentFinalScore(int sid) {
		double marks = getAvgMarks(sid) ;
		Student student = getStudentById(sid);
		student.setFinalScore(marks);
		logger.debug(new Date() +"  : "+ this.getClass() + " Method name : updateStudentFinalScore("+sid+") ");
		updateStudent(student);
		
	}

	@Override
	public void updateStudent(Student student) {
		logger.debug(new Date() +"  : "+ this.getClass() + " Method name : updateStudent("+student.getId()+") updating final marks");
		sessionFactory.getCurrentSession().update(student);
	}

	
}