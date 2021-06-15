package com.shop.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dto.ProductDTO;
import com.shop.entity.Product;



@Service
public class ProductMapper {
	@Autowired
	public ModelMapper mapper;

	public Product convertToEntity(ProductDTO proDTO) {
		Product entity = mapper.map(proDTO, Product.class);
		return entity;
	}
	public ProductDTO convertToDTO(Product pro) {
		ProductDTO proDTO = mapper.map(pro, ProductDTO.class);
		return proDTO;
	}
}
