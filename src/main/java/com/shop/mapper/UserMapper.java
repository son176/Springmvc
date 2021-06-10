package com.shop.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dto.UserDTO;
import com.shop.entity.User;



@Service
public class UserMapper {
	@Autowired
	public  ModelMapper mapper;

	public  User convertToEntity(UserDTO userDTO) {
		User entity = mapper.map(userDTO,User.class);
				return entity;
	}
	
	public  UserDTO convertToDTO(User user) {
		UserDTO userDTO = mapper.map(user, UserDTO.class);
		return userDTO;
	}
}
