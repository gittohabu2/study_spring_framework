package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyFifthPraController {

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/fifthpra", method = RequestMethod.GET)
	public String fifthget(Model model) {
		
		model.addAttribute("modelpra", "さんもんめ");
		return "myfifthpra";
	}
	
	@RequestMapping(path = "/fifthpra", method = RequestMethod.POST)
	public String fifthpost(String like,Model model) {
		
		if("寿司".equals(like)) {
			System.out.println(like);
			model.addAttribute("likefood","私もお寿司が好きです");
		}else {
			System.out.println(like);
			model.addAttribute("likefood",like);
		}
		
		return "myfifthpra";
	}
}