package com.manolo.jdbc.dto;

public record CreateOwnerDto(
        String fullName,
        String email,
        String username,
        AddressDto address
) {
}
