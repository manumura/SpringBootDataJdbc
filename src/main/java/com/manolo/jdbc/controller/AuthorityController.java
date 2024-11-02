package com.manolo.jdbc.controller;

import com.manolo.jdbc.dto.AuthorityDto;
import com.manolo.jdbc.dto.CreateAuthorityDto;
import com.manolo.jdbc.dto.UpdateAuthorityDto;
import com.manolo.jdbc.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authorities")
@RequiredArgsConstructor
public class AuthorityController {

	private final AuthorityService authorityService;
	
	@GetMapping
	public List<AuthorityDto> getAuthorities() {
		return authorityService.getAuthorities();
	}
	
	@GetMapping("/{id}")
	public AuthorityDto getAuthorityById(@PathVariable("id") Integer id) {
		return authorityService.getAuthorityById(id);
	}
	
	@PostMapping
	public AuthorityDto createAuthority(@RequestBody CreateAuthorityDto createAuthorityDto) {
		return authorityService.createAuthority(createAuthorityDto);
	}
	
	@PutMapping("/{id}")
	public AuthorityDto updateAuthorities(@PathVariable("id") Integer id, @RequestBody UpdateAuthorityDto updateAuthorityDto) {
		return authorityService.updateAuthority(id, updateAuthorityDto);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteAuthorities(@PathVariable("id") Integer id) {
		authorityService.deleteAuthority(id);
	}
	
}
