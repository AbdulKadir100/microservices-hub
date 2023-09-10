package com.springbootrestapi.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.springbootrestapi.dto.UserDto;
import com.springbootrestapi.entity.User;
import com.springbootrestapi.exception.EmailAlreadyExistsException;
import com.springbootrestapi.exception.ResourceNotFoundException;
import com.springbootrestapi.repository.UserRepository;
import com.springbootrestapi.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	

	@Override
	public UserDto createUser(UserDto userDto) {
		
		Optional<User> optional = userRepository.findByEmail(userDto.getEmail());
		
		if(optional.isPresent()) {
			throw new EmailAlreadyExistsException("Email Already Exist for the User");
		}
		
		User user = modelMapper.map(userDto, User.class);
		User savedUser =  userRepository.save(user);
		return modelMapper.map(savedUser, UserDto.class);
	}
	
	
	@Override
	public UserDto getById(Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User","id ",id));
		return mapToDTO(user);
	}
	

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users =  userRepository.findAll();
		return users.stream().map((user) -> mapToDTO(user)).collect(Collectors.toList());
	}


	@Override
	public UserDto updateUser(UserDto userDto) {

		User user = mapToEntity(userDto);
		
		User existingUser = userRepository.findById(user.getId()).orElseThrow(
				() -> new ResourceNotFoundException("User","id ",user.getId()));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		
		User updatedUser = userRepository.save(existingUser);
		
		return mapToDTO(updatedUser);
	}


	@Override
	public void deleteUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User","id ",id));
		userRepository.delete(user);
	}
	
	// entity to Data transfer object
	private UserDto mapToDTO(User user) {
		return modelMapper.map(user, UserDto.class); 
	}

	private User mapToEntity(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
		
	}


	

}
