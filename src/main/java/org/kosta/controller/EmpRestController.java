package org.kosta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.dto.EmpVO;

@RestController
//@RequestMapping("/emp/*")
public class EmpRestController {
	
	Logger logger = LoggerFactory.getLogger(EmpRestController.class);
	
	@RequestMapping(value="/emplist2.do", produces="text/plain;charset=utf-8")
	public String emplist() {
		return "모든 직원을 조회합니다.";
	}
	@RequestMapping(value="/emplist2.do/{empid}/{message}", produces=MediaType.APPLICATION_JSON_VALUE)
	public String empById(@PathVariable int empid, @PathVariable String message) {
		logger.info("empid= " + empid + "message=" + message);
		EmpVO emp = new EmpVO();
		emp.setEmployee_id(empid);
		emp.setFirst_name("홍길동");
		//Jackson이 자바의 객체를 JSON으로 만들어준다.
		return empid + "직원 상세정보입니다.";
	}
}
