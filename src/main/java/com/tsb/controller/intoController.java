package com.tsb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class intoController {

	@RequestMapping(path = "/blog", method = {RequestMethod.GET})
	public String index(Model model) {
		model.addAttribute("valid", false);
		return "index";
	}
	
	@RequestMapping(path = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(Model model, HttpServletRequest request) {
		HttpSession session =  request.getSession();
		if("POST".equals(request.getMethod())) {
			if(session.getAttribute("valid") != null && true == (boolean)session.getAttribute("valid")) {
				System.out.println("帳號密碼正確");
			}else {
				model.addAttribute("validshow", true);
				model.addAttribute("errormsg", "帳號密碼有誤");
			}
		}
		return "login";
	}
}
