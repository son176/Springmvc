package com.shop.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotNull
	@NotBlank
	private String name;
	@NotNull
	@NotBlank
	private String image;
	@NotNull
	private Integer price;
	@NotNull
	private Date create_date;
	@NotNull
	private Integer avaliable;
}
