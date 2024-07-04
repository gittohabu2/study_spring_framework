package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class KokusaiDomeController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//予約一覧画面表示
	@RequestMapping(path = "/kokusaidome", method = RequestMethod.GET)
	public String kokusai(HttpSession session) {

		List<Map<String, Object>> yoyakuList = jdbcTemplate.queryForList("SELECT * FROM domeyoyaku ORDER BY yoyakubi asc;");

		session.setAttribute("yoyakuList", yoyakuList);

		return "kokusaidomeitiran";

	}

	//予約操作画面表示
	@RequestMapping(path = "/kokusaidome2", method = RequestMethod.GET)
	public String kokusai() {

		return "kokusaidomesousa";

	}
	
	//キーワード検索
	@RequestMapping(path = "/kokusaidomeSer", method = RequestMethod.POST)
	public String kokusai(HttpSession session, String keyward) {

		List<Map<String, Object>> kensakuListSer;
		kensakuListSer= jdbcTemplate.queryForList("SELECT * FROM domeyoyaku WHERE yoyakubi LIKE ? OR yoyakuclass LIKE ? OR"
				                               + " yoyakuname LIKE ? OR yoyakucoat LIKE ?;", "%" + keyward + "%", 
				                               "%" + keyward + "%", "%" + keyward + "%", "%" + keyward + "%");

		session.setAttribute("kensakauListSer", kensakuListSer);

		return "redirect:/kokusaidome2";

	}

	//予約
	@RequestMapping(path = "/kokusaidomeIns", method = RequestMethod.POST)
	public String kokusaiIns(Model model, String yoyakubi, String yoyakuclass, String yoyakuname, String yoyakucoat) {

		List<Map<String, Object>> kakuninList = jdbcTemplate
				.queryForList("SELECT * FROM domeyoyaku WHERE yoyakubi = ? AND yoyakucoat = ?;", yoyakubi, yoyakucoat);

		if ("0日".equals(yoyakubi) || !("バスケ".equals(yoyakucoat)) && !("バレー".equals(yoyakucoat)) || kakuninList.size() > 0) {
			model.addAttribute("yoyakujokyo", "予約不可");

			return "kokusaidomesousa";
		} else {
			jdbcTemplate.update("INSERT INTO domeyoyaku VALUES(?,?,?,?);", yoyakubi, yoyakuclass, yoyakuname,
					yoyakucoat);

			return "redirect:/kokusaidome";
		}

	}

	//予約日変更
	@RequestMapping(path = "/kokusaidomeUpd", method = RequestMethod.POST)
	public String kokusaiUpd(String yoyakubi1, String yoyakubi2, String yoyakuname) {

		jdbcTemplate.update("UPDATE domeyoyaku SET yoyakubi = ? WHERE yoyakubi = ? AND yoyakuname = ?;", yoyakubi2,
				yoyakubi1, yoyakuname);

		return "redirect:/kokusaidome";

	}

	//予約削除
	@RequestMapping(path = "/kokusaidomeDel", method = RequestMethod.POST)
	public String kokusaiDel(String yoyakubi, String yoyakuname) {

		if("全削除".equals(yoyakubi) || "全削除".equals(yoyakuname)) {
			jdbcTemplate.update("DELETE FROM domeyoyaku;");
		}else {
		    jdbcTemplate.update("DELETE FROM domeyoyaku WHERE yoyakubi = ? AND yoyakuname = ?;", yoyakubi, yoyakuname);
		}
		
		return "redirect:/kokusaidome";

	}

}
