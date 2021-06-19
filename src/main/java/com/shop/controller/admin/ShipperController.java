package com.shop.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.entity.User;
import com.shop.repositories.UserRepositori;


@Controller
@RequestMapping(value="/admin/order")
public class ShipperController {

	@Autowired
	private UserRepositori oPo;

	@GetMapping()
	public String home(Model model) {
		List<User> list = oPo.findAll();
		model.addAttribute("listod", list);
		return "admin/product/order";
	}

}
