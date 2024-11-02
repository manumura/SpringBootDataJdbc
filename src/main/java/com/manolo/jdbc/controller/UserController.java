package com.manolo.jdbc.controller;

import com.manolo.jdbc.dto.AuthorityDto;
import com.manolo.jdbc.dto.CreateUserDto;
import com.manolo.jdbc.dto.UpdateUserDto;
import com.manolo.jdbc.dto.UserDto;
import com.manolo.jdbc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping
	public List<UserDto> getUsers() {
		return userService.getUsers();
	}
	
	@GetMapping("/{id}")
	public UserDto getUserById(@PathVariable("id") Integer id) {
		return userService.getUsersById(id);
	}
	
	@PostMapping
	public UserDto createUser(@RequestBody CreateUserDto createUserDto) {
		return userService.createUser(createUserDto);
	}

	@PutMapping("/{id}")
	public UserDto updateUser(@PathVariable Integer id, @RequestBody UpdateUserDto updateUserDto) {
		return userService.updateUser(id, updateUserDto);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
	}
	
	@PutMapping("/{id}/add")
	public UserDto addAuthority(@PathVariable("id") Integer id, @RequestBody AuthorityDto authorityDto) {
		return userService.addAuthority(id, authorityDto);
	}
	
	@PutMapping("/{id}/remove")
	public UserDto removeAuthority(@PathVariable("id") Integer id, @RequestBody AuthorityDto authorityDto) {
		return userService.removeAuthorities(id, authorityDto);
	}
}
