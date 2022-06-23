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
	
	@Autowired		//A�� B�� ����ϸ� A�� B�� �����Ѵ�. �������踦 ���ְ����Ѵ�. Spring�� B�� �����ؼ� inject�ϵ��� �Ѵ�.
	DeptService dService;
	//TestDAO tdao;	//������� ��ü�� �о��! TestDAO tdao = new ~~ �������� ��������
	
	/*
	@ExceptionHandler
	public String processException(Exception ex) {
		ex.printStackTrace();
		logger.info("���� : "+ ex.getMessage());
		return "error/errorPage500";
	}*/
	
	@RequestMapping(value="/dept/deptList.do", method=RequestMethod.GET)
	public void deptList(Model model, HttpServletRequest request) {
		//DB���� ��ȸ
		List<DeptDTO > dlist = dService.selectAll();
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		String resultMessage = null;
		if(flashMap !=null ) {
			resultMessage = (String)flashMap.get("resultMessage");
		}
		model.addAttribute("resultMessage", resultMessage);
		model.addAttribute("deptlist", dlist);
		/* return "dept/deptList"; */ //�̸��� ���Ƽ�
	}
	
	@RequestMapping(value="/dept/deptDelete.do", method=RequestMethod.GET)
	public String deptDelete(int deptid, RedirectAttributes redirectAttr) {
		int result = dService.deptDelete(deptid);
		logger.info(result == 1 ? "�μ���ȣ " + deptid + "�� ��������": "��������");
		redirectAttr.addFlashAttribute("resultMessage", result + "�� ����");
		return "redirect:/dept/deptList.do";
	}
	
	@RequestMapping(value="/dept/deptInsert.do", method=RequestMethod.GET)
	public String deptInsertGet() {
		logger.info("�Է������� ������");
		return "dept/deptInsert";
	}
	
	@RequestMapping(value="/dept/deptInsert.do", method=RequestMethod.POST)
	public String deptInsertPost(DeptDTO dept, RedirectAttributes redirectAttr) {
		logger.info("�Է� :" + dept.toString());
		int result = dService.deptInsert(dept);
		logger.info(result == 1 ? "�߰�����":"�߰�����");
		redirectAttr.addFlashAttribute("resultMessage", result + "�� �߰�");
		return "redirect:/dept/deptList.do";
	}
	@RequestMapping(value="/dept/deptUpdate.do", method=RequestMethod.GET)
	public String deptUpdateGet(int deptid, Model model) {
		//�󼼺���� �μ���ȣ�� ������ ��ȸ�� �� page���̱�
		logger.info("�������� ������");
		DeptDTO deptvo = dService.selectById(deptid);
		model.addAttribute("dept", deptvo); //data������ �� / ����
		return "dept/deptDetail";
	}
	@RequestMapping(value="/dept/deptUpdate.do", method=RequestMethod.POST)
	public String deptUpdatePost(DeptDTO dept, RedirectAttributes redirectAttr) {  //new DeptDTO(); dept.setDepartment_id(request.get)
		logger.info("DB�� ����: "+ dept.toString());
		int result = dService.deptUpdate(dept);
		logger.info(result == 1 ? result + "�� ��������": "��������");
		redirectAttr.addFlashAttribute("resultMessage", result + "�� ����");
		return "redirect:/dept/deptList.do";
	}

	//Exception Test ���� �߰�
	//int a=10/0;
	//int[] arr = new int[10];
	//arr[10] = 100;
}
