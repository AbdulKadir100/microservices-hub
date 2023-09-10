package com.springbootrestapi.service;

import java.util.List;

import com.springbootrestapi.dto.UserDto;

public interface UserService {
	UserDto createUser(UserDto user);
	List<UserDto> getAllUsers();
	UserDto getById(Long id);
	UserDto updateUser(UserDto userDto);
	void deleteUser(Long id);

}
