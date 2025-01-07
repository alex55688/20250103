package com.tsb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class IntoController {

	@RequestMapping(path = "/blog", method = {RequestMethod.GET})
	public String index(Model model) {
		model.addAttribute("valid", false);
		return "index";
	}
}
