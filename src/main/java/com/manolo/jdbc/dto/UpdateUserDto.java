package com.manolo.jdbc.dto;

import java.time.LocalDate;

public record UpdateUserDto(
        String username,
        String password,
        String firstName,
        String lastName,
        String emailAddress,
        LocalDate birthdate
){
    public UpdateUserDto withPassword(String password) {
        return new UpdateUserDto(username, password, firstName, lastName, emailAddress, birthdate);
    }
}
