package com.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Product;



public interface ProductRepositories  extends JpaRepository<Product, Integer> {

}
