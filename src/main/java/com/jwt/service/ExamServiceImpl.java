package com.jwt.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.controller.ExamController;
import com.jwt.dao.ExamDAO;
import com.jwt.model.Exam;
import com.jwt.model.Student;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

	private static final Logger logger = Logger
			.getLogger(ExamController.class);
	
	
	@Autowired
	private ExamDAO examDao;


	@Override
	@Transactional
	public void addExam(Exam exam) {
		examDao.addExam(exam);
		updateStudentFinalScore(exam.getSid());
	}

	@Override
	@Transactional
	public List<Exam> getAllExams() {
		return examDao.getAllExams();
	}

	@Override
	@Transactional
	public void deleteExam(int eid, int sid) {
		examDao.deleteExam(eid,sid);
		updateStudentFinalScore(sid);
	}

	public List<Exam> getExamsBySid(int sid) {
		return examDao.getExamsBySid(sid);
	}

	public Exam updateExam(Exam exam) {
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : updateExam()");
		logger.debug(new Date() +"  : "+ this.getClass() + " ExamId "+ exam.getEid()+" SID "+ exam.getSid()+" subject "+ exam.getSubject());
		exam = examDao.updateExam(exam);
		updateStudentFinalScore(exam.getSid());
		return exam;
	}

	public void setExamDao(ExamDAO examDao) {
		this.examDao = examDao;
	}

	@Override
	public Exam getExamById(int eid) {
		return examDao.getExamById(eid);
	}

	@Override
	public void updateStudentFinalScore(int sid) {
		// get final score for student 
		double avgMarks = examDao.getAvgMarks(sid);
		logger.debug(new Date() +"  : "+ this.getClass() + " Method name : updateStudentFinalScore() getAvgMarks("+sid+") = " + avgMarks);
		logger.debug(new Date() +"  : "+ this.getClass() + " Method name : updateStudentFinalScore() fetching student form db");
		Student student = examDao.getStudentById(sid);
		student.setFinalScore(avgMarks);
		logger.debug(new Date() +"  : "+ this.getClass() + " Method name : updateStudentFinalScore("+sid+") calling updateStudent()");
		examDao.updateStudent(student);
		// update final score
		logger.debug(new Date() +"  : "+ this.getClass() + " Method name : updateStudentFinalScore("+ sid+")  student finalScore updated");
		
	}

}
