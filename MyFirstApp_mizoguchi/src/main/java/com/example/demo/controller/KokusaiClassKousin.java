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
public class KokusaiClassKousin {

	@Autowired
	JdbcTemplate jdbcTemplate;

	
	@RequestMapping(path = "/classupd", method = RequestMethod.GET)
	public String kokusai(HttpSession session) {

		List<Map<String, Object>> classList = jdbcTemplate.queryForList("SELECT * FROM m_user;");

		session.setAttribute("classList", classList);

		return "kokusaiclasskousin";
		
	}

	@RequestMapping(path = "/classupd", method = RequestMethod.POST)
	public String kokusai(HttpSession session, Model model, String before, String after) {

		boolean al1 = after.contains("A");
		boolean al2 = after.contains("B");
		boolean al3 = after.contains("C");
		boolean al4 = after.contains("D");
		boolean al5 = after.contains("E");
		boolean al6 = after.contains("F");
		boolean al7 = after.contains("G");
		boolean al8 = after.contains("H");
		boolean al9 = after.contains("I");
		boolean al10 = after.contains("J");
		boolean al11 = after.contains("K");
		
		if(al1== true || al2== true || al3==true || al4==true || al5==true || al6==true || al7==true || al8==true || al9==true || al10==true || al11 == true) {
			jdbcTemplate.update("UPDATE m_user SET class = ? WHERE class = ?;", after, before);
		

		List<Map<String, Object>> classList = jdbcTemplate.queryForList("SELECT * FROM m_user WHERE class = ?;", after);

		session.setAttribute("classList", classList);
		
		}else {
			model.addAttribute("error", "エラー");
		}
		
		return "kokusaiclasskousin";

	}

	
}
