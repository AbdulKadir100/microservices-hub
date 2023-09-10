package com.springbootrestapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private Long id;
	
	@NotEmpty(message = "User first name should not be null or empty")
	private String firstName;
	@NotEmpty(message = "User last name should not be null or empty")
	private String lastName;
	@NotEmpty(message = "User email address should not be null or empty")
	@Email
	private String email;

}
