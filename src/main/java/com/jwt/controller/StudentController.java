package com.jwt.controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.jwt.model.Student;
import com.jwt.service.StudentService;

@Controller
public class StudentController {

	private static final Logger logger = Logger
			.getLogger(StudentController.class);

	public StudentController() {
		logger.info("Testxyz===================================================");
		System.out.println("StudentController()");
	}

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/")
	public ModelAndView listStudent(ModelAndView model) throws IOException {
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : listStudent()" + "  entering into method.............");
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : listStudent()" + " calling student service...");
		List<Student> listStudent = studentService.getAllStudents();
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : listStudent()" + " getAllStudent() successfuly executed ...no of student received " +  listStudent.size());
		model.addObject("listStudent", listStudent);
		model.setViewName("home");
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : listStudent()" + " redirecting to home ...");
		return model;
	}

	@RequestMapping(value = "/newStudent", method = RequestMethod.GET)
	public ModelAndView newStudent(ModelAndView model) {
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : newStudent()" + "  entering into method.............");
		Student student = new Student();
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : newStudent()" + " creating Student object...");
		model.addObject("student", student);
		model.setViewName("StudentForm");
		logger.debug(new Date() +" Class : "+ this.getClass() + "Method name : newStudent()" + " redirecting to StudentForm.jsp...");
		return model;
	}

	@RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
	public ModelAndView saveStudent(@ModelAttribute Student student) {
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : saveStudent()" + " entering into method............");
		if(student.getFinalScore() == 0.0){ 
			student.setFinalScore(0.0);
			logger.debug(new Date() +"  : "+ this.getClass() + "Method name : addStudent()" + " creating new student...");
			studentService.addStudent(student);
			logger.debug(new Date() +"  : "+ this.getClass() + "Method name : addStudent()" + " new student created...");
		}else {
			logger.debug(new Date() +" Class : "+ this.getClass() + "Method name : updateStudent()" + " updating old student...");
			// System.out.println("==============AVGMARKS=============== : " + studentService.getAvgMarks(student.getId()));
			student.setFinalScore(studentService.getAvgMarks(student.getId()));
			logger.debug(new Date() +" Class : "+ this.getClass() + "Method name : saveStudent()" + " avemarks" + studentService.getAvgMarks(student.getId()));
			studentService.updateStudent(student);
		}
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : newStudent()" + " redirecting to home ...");
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteStudent", method = RequestMethod.GET)
	public ModelAndView deleteStudent(HttpServletRequest request) {
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : deleteStudent()" + " entering into method.............");
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : newStudent()" + " fetching student id from request ...");
		int studentId = Integer.parseInt(request.getParameter("id"));
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : newStudent()" + " deleting student ..."+ request.getParameter("id"));
		studentService.deleteStudent(studentId);
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : newStudent()" + " redirecting to home ...");
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editStudent", method = RequestMethod.GET)
	public ModelAndView editStudent(HttpServletRequest request) {
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : editStudent()" + " entering into method.............");
		int studentId = Integer.parseInt(request.getParameter("id"));
		Student student = studentService.getStudent(studentId);
		logger.debug(new Date() +"  : "+ this.getClass() + "Method name : editStudent()" + " redirecting to StudentForm.............");
		ModelAndView model = new ModelAndView("StudentForm");
		model.addObject("student", student);

		return model;
	}

}