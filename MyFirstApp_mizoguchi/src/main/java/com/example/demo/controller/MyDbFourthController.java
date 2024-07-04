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
public class MyDbFourthController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/mydbfourthpra", method = RequestMethod.GET)
	public String dbfg(Model model) {

		//検索処理
		List<Map<String,Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM fourthtable");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra",kensakukekka);

		return "mydbfourth";
	}

	@RequestMapping(path = "/mydbfourthpra", method = RequestMethod.POST)
	public String dbfgpost(String sikaku,Model model) {

		//検索処理
		List<Map<String,Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM fourthtable WHERE sikakuname = ?;", sikaku);

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra",kensakukekka);

		return "mydbfourth";
	}
	
	@RequestMapping(path = "/fourthpraIns", method = RequestMethod.POST)
	public String dbfgIns(String cername, String cerrank, Model model) {

		jdbcTemplate.update("INSERT INTO fourthtable VALUES(?,?)", cername, cerrank);
		

		return "redirect:/mydbfourthpra";
	}
	
	@RequestMapping(path = "/fourthpraUpd", method = RequestMethod.POST)
	public String dbfgUpd(String cername, String cerrank, Model model) {

		jdbcTemplate.update("UPDATE fourthtable SET sikakurank = ? WHERE sikakuname = ?", cerrank, cername);
		

		return "redirect:/mydbfourthpra";
	}
	
	@RequestMapping(path = "/fourthpraDel", method = RequestMethod.POST)
	public String dbfgDel(String cername, Model model) {

		jdbcTemplate.update("DELETE FROM fourthtable WHERE sikakuname = ?", cername);
		

		return "redirect:/mydbfourthpra";
	}


}