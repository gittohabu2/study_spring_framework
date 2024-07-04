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
public class ZasekikanriController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//一覧、登録画面表示用
	@RequestMapping(path = "/sheetreserv", method = RequestMethod.GET)
	public String sheet1(Model model) {

		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT yoyakubango, yoyakubi, yoyakuname FROM sheetreserve;");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);

		return "zasekikanri";
	}

	//編集、削除画面表示用
	@RequestMapping(path = "/reservmodify", method = RequestMethod.GET)
	public String sheet11() {

		/**returnの後ろのhtml名以外は変更の必要無し**/

		return "zasekikanri2";
	}

	//マイページ表示用
	@RequestMapping(path = "/reservmypage", method = RequestMethod.GET)
	public String sheet111(HttpSession session, Model model) {

		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT yoyakubango, yoyakubi, yoyakuname FROM sheetreserve WHERE yoyakuname = ?;",
						session.getAttribute("userid"));

		model.addAttribute("kensakupra", kensakukekka);
		model.addAttribute("yoyakukaisuu", kensakukekka.size() + "回");

		if (kensakukekka.size() == 0) {
			model.addAttribute("userrank", "ひよこユーザ");
		} else if (1 <= kensakukekka.size() && kensakukekka.size() <= 3) {
			model.addAttribute("userrank", "初心者ユーザ");
		} else if (4 <= kensakukekka.size() && kensakukekka.size() <= 7) {
			model.addAttribute("userrank", "ベテランユーザ");
		} else if (8 <= kensakukekka.size() && kensakukekka.size() <= 11) {
			model.addAttribute("userrank", "大御所ユーザ");
		} else if (12 <= kensakukekka.size()) {
			model.addAttribute("userrank", "石油王");
		}

		return "zasekimypage";
	}

	//予約回数ランキング表示用
	@RequestMapping(path = "/reservranking", method = RequestMethod.GET)
	public String sheet1111(Model model) {

		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT CONCAT(DENSE_RANK() OVER(ORDER BY COUNT(yoyakuname) desc),'位') AS ranking, yoyakuname, CONCAT(COUNT(yoyakuname),'回') AS yoyakucount \n"
						+ "FROM sheetreserve GROUP BY yoyakuname;");

		model.addAttribute("kensakupra", kensakukekka);

		return "zasekiranking";
	}

	//ログイン用
	@RequestMapping(path = "/sheetLogin", method = RequestMethod.POST)
	public String sheet1(HttpSession session, String id, String pass) {

		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT userid, userpass FROM sheetuser WHERE userid = ? AND userpass = ?;", id, pass);

		if (kensakukekka.size() != 0) {
			session.setAttribute("userid", id);
		}

		return "redirect:/sheetreserv";

	}

	//検索用(ヒント無し)
	@RequestMapping(path = "/sheetSer", method = RequestMethod.POST)
	public String sheet(Model model, String name) {

		// 検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList(
				"SELECT yoyakubango, yoyakubi, yoyakuname FROM sheetreserve WHERE yoyakuname LIKE ?;",
				"%" + name + "%");

		model.addAttribute("kensakupra", kensakukekka);

		return "zasekikanri";
	}

	//登録用
	@RequestMapping(path = "/sheetIns", method = RequestMethod.POST)
	public String sheet2(HttpSession session, String number, String day, String name) {

		name = (String) session.getAttribute("userid");
		//登録処理
		jdbcTemplate.update("INSERT INTO sheetreserve VALUES(?, ?, ?);", number, day, name);

		return "redirect:/sheetreserv";
	}

	//更新用(ヒント無し)
	@RequestMapping(path = "/sheetUpd", method = RequestMethod.POST)
	public String sheet3(HttpSession session, String number, String name) {

		name = (String) session.getAttribute("userid");
		// 更新処理
		jdbcTemplate.update("UPDATE sheetreserve SET yoyakuname = ? WHERE yoyakubango = ?;", name, number);

		return "redirect:/sheetreserv";
	}

	//削除用(ヒント無し)
	@RequestMapping(path = "/sheetDel", method = RequestMethod.POST)
	public String sheet4(String number) {

		//削除処理
		jdbcTemplate.update("DELETE FROM sheetreserve WHERE yoyakubango = ?;", number);

		return "redirect:/sheetreserv";
	}

}