package com.manolo.jdbc.service;

import com.manolo.jdbc.dto.AuthorityDto;
import com.manolo.jdbc.dto.CreateAuthorityDto;
import com.manolo.jdbc.dto.UpdateAuthorityDto;
import com.manolo.jdbc.mapper.AuthorityMapper;
import com.manolo.jdbc.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthorityService {
	
	private final AuthorityRepository authorityRepository;
	private final AuthorityMapper authorityMapper = AuthorityMapper.MAPPER;

	public List<AuthorityDto> getAuthorities() {
		var authorities = authorityRepository.findAll();
		return authorityMapper.toAuthorityDtos(authorities);
	}
	
	public AuthorityDto getAuthorityById(Integer id) {
		var authority = authorityRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("Authority not found")
		);
		return authorityMapper.toAuthorityDto(authority);
	}
	
	public AuthorityDto createAuthority(CreateAuthorityDto createAuthorityDto) {
		var authority = authorityMapper.toAuthority(createAuthorityDto);
		var authorityCreated = authorityRepository.save(authority);
		return authorityMapper.toAuthorityDto(authorityCreated);
	}
	
	public AuthorityDto updateAuthority(Integer id, UpdateAuthorityDto updateAuthorityDto) {
		var authorityToUpdate = authorityRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("Authority not found")
		);
		var authority = authorityMapper.toAuthority(authorityToUpdate, updateAuthorityDto);
		var authorityUpdated = authorityRepository.save(authority);
		return authorityMapper.toAuthorityDto(authorityUpdated);
	}
	
	public void deleteAuthority(Integer id) {
		authorityRepository.deleteById(id);
	}
}
