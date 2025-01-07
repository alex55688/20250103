package com.tsb.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsb.entity.Users;
import com.tsb.service.LoginService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component("LoginFilter")
public class LoginFilter implements Filter{
	
	@Autowired
	private LoginService loginService;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("測試filter");

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		String uri = req.getRequestURI();
		if(uri.contains("/login")&& "POST".equals(req.getMethod())) {
			String id = req.getParameter("id");
			System.out.println("id : "+id);
			boolean checkId = loginService.checkId(id);
			boolean validAcc = false;
			if(checkId) {
				Users user = new Users();
				user.setId(id);
				user.setMarkname(req.getParameter("markname"));
				user.setPassword(req.getParameter("password"));
				validAcc = loginService.validAcc(user);
				if(validAcc) {
					System.out.println("Filter : 帳號密碼正確");
					session.setAttribute("valid", true);
					chain.doFilter(request, response);
					return;
				}
				session.setAttribute("valid", false);
				System.out.println("Filter : 帳號密碼有誤");
			}
			session.setAttribute("valid", false);
			System.out.println("Filter : 帳號密碼有誤");
		}
		chain.doFilter(request, response);
	}
}
