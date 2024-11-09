package com.manolo.jdbc.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record UserDto(
        Integer id,
        String username,
        String password,
        Boolean accountNonExpired,
        Boolean accountNonLocked,
        Boolean credentialsNonExpired,
        Boolean enabled,
        String firstName,
        String lastName,
        String emailAddress,
        LocalDate birthdate,
        Set<AuthorityDto> authorities
){
    public UserDto {
        if (authorities == null) {
            authorities = new HashSet<>();
        }
    }

    public void addAllAuthorities(Set<AuthorityDto> authorities) {
        this.authorities.addAll(authorities);
    }
}
