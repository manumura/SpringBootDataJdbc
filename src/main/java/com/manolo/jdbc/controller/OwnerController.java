package com.manolo.jdbc.controller;

import com.manolo.jdbc.dto.CreateOwnerDto;
import com.manolo.jdbc.dto.OwnerDto;
import com.manolo.jdbc.dto.UpdateOwnerDto;
import com.manolo.jdbc.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

	private final OwnerService ownerService;
	
	@GetMapping
	public List<OwnerDto> getOwners() {
		return ownerService.getOwners();
	}
	
	@GetMapping("/{id}")
	public OwnerDto getOwnerById(@PathVariable("id") Integer id) {
		return ownerService.getOwnerById(id);
	}
	
	@PostMapping
	public OwnerDto createOwner(@RequestBody CreateOwnerDto createOwnerDto) {
		return ownerService.createOwner(createOwnerDto);
	}
	
	@PutMapping("/{id}")
	public OwnerDto updateOwner(@PathVariable("id") Integer id, @RequestBody UpdateOwnerDto updateOwnerDto) {
		return ownerService.updateOwner(id, updateOwnerDto);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteOwner(@PathVariable("id") Integer id) {
		ownerService.deleteOwner(id);
	}
}
