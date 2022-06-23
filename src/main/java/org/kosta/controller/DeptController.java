package org.kosta.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.dto.DeptDTO;
import com.kosta.myapp.model.DeptService;

@Controller		
public class DeptController {
	Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired		//A가 B를 사용하면 A는 B를 의존한다. 의존관계를 없애고자한다. Spring이 B를 생성해서 inject하도록 한다.
	DeptService dService;
	//TestDAO tdao;	//만들어진 객체를 읽어라! TestDAO tdao = new ~~ 의존하지 않으려고
	
	/*
	@ExceptionHandler
	public String processException(Exception ex) {
		ex.printStackTrace();
		logger.info("오류 : "+ ex.getMessage());
		return "error/errorPage500";
	}*/
	
	@RequestMapping(value="/dept/deptList.do", method=RequestMethod.GET)
	public void deptList(Model model, HttpServletRequest request) {
		//DB에서 조회
		List<DeptDTO > dlist = dService.selectAll();
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		String resultMessage = null;
		if(flashMap !=null ) {
			resultMessage = (String)flashMap.get("resultMessage");
		}
		model.addAttribute("resultMessage", resultMessage);
		model.addAttribute("deptlist", dlist);
		/* return "dept/deptList"; */ //이름이 같아서
	}
	
	@RequestMapping(value="/dept/deptDelete.do", method=RequestMethod.GET)
	public String deptDelete(int deptid, RedirectAttributes redirectAttr) {
		int result = dService.deptDelete(deptid);
		logger.info(result == 1 ? "부서번호 " + deptid + "번 삭제성공": "삭제실패");
		redirectAttr.addFlashAttribute("resultMessage", result + "건 삭제");
		return "redirect:/dept/deptList.do";
	}
	
	@RequestMapping(value="/dept/deptInsert.do", method=RequestMethod.GET)
	public String deptInsertGet() {
		logger.info("입력페이지 보여줌");
		return "dept/deptInsert";
	}
	
	@RequestMapping(value="/dept/deptInsert.do", method=RequestMethod.POST)
	public String deptInsertPost(DeptDTO dept, RedirectAttributes redirectAttr) {
		logger.info("입력 :" + dept.toString());
		int result = dService.deptInsert(dept);
		logger.info(result == 1 ? "추가성공":"추가실패");
		redirectAttr.addFlashAttribute("resultMessage", result + "건 추가");
		return "redirect:/dept/deptList.do";
	}
	@RequestMapping(value="/dept/deptUpdate.do", method=RequestMethod.GET)
	public String deptUpdateGet(int deptid, Model model) {
		//상세보기는 부서번호로 정보를 조회한 후 page보이기
		logger.info("상세페이지 보여줌");
		DeptDTO deptvo = dService.selectById(deptid);
		model.addAttribute("dept", deptvo); //data가지고 감 / 저장
		return "dept/deptDetail";
	}
	@RequestMapping(value="/dept/deptUpdate.do", method=RequestMethod.POST)
	public String deptUpdatePost(DeptDTO dept, RedirectAttributes redirectAttr) {  //new DeptDTO(); dept.setDepartment_id(request.get)
		logger.info("DB에 수정: "+ dept.toString());
		int result = dService.deptUpdate(dept);
		logger.info(result == 1 ? result + "건 수정성공": "수정실패");
		redirectAttr.addFlashAttribute("resultMessage", result + "건 수정");
		return "redirect:/dept/deptList.do";
	}

	//Exception Test 위해 추가
	//int a=10/0;
	//int[] arr = new int[10];
	//arr[10] = 100;
}
