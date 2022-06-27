package org.kosta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.myapp.model.DeptService;
import com.kosta.myapp.model.EmpService;

@Controller
//@RequestMapping("/emp")
public class EmpController {
	@Autowired
	EmpService empService;
	@Autowired
	DeptService deptService;
	
	@GetMapping("/emp/emptest.do")
	public String emptestView(Model model) {
		model.addAttribute("mlist", empService.selectManagerAll());
		model.addAttribute("dlist", deptService.selectAll());
		model.addAttribute("jlist", empService.selectJobAll());
		return "/emp/empForm";
	}
}
