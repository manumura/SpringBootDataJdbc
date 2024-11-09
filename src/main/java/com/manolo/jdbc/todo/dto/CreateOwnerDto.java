package com.manolo.jdbc.todo.dto;

public record CreateOwnerDto(
        String fullName,
        String email,
        String username,
        AddressDto address
) {
}
