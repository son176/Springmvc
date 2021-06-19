package com.shop.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.Service.UserProductSevice;
import com.shop.dto.OrderDTO;
import com.shop.entity.Order;

@Controller
@RequestMapping("/user/cart")
public class UserCartController {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserProductSevice userProductSevice;

	@GetMapping
	public String show(@ModelAttribute("order") Order order, Model model) {
		Double total = userProductSevice.getTotal(request);
		model.addAttribute("total", total);
		return "user/cart";
	}

	@GetMapping(value = "/pay", params = "address")
	public String pay(Model model, @Valid @ModelAttribute("order") OrderDTO od, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			Double total = userProductSevice.getTotal(request);
			model.addAttribute("total", total);
			return "user/cart";
		} else {
		 HttpSession session = request.getSession();
			session.setAttribute("status", "Thanh toán thành công");
			Order order = userProductSevice.saveOrder(request, od.getAddress());
			model.addAttribute("order", order);
			return "redirect:/user/cart";
		}
	}

	@GetMapping("/clear")
	public String clear(@ModelAttribute("order") OrderDTO o, Model model) {
		userProductSevice.removeAll(request);
		Double total = userProductSevice.getTotal(request);
		model.addAttribute("total", total);
		return "user/cart";
	}
}
