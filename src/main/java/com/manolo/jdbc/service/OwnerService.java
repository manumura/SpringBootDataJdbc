package com.manolo.jdbc.service;

import com.manolo.jdbc.dto.CreateOwnerDto;
import com.manolo.jdbc.dto.OwnerDto;
import com.manolo.jdbc.dto.UpdateOwnerDto;
import com.manolo.jdbc.entity.Address;
import com.manolo.jdbc.mapper.OwnerMapper;
import com.manolo.jdbc.repository.AddressRepository;
import com.manolo.jdbc.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {

	private final OwnerRepository ownerRepository;
	private final AddressRepository addressRepository;
	private final OwnerMapper ownerMapper = OwnerMapper.MAPPER;
	
	public List<OwnerDto> getOwners() {
		var owners = ownerRepository.findAll();
		return owners.stream().map(
				owner -> {
					var address = Optional.ofNullable(owner.addressRef())
							.map(AggregateReference::getId)
							.flatMap(addressRepository::findById)
							.orElse(null);
					return ownerMapper.toOwnerDto(owner, address);
				}
		).toList();
	}
	
	public OwnerDto getOwnerById(@PathVariable("id") Integer id) {
		var owner = ownerRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("Owner not found")
		);
		var address = Optional.ofNullable(owner.addressRef())
				.map(AggregateReference::getId)
				.flatMap(addressRepository::findById)
//				.or(Optional::empty)
				.orElse(null);
		return ownerMapper.toOwnerDto(owner, address);
	}

	@Transactional
	public OwnerDto createOwner(CreateOwnerDto createOwnerDto) {
		var addressLine = Optional.ofNullable(createOwnerDto.address().addressLine()).orElseThrow(
				() -> new IllegalArgumentException("Address is required")
		);
		var address = addressRepository.save(new Address(null, addressLine));
		// TODO test rollback address
		if (createOwnerDto.address().addressLine().equals("rollback")) {
			throw new IllegalArgumentException("rollback address");
		}
		var owner = ownerMapper.toOwner(createOwnerDto).withAddressRef(address.id());
		var ownerCreated = ownerRepository.save(owner);
		// TODO test rollback address + owner
		if (createOwnerDto.username().equals("rollback")) {
			throw new IllegalArgumentException("rollback owner");
		}
		return ownerMapper.toOwnerDto(ownerCreated, address);
	}

	@Transactional
	public OwnerDto updateOwner(Integer id, UpdateOwnerDto updateOwnerDto) {
		var owner = ownerRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("Owner not found")
		);
		var address = Optional.ofNullable(owner.addressRef())
				.map(AggregateReference::getId)
				.flatMap(addressRepository::findById)
				.orElseThrow(
						() -> new NoSuchElementException("Address not found")
				);
		if (updateOwnerDto.address().addressLine() != null) {
			address = new Address(address.id(), updateOwnerDto.address().addressLine());
			addressRepository.save(address);
		}
		var ownerToUpdate = ownerMapper.toOwner(owner, updateOwnerDto);
		var ownerUpdated = ownerRepository.save(ownerToUpdate);
		return ownerMapper.toOwnerDto(ownerUpdated, address);
	}
	
	public void deleteOwner(Integer id) {
		var owner = ownerRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("Owner not found")
		);
		if (owner.addressRef() != null && owner.addressRef().getId() != null) {
			addressRepository.deleteById(owner.addressRef().getId());
		}
		ownerRepository.deleteById(id);
	}
}
