package org.kosta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.dto.EmpVO;
import com.kosta.dto.JobVO;
import com.kosta.myapp.model.EmpService;

@RestController //@Controller + @ResponseBody
@RequestMapping("/emp/*") //@RequestMapping=>@GetMapping, PostMapping
public class EmpRestController {
	
	@Autowired
	EmpService empService;
	
	Logger logger = LoggerFactory.getLogger(EmpRestController.class);
	
	//consumes는 들어오는 데이터 타입을 정의 : consumes = MediaType.APPLICATION_JSON_VALUE
	//==>Content-tpye : application.json
	//produces는 반환하는 데이터 타입을 정의
	//==>produces = MediaType.APPLICATION_JSON_VALUE
	@RequestMapping(value="/hello.do", produces="text/plain;charset=utf-8")
	public String hello() {
		return "hello 하이~";
	}
	@RequestMapping(value="/emplist.do", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = empService.selectAll();
		return emplist;
	}
	@RequestMapping(value="/empdetail.do/{empid}", produces="application/json")
	public EmpVO empById(@PathVariable int empid) {
		logger.info("empid= " + empid);
		EmpVO emp = empService.selectById(empid);
		return emp;
	}
	@GetMapping(value = "/empByManager.do/{mid}",    produces = "application/json")
	public List<EmpVO> empByManager(@PathVariable int mid ) {
		logger.info("mid = " + mid );
		List<EmpVO> emplist = empService.selectByManager(mid);
		return emplist;
	}
	@GetMapping(value="/empByEmail.do/{email}", produces="text/plain;charset=utf-8")
	public String empByEmail(@PathVariable String email) {
		logger.info("email= " + email);
		int result = empService.selectByEmail(email);
		return result + "건";
	}
	@GetMapping(value="/empByManagerAll.do", produces="application/json")
	public List<EmpVO> empByManagerAll() {
		List<EmpVO> emplist = empService.selectManagerAll();
		logger.info("모든 매니저 조회 .." + emplist.size());
		return emplist;
	}
	@GetMapping(value="/empByJobAll.do", produces="application/json")
	public List<JobVO> empByJobAll() {
		List<JobVO> joblist = empService.selectJobAll();
		logger.info("모든 joblist..." + joblist.size());
		return joblist;
	}
	@GetMapping(value="/empByJob.do/{jobid}", produces="application/json")
	public List<EmpVO> empByManagerAll(@PathVariable String jobid) {
		List<EmpVO> emplist = empService.selectByJob(jobid);
		logger.info(jobid + "(직업)의 직원들 조회 .." + emplist.size());
		return emplist;
	}
	@GetMapping(value="/empByDept.do/{deptid}", produces="application/json")
	public List<EmpVO> empByDept(@PathVariable int deptid) {
		List<EmpVO> emplist = empService.selectByDept(deptid);
		logger.info(deptid + "(부서)의 직원들 조회 .." + emplist.size());
		return emplist;
	}
	@GetMapping(value="/empByCondition.do/{deptid}/{jobid}/{sal}/{sdate}", produces="application/json")
	public List<EmpVO> empByCondition(@PathVariable int deptid, @PathVariable String jobid, @PathVariable int sal, @PathVariable String sdate) {
		List<EmpVO> emplist = empService.selectByCondition(deptid, jobid, sal, sdate);
		logger.info(deptid + "(condition)의 직원들 조회 .." + emplist.size());
		return emplist;
	}
	//입력..@PostMapping = @RequestMapping + method = RequestMethod.POST
	@PostMapping(value = "/empInsert.do", consumes ="application/json")
	public String insert(@RequestBody EmpVO emp) {
		logger.info("입력할 데이터 : " + emp);
		int result = empService.empInsert(emp);
		return result +"";
	}
	@PutMapping(value = "/empUpdate.do", consumes ="application/json")
	public String update(@RequestBody EmpVO emp) {
		logger.info("수정할 데이터 : " + emp);
		int result = empService.empUpdate(emp);
		return result +"";
	}
	@DeleteMapping(value = "/empDelete.do/{empid}")
	public String delete(@PathVariable int empid) {
		logger.info("삭제할 직원번호 : " + empid);
		int result = empService.empDelete(empid);
		return result +"";
	}
	//@RequestBody : page에 뿌림
	//@PathVariable : 주소창
}
