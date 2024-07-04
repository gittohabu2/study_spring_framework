package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CentralController {

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/matome", method = RequestMethod.GET)
	public String fourthget() {
		return "central";
	}
	
}