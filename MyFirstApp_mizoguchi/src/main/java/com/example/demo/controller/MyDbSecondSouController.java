package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyDbSecondSouController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/mydbsecondsou", method = RequestMethod.GET)
	public String dbsg2() {

		return "mydbsecondsou";
	}
	
	/**************************↓今日重要なのはここから↓******************************/
	@RequestMapping(path = "/mydbsecondsou", method = RequestMethod.POST)
	public String dbfgpra(Model model,String gaiyo) {

		List<Map<String,Object>> kensakukekka;
		
		if(gaiyo != null) {
			//検索処理
			kensakukekka = jdbcTemplate.queryForList("SELECT * FROM secondsoutable WHERE gaiyo = ?",gaiyo);

			//検索結果のリストをmodelに格納する。
			model.addAttribute("kensakusouSecond",kensakukekka);
		}
				

		return "mydbsecondsou";
	}
	
	
	@RequestMapping(path = "/mydbsecondInssou", method = RequestMethod.POST)
	public String dbspins(Model model,String gaiyo,String setumei) {

		
		//データ登録
		jdbcTemplate.update("INSERT INTO secondsoutable (gaiyo,setumei) VALUES (?,?);",gaiyo,setumei);
		
		//データ登録後、全件検索
		List<Map<String,Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT * FROM secondsoutable");
		//検索結果のリストをmodelに格納する。
		model.addAttribute("kensakusouSecond",kensakukekka);		

		return "mydbsecondsou";
	}
	/**************************************************************************/
	
}