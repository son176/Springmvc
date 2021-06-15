package com.shop.controller.admin;

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

import com.shop.dto.CategoryDTO;
import com.shop.entity.Category;
import com.shop.mapper.CategoryMapper;
import com.shop.repositories.CategoryRepository;
import com.shop.utils.UploadFile;

@Controller
@RequestMapping(value ="/admin/categories")
public class CategoryControler {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private CategoryMapper mapper;

	@GetMapping()
	public String index(Model model) {
		String sortBy = request.getParameter("sort_by");
		String sortDirection = request.getParameter("sort_direction");
		String pageParam = request.getParameter("page");
		String limitParam = request.getParameter("limit");
		String sortField = sortBy == null ? "id" : sortBy;
		Sort sort = (sortDirection == null || sortDirection.equals("asc")) ? 
				 Sort.by(Direction.ASC, sortField)
				: Sort.by(Direction.DESC, sortField);
		int page = pageParam == null ? 0 : Integer.parseInt(pageParam);
		int limit = limitParam == null ? 10 : Integer.parseInt(limitParam);
		Pageable pageable = PageRequest.of(page, limit, sort);
		Page pageData = this.categoryRepo.findAll(pageable);
		model.addAttribute("pageData", pageData);
		return "admin/category/index";
	}

	@GetMapping(value = "/create")
	public String create(@ModelAttribute("category") CategoryDTO category) {
		return "admin/category/create";
	}

	@PostMapping(value = "/store")
	public String store(Model model, @Valid @ModelAttribute("category") CategoryDTO category, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "admin/category/create";
		} else {
			Category entity = this.mapper.convertToEntity(category);
			Category find = this.categoryRepo.findByName(entity.getName());
			session = request.getSession();
			if (find == null) {
				session.setAttribute("sucessfully", "Thêm thành công");
				this.categoryRepo.save(entity);
			} else {
				session.setAttribute("error", "Tên không được trùng");
				return "admin/category/create";
			}
			return "redirect:/admin/categories";
		}
	}

	@GetMapping(value = "/edit/{id}")
	public String edit(Model model, @PathVariable("id") Category entity) {
		CategoryDTO category = this.mapper.convertToDTO(entity);
		model.addAttribute("category", category);
		return "admin/category/edit";
	}

	@PostMapping(value = "/update/{id}")
	public String update(Model model, @Valid @ModelAttribute("category") CategoryDTO category, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "admin/category/edit";
		} else {
			Category entity = this.mapper.convertToEntity(category);
			Category find = this.categoryRepo.searchByName(entity.getName(), entity.getId());
			if (find == null) {
				session.setAttribute("sucessfully", "Sửa thành công");
				this.categoryRepo.save(entity);
			} else {
				session.setAttribute("error", "Bạn từng tạo một Category có tên như trên");
				return "admin/category/create";
			}
			return "redirect:/admin/categories";
		}

	}

	@PostMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		session = request.getSession();
		session.setAttribute("status", "xóa thành công");
		this.categoryRepo.deleteById(id);
		return "redirect:/admin/categories";
	}
}
