package com.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
