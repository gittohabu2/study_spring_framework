package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class ZasekiController {

	@RequestMapping(path = "/zasekiyoyaku", method = RequestMethod.GET)
	public String zasekiget(Model model, HttpSession session) {
		
		session.setAttribute("userid", null);
		session.setAttribute("menu", "");
		model.addAttribute("login", "login");

		return "zaseki";
	}

	@RequestMapping(path = "/zasekiyoyaku", method = RequestMethod.POST)
	public String zasekipost(String id, String menu, String yoyaku, String matomete1, String matomete2, String torikesi,
			Model model, HttpSession session) {

		//ログイン
		if(id != null) {
		session.setAttribute("userid", id);
		}

		//操作、リスト定義
		session.setAttribute("menu", menu);
		List<Integer> zasekiList = new ArrayList<>();
		List<String> userList = new ArrayList<>();

		//予約
		if (menu == null && yoyaku != null) {

			int yoyaku2 = Integer.parseInt(yoyaku);

			//重複確認
			if (zasekiList.contains(yoyaku2) == true || session.getAttribute("yoyaku" + yoyaku2) != null) {
				model.addAttribute("tyouhuku", "重複");
				session.setAttribute("menu", "予約");
			} else {

				if (yoyaku2 == 1) {

					session.setAttribute("yoyaku1", yoyaku2);
				}
				if (yoyaku2 == 2) {

					session.setAttribute("yoyaku2", yoyaku2);
				}
				if (yoyaku2 == 3) {

					session.setAttribute("yoyaku3", yoyaku2);
				}
				if (yoyaku2 == 4) {

					session.setAttribute("yoyaku4", yoyaku2);
				}
				if (yoyaku2 == 5) {

					session.setAttribute("yoyaku5", yoyaku2);
				}
				if (yoyaku2 == 6) {

					session.setAttribute("yoyaku6", yoyaku2);
				}
				if (yoyaku2 == 7) {

					session.setAttribute("yoyaku7", yoyaku2);
				}
				if (yoyaku2 == 8) {

					session.setAttribute("yoyaku8", yoyaku2);
				}
				if (yoyaku2 == 9) {

					session.setAttribute("yoyaku9", yoyaku2);
				}
				if (yoyaku2 == 10) {

					session.setAttribute("yoyaku10", yoyaku2);
				}

			}

		}

		//まとめて予約
		if (menu == null && matomete1 != null && matomete2 != null) {
			int matometex = Integer.parseInt(matomete1);
			int matometey = Integer.parseInt(matomete2);
			for (; matometex <= matometey; matometex++) {

				//重複確認
				if (zasekiList.contains(matometex) == true || session.getAttribute("yoyaku" + matometex) != null) {
					model.addAttribute("tyouhuku", "重複");
					session.setAttribute("menu", "まとめて予約");
				}
				session.setAttribute("yoyaku" + matometex, matometex);
			}

		}

		//取消
		if (menu == null && torikesi != null) {
			int torikesi2 = Integer.parseInt(torikesi);

			if (torikesi2 == 1) {

				session.removeAttribute("yoyaku1");
			}
			if (torikesi2 == 2) {

				session.removeAttribute("yoyaku2");
			}
			if (torikesi2 == 3) {

				session.removeAttribute("yoyaku3");
			}
			if (torikesi2 == 4) {

				session.removeAttribute("yoyaku4");
			}
			if (torikesi2 == 5) {

				session.removeAttribute("yoyaku5");
			}
			if (torikesi2 == 6) {

				session.removeAttribute("yoyaku6");
			}
			if (torikesi2 == 7) {

				session.removeAttribute("yoyaku7");
			}
			if (torikesi2 == 8) {

				session.removeAttribute("yoyaku8");
			}
			if (torikesi2 == 9) {

				session.removeAttribute("yoyaku9");
			}
			if (torikesi2 == 10) {

				session.removeAttribute("yoyaku10");
			}
			if(torikesi2 == 11) {
				
				for (int i = 1; i <= 10; i++) {
					if (session.getAttribute("yoyaku" + i) != null) {
						session.removeAttribute("yoyaku" + i);
					}

				}
			}

		}

		//リストに格納
		for (int i = 1; i <= 10; i++) {
			if (session.getAttribute("yoyaku" + i) != null) {
				zasekiList.add((int) session.getAttribute("yoyaku" + i));
			}

		}

		for (int i = 1; i <= 10; i++) {
			if (session.getAttribute("yoyaku" + i) != null) {
				String user = String.valueOf(session.getAttribute("yoyaku" + i));
				session.setAttribute("user" + i, (String)session.getAttribute("userid") + "が" + user);
				userList.add((String)session.getAttribute("user" + i));
			}
		}

		//リストをセッションに格納
		session.setAttribute("yoyakulist", zasekiList);
		session.setAttribute("userList", userList);

		return "zaseki";
	}

}
