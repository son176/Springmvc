package com.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.shop.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	@Query("SELECT entity FROM Category entity WHERE name=:name")
	public Category findByName(@Param("name") String name);

	@Query("SELECT entity FROM Category entity WHERE entity.name=:name AND entity.id <> :id")
	public Category searchByName(@Param("name") String name, @Param("id") Integer id);
}
