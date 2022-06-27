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
	
	//���������ȸ
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public String selectAll(Model model) {
		model.addAttribute("emp",eService.selectAll());
		return "emp/empList";
	}
	/*
	//��� jobs��ȭ
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public void selectJobAll(Model model) {
		
		model.addAttribute("emp",eService.selectJobAll());
	}
	//��� manager��ȸ
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public void selectManagerAll(Model model) {
		model.addAttribute("emp",eService.selectManagerAll());
	}
	//Ư���μ���ȸ
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public void selectByDept(Model model, int deptid) {
		model.addAttribute("emp",eService.selectByDept(deptid));
	}
	//Ư���Ŵ�����ȸ
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public void selectByManager(Model model, int mid) {
		model.addAttribute("emp",eService.selectByManager(mid));
	}
	//Ư��job��ȸ
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public void selectByJob(Model model,String job_id) {
		model.addAttribute("emp",eService.selectByJob(job_id));
	}
	//Ư��������ȸ
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public void selectByCondition(Model model, int deptid, String job_id, double sal, String hire_date) {
		model.addAttribute("emp",eService.selectByCondition(deptid, job_id, sal, hire_date));
	}
	//�̸��� �ߺ�üũ
	@RequestMapping(value="/empList.do", method=RequestMethod.GET)
	public void selectByEmail(String email) {
		int result = eService.selectByEmail(email);
	}
	*/
	//insert
	@RequestMapping(value="/empInsert.do", method=RequestMethod.GET)
	public String empInsertView() {
		logger.info("�Է� Form���̱�");
		return "emp/empInsert";
	}
	//update empid
	//Ư�� id��ȸ
	@RequestMapping(value="/empDetail.do", method=RequestMethod.GET)
	public void selectById(Model model, int empid) {
		model.addAttribute("empid",eService.selectById(empid));
	}
	//update department
	//delete empid
	//delete department
}
