package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyFourthController {

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/fourthpage", method = RequestMethod.GET)
	public String fourthget() {
		return "myfourth";
	}
	
	//画面からの値を処理するPOSTメソッド
	@RequestMapping(path = "/fourthpage", method = RequestMethod.POST)
	public String fourthpost(String param1,String param2,String bplace, Model model, Model bp) {
		
		System.out.println("画面から"+ param1 +"と入力されました");
		
		model.addAttribute("userName", param1);
		
		System.out.println("画面から"+ param2 +"と入力されました");
		
		model.addAttribute("userhobby", param2);
		
		System.out.println("画面から"+ param2 +"と入力されました。趣味は" + param2 + "のようです。出身は"+ bplace +"のようです。");
		
		bp.addAttribute("usersyussin", bplace);
		
		return "myfourth";
	}
//	@RequestMapping(path = "/fourthpra", method = RequestMethod.GET)
//	public String fourth(String x, Model modelpra) {
//		
//		modelpra.addAttribute("model", "4問目");
//		
//		return "myfourth";
//	}
	@RequestMapping(path = "/fourth/pra2", method = RequestMethod.GET)
	public String fourthpra() {
		
		return "myfourthpra";
	}
}