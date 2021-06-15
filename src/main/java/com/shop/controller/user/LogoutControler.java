package com.shop.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LogoutControler {
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping("/logout")
	public String logoout() {
		request.getSession().removeAttribute("user");
		request.getSession().setAttribute("isLogin", false);
		return "redirect:/login";
	}
}
