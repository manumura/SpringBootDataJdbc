package com.manolo.jdbc.dto;

public record UpdateOwnerDto(
        String fullName,
        String email,
        String username,
        AddressDto address
) {
}
