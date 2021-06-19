package com.shop.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.shop.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private Integer id;
	@NotBlank
	private String name;
	@NotBlank
	private String image;
	@NotNull
	@Min(0)
	private Double price;

	private String create_date;
	
	private Integer avaliable;

	private Category category;
}
