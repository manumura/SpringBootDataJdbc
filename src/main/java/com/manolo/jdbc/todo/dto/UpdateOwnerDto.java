package com.manolo.jdbc.todo.dto;

public record UpdateOwnerDto(
        String fullName,
        String email,
        String username,
        AddressDto address
) {
}
