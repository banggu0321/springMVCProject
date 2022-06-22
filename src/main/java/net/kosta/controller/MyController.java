package net.kosta.controller;

import java.sql.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.myapp.vo.Car;

@Controller
@RequestMapping("/hello") 	//class level 작성
public class MyController {
	
	@RequestMapping("/my1") 	//function level작성ㅇ
	public String test1(Model model) {
		model.addAttribute("major","컴퓨터공학");
		model.addAttribute("phone","010-1");
		model.addAttribute("car",new Car("ABC",1000,"white"));
		return "myjsp1";
	}
	
	@RequestMapping(value = {"/my2","/my3"},method=RequestMethod.GET)
	public ModelAndView test2(ModelAndView mv) {
		mv.addObject("major","컴퓨터공학2");
		mv.addObject("phone","010-1");
		mv.addObject("car",new Car("ABC",1000,"white"));
		mv.setViewName("myjsp1");
		return mv;
	}
	@RequestMapping(value = {"/my2","/my3"},method=RequestMethod.POST)
	public ModelAndView test3(ModelAndView mv) {
		mv.addObject("major","산업공학");
		mv.addObject("phone","010-12");
		mv.addObject("car",new Car("ABC",1000,"white"));
		mv.setViewName("myjsp1");
		return mv;
	}
	@RequestMapping(value = {"/param.do"},
			params = {"userid=hi","userpass","!email"},
			method=RequestMethod.GET)
	public String test4() {
		System.out.println("요청받음");
		return "paramResult";
	}
	@ResponseBody
	@RequestMapping("/param2.do")
	public String test5(String userid, int userpass, String email, Date birthday) {
		//request.getParameter("userpass")
		System.out.println("요청받음");
		System.out.println(userid);
		System.out.println(userpass);
		System.out.println(email);
		System.out.println(birthday);
		return "paramResult";
	}
	@ResponseBody
	@RequestMapping("/param3.do")
	public String test6(
			@RequestParam("userid") String userid, 
			@RequestParam("userpass") int pass, 
			String email, 
			Date birthday) {
		//request.getParameter("userpass")
		System.out.println("param3.do 요청받음");
		System.out.println(userid);
		System.out.println(pass);
		System.out.println(email);
		System.out.println(birthday);
		return "paramResult";
	}
	@ResponseBody //body에 리턴값 추가
	@RequestMapping("/param4.do")
	public String test7(Car car, String email, Date birthday) {
		System.out.println(car);
		System.out.println(email);
		System.out.println(birthday);
		return "car받음";
	}
	@ResponseBody
	@RequestMapping("/param5.do") 
	public String test8(@RequestParam Map<String,String> cart) {
		//파라메터이름과 매개변수이름이 일치하지 않은 경우 생략하지 말기
		for(String key:cart.keySet()) {
			System.out.println(key+"==>"+cart.get(key));
		}
		return "map test";
	}
	
	//JavaBeans객체로 view에 전달 가능 email,birthday 객체가 아님
	@RequestMapping("/param6.do") 
	public String test9(@ModelAttribute Car car, 
			 @RequestParam String email, 
			 @RequestParam Date birthday, Model model) {
		System.out.println(car);
		System.out.println(email);
		System.out.println(birthday);
		model.addAttribute("title", "@ModelAttribute연습");
		model.addAttribute("email", email);
		model.addAttribute("birthday", birthday);
		return "carInfo";
	}
	
}
