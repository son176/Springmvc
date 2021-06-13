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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.dto.ProductDTO;
import com.shop.dto.UserDTO;
import com.shop.entity.Product;
import com.shop.entity.User;
import com.shop.mapper.ProductMapper;
import com.shop.mapper.UserMapper;
import com.shop.repositories.UserRepositori;
import com.shop.utils.HashUtil;
import com.shop.utils.UploadFile;

@Controller
@RequestMapping(value="/admin/product")
public class ProductController {
	@Autowired
	private HttpServletRequest request;
	@Autowired //khởi tạo
	private UserRepositori userRepo;
	@Autowired
	private ProductMapper mapper;
	@Autowired
	private UploadFile uploadutil;
	
	
	@GetMapping()
	public String index(Model model)
	{
		String sortBy = request.getParameter("sort_by");
		String sortDirection = request.getParameter("sort_direction");
		String pageParam = request.getParameter("page");
		String limitParam = request.getParameter("limit");
		String sortField = sortBy == null ? "id" : sortBy;
		Sort sort = ( sortDirection == null || sortDirection.equals("asc") ) ?
			Sort.by(Direction.ASC, sortField):
			Sort.by(Direction.DESC, sortField);
		int page = pageParam == null ? 0 : Integer.parseInt(pageParam);
		int limit = limitParam == null ? 2 : Integer.parseInt(limitParam);
		Pageable pageable = PageRequest.of(page, limit, sort);
		Page pageData = this.userRepo.findAll(pageable);
		model.addAttribute("pageData", pageData);
		return "admin/products/index";
	}
//
//	@GetMapping(value="{id}")
//	public String show(
//		Model model,
//		@PathVariable("id") Product entity
//	) {
//		ProductDTO proDTO =this.mapper.convertToDTO(entity);
//		model.addAttribute("user", proDTO);
//		return "admin/users/show";
//	}
//
//	@GetMapping(value="/create")
//	public String create(
//			@ModelAttribute("user") UserDTO user
//	)
//	{
//		return "admin/users/create";
//	}
//
//	@PostMapping(value="/store")
//	public String store(
//			Model model,
//			@Valid @ModelAttribute("user") UserDTO user,
//			BindingResult result,
//			@RequestParam("upload_file") MultipartFile uploadFile
//			)
//	{  
//		if(result.hasErrors()) {
//			System.out.println(result);
//			model.addAttribute("errors", result.getAllErrors());
//			return "admin/users/create";
//		}else {
//			User entity = this.mapper.convertToEntity(user);
//			String hashpassword =HashUtil.hash(entity.getPassword());
//			entity.setPassword(hashpassword);
//			this.uploadutil.handUploadFile(uploadFile);
//			entity.setPhoto(uploadFile.getOriginalFilename());
//			User find =this.userRepo.findByEmail(entity.getEmail());//check email
//			HttpSession session = request.getSession();
//			if(find==null) {
//				this.userRepo.save(entity);
//			}else {
//				session.setAttribute("error", "email không được trùng");
//				return "admin/users/create";
//			}
//			
//			return "redirect:/admin/users";
//		}
//		
//	}
//
//	@GetMapping(value="/edit/{id}")
//	public String edit(Model model, @PathVariable("id") User entity)
//	{
//		UserDTO user =this.mapper.convertToDTO(entity);
//		model.addAttribute("user", user);
//		return "admin/users/edit";
//	}
//
//	@PostMapping(value="/update/{id}")
//	public String update(
//		Model model,
//		@Valid @ModelAttribute("user") UserDTO user,
//		BindingResult result,
//		@RequestParam("upload_file") MultipartFile uploadFile
//	) {
//		if(result.hasErrors()) {
//			model.addAttribute("errors", result.getAllErrors());
//			return "admin/users/edit";
//		}else {
//			User entity =this.mapper.convertToEntity(user);
//			this.uploadutil.handUploadFile(uploadFile);
//			entity.setPhoto(uploadFile.getOriginalFilename());
//			this.userRepo.save(entity);
//			return "redirect:/admin/users";
//		}
//		
//	}
//
//	@PostMapping(value="/delete/{id}")
//	public String delete(@PathVariable("id") Integer id)
//	{
//		this.userRepo.deleteById(id);
//		return "redirect:/admin/users";
//	}
}
