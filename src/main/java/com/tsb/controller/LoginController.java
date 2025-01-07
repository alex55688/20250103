package com.tsb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tsb.entity.Users;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@RequestMapping(path = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(Users user, Model model, HttpServletRequest request) {
		HttpSession session =  request.getSession();
		boolean valid = (boolean)session.getAttribute("valid");
		if("POST".equals(request.getMethod())) {
			if(session.getAttribute("valid") != null && valid) {
				System.out.println("帳號密碼正確");
				model.addAttribute("validshow", true);
				model.addAttribute("msg", "帳號密碼正確");
			}else {
				model.addAttribute("validshow", true);
				model.addAttribute("msg", "帳號密碼有誤");
			}
		}
		return "login";
	}
}
