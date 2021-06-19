package com.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.entity.Order;

@Configuration
public class EntityConfig {
	@Bean("newOrder")
	public Order getOrder() {
		return new Order();
	}
}
