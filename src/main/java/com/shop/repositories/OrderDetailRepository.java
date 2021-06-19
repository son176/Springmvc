package com.shop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.shop.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer > {
	@Query("SELECT entity.product, COUNT(entity.quantity) AS total FROM OrderDetail entity GROUP BY entity.product ORDER BY COUNT(entity.quantity) DESC")
	public List<Object[]> getList();
}
