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
	
	@Autowired		//A�� B�� ����ϸ� A�� B�� �����Ѵ�. �������踦 ���ְ����Ѵ�. Spring�� B�� �����ؼ� inject�ϵ��� �Ѵ�.
	TestDAO tdao;	//������� ��ü�� �о��! TestDAO tdao = new ~~ �������� ��������
	
	/*
	@ExceptionHandler
	public String processException(Exception ex) {
		ex.printStackTrace();
		logger.info("���� : "+ ex.getMessage());
		return "error/errorPage500";
	}*/
	
	@RequestMapping(value="/dept/deptList.do", method=RequestMethod.GET)
	public void deptList(Model model) {
		//Exception Test ���� �߰�
		//int a=10/0;
		//int[] arr = new int[10];
		//arr[10] = 100;
		//DB���� ��ȸ�� �߰��� ����
		List<DeptDTO > dlist = new ArrayList<>();
		dlist.add(new DeptDTO(10,"�μ�1",100,1700));
		dlist.add(new DeptDTO(20,"�μ�2",100,1700));
		dlist.add(new DeptDTO(30,"�μ�3",100,1700));
		dlist.add(new DeptDTO(40,"�μ�4",100,1700));
		dlist.add(new DeptDTO(50,"�μ�5",100,1700));
		dlist.add(new DeptDTO(60,"�μ�6",100,1700));
		model.addAttribute("deptlist", dlist);
		/* return "dept/deptList"; */ //�̸��� ���Ƽ�
	}
	
	@RequestMapping(value="/dept/deptDelete.do", method=RequestMethod.GET)
	public String deptDelete(int deptid) {
		//DB���� ������ �߰��� ����
		logger.info(deptid + "�� ������");
		return "redirect:/dept/deptList.do";
	}
	
	@RequestMapping(value="/dept/deptInsert.do", method=RequestMethod.GET)
	public String deptInsertGet() {
		logger.info("�Է������� ������");
		return "dept/deptInsert";
	}
	
	@RequestMapping(value="/dept/deptInsert.do", method=RequestMethod.POST)
	public String deptInsertPost(DeptDTO dept) {
		logger.info("�Է� :" + dept.toString());
		//DB�� �Է��ϱ�� �߰��� �����̴�
		return "redirect:/";
	}
	@RequestMapping(value="/dept/deptUpdate.do", method=RequestMethod.GET)
	public String deptUpdateGet(int deptid, Model model) {
		//�󼼺���� �μ���ȣ�� ������ ��ȸ�� �� page���̱�
		logger.info("�������� ������");
		DeptDTO deptvo = tdao.selectById(deptid);
		model.addAttribute("dept", deptvo); //data������ �� / ����
		return "dept/deptDetail";
	}
	@RequestMapping(value="/dept/deptUpdate.do", method=RequestMethod.POST)
	public String deptUpdatePost(DeptDTO dept) {  //new DeptDTO(); dept.setDepartment_id(request.get)
		logger.info("DB�� ����: "+ dept.toString());
		//DB�� �Է��ϱ�� �߰��� �����̴�
		return "redirect:/";
	}
}
