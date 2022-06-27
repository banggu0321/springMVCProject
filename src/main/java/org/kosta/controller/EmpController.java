package org.kosta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosta.myapp.model.EmpService;

@Controller
@RequestMapping("/emp")
public class EmpController {
	
	@Autowired
	EmpService eService;
	
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//모든직원조회
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public String selectAll(Model model) {
		model.addAttribute("emp",eService.selectAll());
		return "emp/empList";
	}
	/*
	//모든 jobs조화
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public void selectJobAll(Model model) {
		
		model.addAttribute("emp",eService.selectJobAll());
	}
	//모든 manager조회
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public void selectManagerAll(Model model) {
		model.addAttribute("emp",eService.selectManagerAll());
	}
	//특정부서조회
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public void selectByDept(Model model, int deptid) {
		model.addAttribute("emp",eService.selectByDept(deptid));
	}
	//특정매니저조회
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public void selectByManager(Model model, int mid) {
		model.addAttribute("emp",eService.selectByManager(mid));
	}
	//특정job조회
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public void selectByJob(Model model,String job_id) {
		model.addAttribute("emp",eService.selectByJob(job_id));
	}
	//특정복합조회
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public void selectByCondition(Model model, int deptid, String job_id, double sal, String hire_date) {
		model.addAttribute("emp",eService.selectByCondition(deptid, job_id, sal, hire_date));
	}
	//이메일 중복체크
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public void selectByEmail(String email) {
		int result = eService.selectByEmail(email);
	}
	*/
	//insert
	@RequestMapping(value="/empInsert.do", method=RequestMethod.GET)
	public String empInsertView() {
		logger.info("입력 Form보이기");
		return "emp/empInsert";
	}
	//update empid
	//특정 id조회
	@RequestMapping(value="/empDetail.do", method=RequestMethod.GET)
	public void selectById(Model model, int empid) {
		model.addAttribute("empid",eService.selectById(empid));
	}
	//update department
	//delete empid
	//delete department
}
