package com.shop.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dto.CategoryDTO;
import com.shop.entity.Category;



@Service
public class CategoryMapper {
	@Autowired
	public ModelMapper mapper;

	public Category convertToEntity(CategoryDTO cateDTO) {
		Category entity = mapper.map(cateDTO, Category.class);
		return entity;
	}

	public CategoryDTO convertToDTO(Category cate) {
		CategoryDTO cateDTO = mapper.map(cate, CategoryDTO.class);
		return cateDTO;
	}
}
