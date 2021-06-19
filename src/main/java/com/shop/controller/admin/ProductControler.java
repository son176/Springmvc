package com.shop.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.dto.ProductDTO;
import com.shop.entity.Category;
import com.shop.entity.Product;
import com.shop.mapper.ProductMapper;
import com.shop.repositories.CategoryRepository;
import com.shop.repositories.ProductRepository;
import com.shop.utils.DateFormat;

@Controller
@RequestMapping(value = "/admin/product")
public class ProductControler {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private ProductMapper mapper;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private DateFormat dateformat;

	@ModelAttribute("listCate")
	public List<Category> getCategory() {
		List<Category> list = categoryRepo.findAll();
		return list;
	}
	
	@ModelAttribute("getNow")
	public String getNow() {
		Date now = new Date();
		String dateStr = dateformat.toString(now, "yyyy-MM-dd");
		return dateStr;
	}

	@GetMapping()
	public String index(Model model) {
		String sortBy = request.getParameter("sort_by");
		String sortDirection = request.getParameter("sort_direction");
		String pageParam = request.getParameter("page");
		String limitParam = request.getParameter("limit");
		String sortField = sortBy == null ? "id" : sortBy;
		Sort sort = (sortDirection == null || sortDirection.equals("asc")) ? Sort.by(Direction.ASC, sortField)
				: Sort.by(Direction.DESC, sortField);
		int page = pageParam == null ? 0 : Integer.parseInt(pageParam);
		int limit = limitParam == null ? 10 : Integer.parseInt(limitParam);
		Pageable pageable = PageRequest.of(page, limit, sort);
		Page pageData = this.productRepo.findAll(pageable);
		model.addAttribute("pageData", pageData);
		return "admin/product/index";
	}

	@GetMapping(value = "/create")
	public String create(@ModelAttribute("product") ProductDTO product, Model model

	) {
		return "admin/product/create";
	}

	@PostMapping(value = "/store")
	public String store(Model model, @Valid @ModelAttribute("product") ProductDTO product, BindingResult result

	) {
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "admin/product/create";
		} else {
			Product entity = this.mapper.convertToEntity(product);
			session = request.getSession();
			session.setAttribute("sucessfully", "Thêm thành công");
			this.productRepo.save(entity);
			return "redirect:/admin/product";
		}
	}

	@GetMapping(value = "/edit/{id}")
	public String edit(Model model, @PathVariable("id") Product entity) {
		List<Category> listCate = this.categoryRepo.findAll();
		model.addAttribute("listCate", listCate);
		ProductDTO product = this.mapper.convertToDTO(entity);
		model.addAttribute("product", product);
		return "admin/product/edit";
	}

	@PostMapping(value = "/update/{id}")
	public String update(Model model, @Valid @ModelAttribute("product") ProductDTO product, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "admin/product/edit";
		} else {
			Product entity = this.mapper.convertToEntity(product);
			session.setAttribute("sucessfully", "Sửa thành công");
			this.productRepo.save(entity);
			return "redirect:/admin/product";
		}

	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		session = request.getSession();
		session.setAttribute("status", "Xóa thành công");
		this.productRepo.deleteById(id);
		return "redirect:/admin/product";
	}
}
