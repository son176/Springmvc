package com.shop.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.dto.UserDTO;
import com.shop.entity.User;
import com.shop.repositories.UserRepositori;
import com.shop.utils.CookieUtils;
import com.shop.utils.HashUtil;
import com.shop.utils.LoginUtils;

@Controller
public class Logincontroler {
	@Autowired
	private UserRepositori userRepo;
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@GetMapping("/login")
	public String getLoginForm(@ModelAttribute("user") UserDTO user) {
		if(LoginUtils.getRole(request) == "admin") {
			return "redirect:/admin/user";
		}
		if(LoginUtils.getRole(request) == "user") {
			return "redirect:/home";
		}
		return "/auth/login";
	}
	
	@PostMapping("/login")
	public String login(
		@RequestParam("email") String email,
		@RequestParam("password") String password,
		@RequestParam(value="remember" ,required = false) String remember
	) {
		User entity = this.userRepo.findByEmail(email);
		HttpSession session = request.getSession();
		if (entity == null) {
			session.setAttribute("error", "Sai email hoặc mật khẩu");
			return "redirect:/login";
		}
		if( entity.getActivated() == 0) {
			session.setAttribute("error", "Tài khoản bị khóa liên hệ 0392567806");
			return "redirect:/login";
		}

		boolean checkPwd = HashUtil.verify(password, entity.getPassword());
		if (!checkPwd) {
			session.setAttribute("error", "Sai email hoặc mật khẩu");
			return "redirect:/login";
		}
		
		if(remember != null) {
            CookieUtils.addCookie("JSESSIONID", session.getId() , 24, response);
        }else {
            CookieUtils.addCookie("JSESSIONID", session.getId() , -1, response);
        }
		session.setAttribute("isLogin", true);
		session.setAttribute("user", entity);
		if(entity.getAdmin() == 1) {
			return "redirect:/admin/users";
		}
		return "redirect:/home";
	}
}
