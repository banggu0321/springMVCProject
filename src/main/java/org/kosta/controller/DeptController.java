package org.kosta.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosta.dto.DeptDTO;
import com.kosta.myapp.model.TestDAO;

@Controller		
public class DeptController {
	Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired		//A가 B를 사용하면 A는 B를 의존한다. 의존관계를 없애고자한다. Spring이 B를 생성해서 inject하도록 한다.
	TestDAO tdao;	//만들어진 객체를 읽어라! TestDAO tdao = new ~~ 의존하지 않으려고
	
	/*
	@ExceptionHandler
	public String processException(Exception ex) {
		ex.printStackTrace();
		logger.info("오류 : "+ ex.getMessage());
		return "error/errorPage500";
	}*/
	
	@RequestMapping(value="/dept/deptList.do", method=RequestMethod.GET)
	public void deptList(Model model) {
		//Exception Test 위해 추가
		//int a=10/0;
		//int[] arr = new int[10];
		//arr[10] = 100;
		//DB에서 조회는 추가할 예정
		List<DeptDTO > dlist = new ArrayList<>();
		dlist.add(new DeptDTO(10,"부서1",100,1700));
		dlist.add(new DeptDTO(20,"부서2",100,1700));
		dlist.add(new DeptDTO(30,"부서3",100,1700));
		dlist.add(new DeptDTO(40,"부서4",100,1700));
		dlist.add(new DeptDTO(50,"부서5",100,1700));
		dlist.add(new DeptDTO(60,"부서6",100,1700));
		model.addAttribute("deptlist", dlist);
		/* return "dept/deptList"; */ //이름이 같아서
	}
	
	@RequestMapping(value="/dept/deptDelete.do", method=RequestMethod.GET)
	public String deptDelete(int deptid) {
		//DB에서 삭제는 추가할 예정
		logger.info(deptid + "를 삭제함");
		return "redirect:/dept/deptList.do";
	}
	
	@RequestMapping(value="/dept/deptInsert.do", method=RequestMethod.GET)
	public String deptInsertGet() {
		logger.info("입력페이지 보여줌");
		return "dept/deptInsert";
	}
	
	@RequestMapping(value="/dept/deptInsert.do", method=RequestMethod.POST)
	public String deptInsertPost(DeptDTO dept) {
		logger.info("입력 :" + dept.toString());
		//DB에 입력하기는 추가할 예정이다
		return "redirect:/";
	}
	@RequestMapping(value="/dept/deptUpdate.do", method=RequestMethod.GET)
	public String deptUpdateGet(int deptid, Model model) {
		//상세보기는 부서번호로 정보를 조회한 후 page보이기
		logger.info("상세페이지 보여줌");
		DeptDTO deptvo = tdao.selectById(deptid);
		model.addAttribute("dept", deptvo); //data가지고 감 / 저장
		return "dept/deptDetail";
	}
	@RequestMapping(value="/dept/deptUpdate.do", method=RequestMethod.POST)
	public String deptUpdatePost(DeptDTO dept) {  //new DeptDTO(); dept.setDepartment_id(request.get)
		logger.info("DB에 수정: "+ dept.toString());
		//DB에 입력하기는 추가할 예정이다
		return "redirect:/";
	}
}
