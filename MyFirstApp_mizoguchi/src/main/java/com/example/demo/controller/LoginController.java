package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(path="/userLogin", method = RequestMethod.GET)
	public String loginget() {
		return "login";
	}
	
	@RequestMapping(path="/userLogin", method = RequestMethod.POST)
	public String loginpost(String id,String pass,Model model) {
		
		int result = pass.length();
		
		if(id.equals("userlogin")&&pass.equals("userpass")) {
			model.addAttribute("login",id);
			return "home";
		}else {
            if(result < 8) {
            	model.addAttribute("failure","パスワードが短い");
            	return "login";
			}else {
				model.addAttribute("failure","ログイン失敗しました");
				return "failure";
			}
			
		}
	}
	
	@RequestMapping(path="/userHome", method = RequestMethod.GET)
	public String homeget() {
		return "home";
	}
	
	@RequestMapping(path="/userHome", method = RequestMethod.POST)
	public String homepost(String tubuyaku,Model model) {
		
		model.addAttribute("toukou",tubuyaku);
		return "home";
		
	}

}
