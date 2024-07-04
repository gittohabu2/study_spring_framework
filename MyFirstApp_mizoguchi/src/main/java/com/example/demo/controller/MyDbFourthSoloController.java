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
public class MyDbFourthSoloController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/fourthsolo", method = RequestMethod.GET)
	public String dbfg(Model model) {

		//検索処理
		List<Map<String,Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM fourthsolotable;");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra",kensakukekka);

		return "mydbfourthsolo";
	}

		@RequestMapping(path = "/fourthsoloIns", method = RequestMethod.POST)
		public String dbfgIns(Model model,String compname, String compemp,String compgyoshu) {
	
			// データ登録
			jdbcTemplate.update("INSERT INTO fourthsolotable VALUES(?,?,?)",compname,compemp,compgyoshu);
	
			return "redirect:/fourthsolo";
		}

		@RequestMapping(path = "/fourthsoloUpd", method = RequestMethod.POST)
		public String dbfgUpd(Model model,String compname,String compemp, String compgyoshu) {
	
			// データ更新
			jdbcTemplate.update("UPDATE fourthsolotable SET compemp = ?, compgyoshu = ? WHERE compname = ?",compemp,compgyoshu,compname);
		
		return "redirect:/fourthsolo";
		}

		@RequestMapping(path = "/fourthsoloDel", method = RequestMethod.POST)
		public String dbfgdel(Model model, String compname) {
	
			// データ削除
			jdbcTemplate.update("DELETE FROM fourthsolotable WHERE compname = ?",compname);
	
		return "redirect:/fourthsolo";
		}

		@RequestMapping(path = "/fourthsoloSer", method = RequestMethod.POST)
		public String dbfgSer(Model model, String compname) {
	
			// データ検索処理
			List<Map<String,Object>> kensakukekka = jdbcTemplate.queryForList("SELECT * FROM fourthsolotable WHERE compname LIKE ? OR compgyoshu LIKE ?","%" + compname + "%", "%" + compname + "%");
	
			model.addAttribute("kensakupra",kensakukekka);
	
		return "mydbfourthsolo";
		}

}