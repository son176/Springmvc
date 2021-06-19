package com.shop.dto;

import com.shop.entity.Order;
import com.shop.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
	private Integer id;

	private Integer quantity;

	private Double price;

	private Order order;

	private Product product;
}
