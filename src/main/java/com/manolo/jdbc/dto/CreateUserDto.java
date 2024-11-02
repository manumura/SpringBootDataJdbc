package com.manolo.jdbc.dto;

import java.time.LocalDate;

public record CreateUserDto(
        String username,
        String password,
        String firstName,
        String lastName,
        String emailAddress,
        LocalDate birthdate
){
    public CreateUserDto withPassword(String password) {
        return new CreateUserDto(username, password, firstName, lastName, emailAddress, birthdate);
    }
}
