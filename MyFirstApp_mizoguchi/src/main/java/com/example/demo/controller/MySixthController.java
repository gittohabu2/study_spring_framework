package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class MySixthController {
	@RequestMapping(path = "/sixthpra", method = RequestMethod.GET)
	public String four(Model model) {
		model.addAttribute("ichimonme","いちもんめ");
		return "MySixth";
	}
	@RequestMapping(path = "/sixthpra", method = RequestMethod.POST)
	public String four(String mem1,String mem2) {
        System.out.println(mem1 + mem2);
		return "MySixth";
	}
	
	@RequestMapping(path = "/sixthlogin", method = RequestMethod.GET)
	public String four() {
		return "mysixthlogin";
	}
	@RequestMapping(path = "/sixthlogin", method = RequestMethod.POST)
	public String fourlogpo(String id,String pass,Model model) {
		
		if("uho".equals(id) && "uhoho".equals(pass)) {
			model.addAttribute("result","ログイン成功");
			return "mysixthloginsuc";
		} else {
			model.addAttribute("result","ログイン失敗");
			return "mysixthloginfail";
		}
	}
	
}