package com.shop.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.shop.utils.CookieUtils;


@Controller
public class LogoutControler {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	@GetMapping("/logout")
	public String logoout() {
		request.getSession().removeAttribute("user");
		CookieUtils.addCookie("email", null, 0, response);
		request.getSession().setAttribute("isLogin", false);
		request.getSession().removeAttribute("listOrderDetails");
		return "redirect:/login";
	}
}
