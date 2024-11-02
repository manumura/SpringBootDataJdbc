package com.manolo.jdbc.mapper;

import com.manolo.jdbc.dto.CreateOwnerDto;
import com.manolo.jdbc.dto.OwnerDto;
import com.manolo.jdbc.dto.UpdateOwnerDto;
import com.manolo.jdbc.entity.Address;
import com.manolo.jdbc.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OwnerMapper {

    OwnerMapper MAPPER = Mappers.getMapper( OwnerMapper.class );

    OwnerDto toOwnerDto(Owner owner);

    OwnerDto toOwnerDto(Owner owner, Address address);

    List<OwnerDto> toOwnerDtos(List<Owner> owners);

    Owner toOwner(CreateOwnerDto createOwnerDto);

    default Owner toOwner(Owner ownerToUpdate, UpdateOwnerDto updateOwnerDto) {
        return new Owner(
                ownerToUpdate.id(),
                updateOwnerDto.fullName(),
                updateOwnerDto.username(),
                updateOwnerDto.email(),
                ownerToUpdate.addressRef()
        );
    }
}
