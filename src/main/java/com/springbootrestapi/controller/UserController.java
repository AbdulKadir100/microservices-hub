package com.springbootrestapi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.springbootrestapi.dto.UserDto;
import com.springbootrestapi.entity.User;
import com.springbootrestapi.exception.ErrorDetails;
import com.springbootrestapi.exception.ResourceNotFoundException;
import com.springbootrestapi.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
	
	private UserService userService;
	
	// build create user REST API
	// http://localhost:8080/api/users
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.createUser(userDto),HttpStatus.CREATED);
	}
	
	// build get single user REST API
	// http://localhost:8080/api/users/1
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
		return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
	}
	
	// build get all users REST API
	// http://localhost:8080/api/users
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users = userService.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	// update user
	// http://localhost:8080/api/users/id
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @PathVariable(name = "id") Long userId, @RequestBody UserDto userDto){
		userDto.setId(userId);
		UserDto updatedUser = userService.updateUser(userDto);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
	}

	// delete user REST API
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@Valid @PathVariable(name = "id") Long userId){
		userService.deleteUser(userId);
		return new ResponseEntity<>("User deleted successfully!",HttpStatus.OK);
    }
	
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
//			ResourceNotFoundException exception, WebRequest request){
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
//				request.getDescription(false), "USER_NOT_FOUND");
//		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//		
//	}
//	
}
