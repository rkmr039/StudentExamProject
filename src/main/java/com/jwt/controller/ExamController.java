package com.jwt.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.jwt.model.Exam;
import com.jwt.service.ExamService;

@Controller
@RequestMapping("/exam")
public class ExamController {

	private static final Logger logger = Logger
			.getLogger(ExamController.class);

	public ExamController() {
		System.out.println("ExamController()");
	}

	@Autowired
	private ExamService examService;

	@RequestMapping(value = "/showExam")
	public ModelAndView listExam(HttpServletRequest request,ModelAndView model) throws IOException {
		int sid = Integer.parseInt(request.getParameter("sid"));
		// System.out.println("SID : " + sid);
		logger.debug(new Date() +"  "+ this.getClass() + " Method name : listExam()" + " entering into method...");
		logger.debug(new Date() +"  "+ this.getClass() + " Method name : listExam()" + " calling exam service...");
		List<Exam> examList = examService.getExamsBySid(sid);
		model.addObject("sname",request.getParameter("sname"));
		/*for (Exam exam : examList) {
			System.out.println(exam.getEid());
		}*/
		logger.debug(new Date() +"  "+ this.getClass() + " Method name : listExam()" + " getAllExam() successfuly executed ...no of exam received " +  examList.size());
		model.addObject("examList", examList);
		model.addObject("sid",Integer.parseInt(request.getParameter("sid")));
		model.setViewName("ShowExams");
		logger.debug(new Date() +"  "+ this.getClass() + " Method name : listExam()" + " redirecting to home ...");
		return model;
	}

	@RequestMapping(value = "/newExam", method = RequestMethod.GET)
	public ModelAndView newExam(HttpServletRequest request,ModelAndView model) {
		logger.debug(new Date() +"  "+ this.getClass() + "Method name : newExam()" + " entering into method...");
		Exam exam = new Exam();
		exam.setSid(Integer.parseInt(request.getParameter("sid")));
		logger.debug(new Date() +"  "+ this.getClass() + "Method name : newExam()" + " creating Exam object...");
		model.addObject("exam", exam);
		model.setViewName("ExamForm");
		logger.debug(new Date() +" Class : "+ this.getClass() + "Method name : newExam()" + " redirecting to ExamForm.jsp...");
		return model;
	}

	@RequestMapping(value = "/saveExam", method = RequestMethod.POST)
	public ModelAndView saveExam(@ModelAttribute Exam exam) {
		logger.debug(new Date() +" Class : "+ this.getClass() + " Method name : saveExam()" + " entering into method...");
		try{
			logger.debug(new Date() +" Class : "+ this.getClass() + " Method name : saveExam()" + " creating new exam...");
			examService.addExam(exam);
		}catch(DuplicateKeyException | ConstraintViolationException pke){
			logger.debug(new Date() +" Class : "+ this.getClass() + " Method name : saveExam()" + " updating old exam...");
			examService.updateExam(exam);
		}
		
		logger.debug(new Date() +" Class : "+ this.getClass() + " Method name : saveExam()" + " redirecting to home ...");
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteExam", method = RequestMethod.GET)
	public ModelAndView deleteExam(HttpServletRequest request) {
		logger.debug(new Date() +" Class : "+ this.getClass() + " Method name : deleteExam()" + " entering into method...");
		logger.debug(new Date() +" Class : "+ this.getClass() + " Method name : deleteExam()" + " fetching exam id from request ...");
		int examId = Integer.parseInt(request.getParameter("eid"));
		int sId = Integer.parseInt(request.getParameter("sid"));
		logger.debug(new Date() +" Class : "+ this.getClass() + " Method name : deleteExam()" + " deleting exam ..."+ request.getParameter("id"));
		examService.deleteExam(examId,sId);
		logger.debug(new Date() +" Class : "+ this.getClass() + " Method name : deleteExam()" + " redirecting to home ...");
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editExam", method = RequestMethod.GET)
	public ModelAndView editExam(HttpServletRequest request) {
		logger.debug(new Date() +"  "+ this.getClass() + "Method name : editExam()" + " entering into method...");
		int eid = Integer.parseInt(request.getParameter("eid"));
		Exam exam = examService.getExamById(eid);
		ModelAndView model = new ModelAndView("ExamForm");
		model.addObject("exam", exam);
		return model; 	
	}

}