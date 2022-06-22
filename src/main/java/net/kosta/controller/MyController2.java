package net.kosta.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.myapp.vo.Car;
import com.kosta.myapp.vo.UserVO;

@Controller
public class MyController2 {
	
	Logger logger = LoggerFactory.getLogger(MyController2.class);
	
	@RequestMapping("/spring/test1.do")
	public String method1() {	
		return "day0622/test1"; //WEB-INF/views/day0622/test1.jsp
	}
	
	//@ModelAttribute("user") => default�̸��� ������ �� �ִ�.
	//@ModelAttribute UserVO user, => userVO��� �Ѵ�. ���� ("user")�� �����ؾ���
	//@ModelAttribute�̸��� �����Ǹ� view���� ���� userVO userVO�� ���� ����ؾ��Ѵ�.
	@RequestMapping("/spring/test2.do")
	public String method2(
			@RequestParam String userid, 
			String userpass, 
			@ModelAttribute("user") UserVO user,
			Model model,
			HttpServletRequest request,
			HttpSession session
			) {
		String path = request.getRealPath(".");
		String path2 = session.getServletContext().getRealPath(".");
		String url = request.getRequestURI();
		logger.info(path);
		logger.info(path2);
		logger.info(url);
		session.setAttribute("user", user);
		logger.info(user.toString());
		System.out.println(userid);
		System.out.println(userpass);
		model.addAttribute("userid",userid);
		model.addAttribute("userpass",userpass);
		//model.addAttribute("user",user);
		return "day0622/test2"; //WEB-INF/views/day0622/test1.jsp
	}
	/*
	 * return ��
	 * ModelAndView, Model, String, void
	 * String : view�̸�, @ResponseBody�� ���� ����Ϸ��� document�� ����� ����
	 * ModelAndView : data + view�̸�
	 * Model : �޼��� �Ķ���ͷ� �����ϰ� ���� addAttribute()�� setting, view�̸��� String���� return
	 * void : ��û URI�κ��� �Ǿ��� �����ÿ� Ȯ���ڸ� ������ ������ �κ��� �� �̸����� ���
	 */
	@RequestMapping("/day0622/test3.do")
	public void method3(Model model, HttpServletRequest request) {
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null ) {
			String subject = (String)flashMap.get("subject");
			Car mycar = (Car)flashMap.get("car");
			System.out.println(subject);
			model.addAttribute("key",subject);
			model.addAttribute("car",mycar);
		}
		//void�� ���...default�� ��û�ּҿ� ���� �̸��� jsp�� forward�ȴ� 
	}
	@RequestMapping("/day0622/test4.do")
	public String method4(Model model, RedirectAttributes redirectAttr) {
		redirectAttr.addFlashAttribute("subject","SpringFramework");//redirect���� ������
		redirectAttr.addFlashAttribute("car",new Car("ī�Ϲ�", 6000,"white"));//redirect���� ������

		model.addAttribute("key","value!!!!!!!");
		//return "/day0622/test4"; //default�� forward(.jsp�߰�)�ȴ�. �ּҰ� ������� �ʴ´�.
		return "redirect:/day0622/test3.do"; //�ּ�â�� ����ȴ�.
	}
	///day0622/test4.do��û ==> redirect:/day0622/test3.do ==> /day0622/test3.jsp�� forward�ȴ�
	
}
