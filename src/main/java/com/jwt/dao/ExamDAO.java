package com.jwt.dao;

import java.util.List;
import com.jwt.model.Exam;
import com.jwt.model.Student;

public interface ExamDAO {

	public void addExam(Exam exam);
	public List<Exam> getAllExams();
	public void deleteExam(int eid, int sid);
	public Exam getExamById(int eid);
	public Exam updateExam(Exam exam);
	public List<Exam> getExamsBySid(int sid);
	public double getAvgMarks(int sid);
	public Student getStudentById(int id);
	public void updateStudentFinalScore(int id);
	public void updateStudent(Student student);
	
	
}
