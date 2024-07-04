package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyEleventhController {

	@RequestMapping(path = "/elevenpageone", method = RequestMethod.GET)
	public String elevenget(Model model,HttpSession session) {
		
		//モデルにデータを格納
		model.addAttribute("modelData","→モデルにしまったデータです←");
		
		//sessionにデータを格納
		session.setAttribute("sessionData", "→sessionにしまったデータです←");
		session.setAttribute("sessionData2", "練習問題①クリア");
		
		return "myeleventhone";
	}
	
	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/elevenpagetwo", method = RequestMethod.GET)
	public String elevenget2(Model model,HttpSession session) {
		
		//セッションからデータを取り出して適当な文字列をくっつける。
		
		String x = (String)session.getAttribute("sessionData");
		String x2 = (String)session.getAttribute("sessionData2");
		
		x = x + "だよだよ";
		x2 = x2 + "練習問題②もクリア";
		
		//sessionにデータを格納
		session.setAttribute("sessionData", x);
		session.setAttribute("sessionData2", x2);
		
		return "myeleventhtwo";
	}
	
	@RequestMapping(path = "/elevenpagethree", method = RequestMethod.GET)
	public String elevenget3(HttpSession session) {
		
		//sessionにデータを格納
		session.setAttribute("sessionData3", "練習問題③だよおお");
		
		return "myelevenththree";
	}
	
	@RequestMapping(path = "/elevenpageone", method = RequestMethod.POST)
	public String elevenpost(String id,String id2,String id3,String pw,HttpSession session) {
		
		if(pw.equals("elevenpw")) {
			session.setAttribute("userDataId", id);
			if(id.equals("")) {
				
			}
		}
		
		return "myeleventhtwo";
	}
	
}