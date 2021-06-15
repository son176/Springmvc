package com.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.shop.entity.User;

public interface UserRepositori extends JpaRepository<User, Integer> {
	@Query("SELECT entity FROM User entity WHERE email=:email")
	public User findByEmail(@Param("email") String email);
	@Query("SELECT entity FROM User entity WHERE entity.email=:email AND entity.id <> :id")
    public User searchByEmail(@Param("email") String email, @Param("id") Integer id);
}
