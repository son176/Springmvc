package com.shop.controller.admin;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.FormSubmitEvent.MethodType;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.dto.UserDTO;
import com.shop.entity.User;
import com.shop.mapper.UserMapper;
import com.shop.repositories.UserRepositori;
import com.shop.utils.HashUtil;
import com.shop.utils.UploadFile;

@Controller
@RequestMapping(value = "/admin/users")
public class UserControler {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	@Autowired // khởi tạo
	private UserRepositori userRepo;
	@Autowired
	private UserMapper mapper;
	@Autowired
	private UploadFile uploadutil;

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
		Page pageData = this.userRepo.findAll(pageable);
		model.addAttribute("pageData", pageData);
		return "admin/users/index";
	}

	@GetMapping(value = "{id}")
	public String show(Model model, @PathVariable("id") User entity) {
		UserDTO userDTO = this.mapper.convertToDTO(entity);
		model.addAttribute("user", userDTO);
		return "admin/users/show";
	}

	@GetMapping(value = "/create")
	public String create(@ModelAttribute("user") UserDTO user) {
		return "admin/users/create";
	}

	@PostMapping(value = "/store")
	public String store(Model model, @Valid @ModelAttribute("user") UserDTO user, BindingResult result,
			@RequestParam("upload_file") MultipartFile uploadFile) {
		if (uploadFile.getOriginalFilename() == "") {
			result.rejectValue("photo", "errors", "Chưa chọn ảnh");
		}
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "admin/users/create";
		} else {
			User entity = this.mapper.convertToEntity(user);
			String hashpassword = HashUtil.hash(entity.getPassword());
			entity.setPassword(hashpassword);
			File file = uploadutil.handUploadFile(uploadFile);
			entity.setPhoto(file.getName());
			User find = this.userRepo.findByEmail(entity.getEmail());// check email
			session = request.getSession();
			if (find == null) {
				session.setAttribute("sucessfully", "Thêm thành công");
				this.userRepo.save(entity);
			} else {
				session.setAttribute("error", "email không được trùng");
				return "admin/users/create";
			}

			return "redirect:/admin/users";
		}

	}

	@GetMapping(value = "/edit/{id}")
	public String edit(Model model, @PathVariable("id") User entity) {
		UserDTO user = this.mapper.convertToDTO(entity);
		model.addAttribute("user", user);
		return "admin/users/edit";
	}

	@PostMapping(value = "/update/{id}")
	public String update(Model model, @Valid @ModelAttribute("user") UserDTO user, BindingResult result,
			@RequestParam("upload_file") MultipartFile uploadFile, @PathVariable("id") User us) {
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return "admin/users/edit";
		} else {
			User entity = this.mapper.convertToEntity(user);
			User paw = this.userRepo.getById(entity.getId());
			User mail = this.userRepo.searchByEmail(entity.getEmail(), entity.getId());
			session = request.getSession();
			if (uploadFile.getOriginalFilename() != "") {
				File file = uploadutil.handUploadFile(uploadFile);
				entity.setPhoto(file.getName());
			} else {
				entity.setPhoto(us.getPhoto());
			}
			if (mail == null) {
				entity.setPassword(paw.getPassword());
				session.setAttribute("sucessfully", "Cập nhật thành công");
				this.userRepo.save(entity);
			} else {
				session.setAttribute("error", "email đã tồn tại");
				return "admin/users/edit";
			}

			return "redirect:/admin/users";
		}

	}

	@PostMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		session = request.getSession();
		session.setAttribute("status", "xóa thành công");
		this.userRepo.deleteById(id);
		return "redirect:/admin/users";
	}
}
