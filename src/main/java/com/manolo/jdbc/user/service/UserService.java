package com.manolo.jdbc.user.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import com.manolo.jdbc.user.mapper.AuthorityMapper;
import com.manolo.jdbc.user.mapper.UserMapper;
import com.manolo.jdbc.user.dto.AuthorityDto;
import com.manolo.jdbc.user.dto.CreateUserDto;
import com.manolo.jdbc.user.dto.UpdateUserDto;
import com.manolo.jdbc.user.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.manolo.jdbc.user.entity.Authority;
import com.manolo.jdbc.user.entity.User;
import com.manolo.jdbc.user.repository.AuthorityRepository;
import com.manolo.jdbc.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;
	private final UserMapper userMapper = UserMapper.MAPPER;
	private final AuthorityMapper authorityMapper = AuthorityMapper.MAPPER;
	private final PasswordEncoder passwordEncoder;
	
	public List<UserDto> getUsers() {
		var users = userRepository.findAll();
        return users.stream()
                .map(this::toUserDtoWithAuthorities)
                .toList();
	}
	
	public UserDto getUsersById(Integer id) {
		var user = userRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("User not found")
		);
		return toUserDtoWithAuthorities(user);
	}
	
	public UserDto createUser(CreateUserDto createUserDto) {
		var encodedPassword = passwordEncoder.encode(createUserDto.password());
		var createUserDtoWithPassword = createUserDto.withPassword(encodedPassword);
		var user = userMapper.toUser(createUserDtoWithPassword);
		var userCreated = userRepository.save(user);
		return userMapper.toUserDto(userCreated);
	}

	public UserDto updateUser(Integer id, UpdateUserDto updateUserDto) {
		var userToUpdate = userRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("User not found")
		);
		var encodedPassword = passwordEncoder.encode(updateUserDto.password());
		var updateUserDtoWithPassword = updateUserDto.withPassword(encodedPassword);
		var user = userMapper.toUser(userToUpdate, updateUserDtoWithPassword);
		var userUpdated = userRepository.save(user);
		return toUserDtoWithAuthorities(userUpdated);
	}

	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}
	
	public UserDto addAuthority(Integer id, AuthorityDto authorityDto) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("User not found")
		);
		Authority authority = authorityRepository.findByAuthority(authorityDto.authority()).orElseThrow(
				() -> new NoSuchElementException("Authority not found")
		);
		if (user.getAuthorityIds().contains(authority.id())) {
			throw new IllegalArgumentException("User already has this authority");
		}
		user.addAuthorityRef(authority.id());
		User userUpdated = userRepository.save(user);
		return toUserDtoWithAuthorities(userUpdated);
	}
	
	public UserDto removeAuthorities(Integer id, AuthorityDto authorityDto) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("User not found")
		);
		Authority authority = authorityRepository.findByAuthority(authorityDto.authority()).orElseThrow(
				() -> new NoSuchElementException("Authority not found")
		);
		if (!user.getAuthorityIds().contains(authority.id())) {
			throw new IllegalArgumentException("User does not have this authority");
		}
		user.removeAuthorityRef(authority.id());
		User userUpdated = userRepository.save(user);
		return toUserDtoWithAuthorities(userUpdated);
	}

	private UserDto toUserDtoWithAuthorities(User user) {
		var authorityIds = user.getAuthorityIds();
		var authorities = authorityRepository.findAllById(authorityIds);
		var authorityDtos = authorityMapper.toAuthorityDtos(Set.copyOf(authorities));
        return userMapper.toUserDto(user, authorityDtos);
	}
}
