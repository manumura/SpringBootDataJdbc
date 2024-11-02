package com.manolo.jdbc.mapper;

import com.manolo.jdbc.dto.AuthorityDto;
import com.manolo.jdbc.dto.CreateUserDto;
import com.manolo.jdbc.dto.UpdateUserDto;
import com.manolo.jdbc.dto.UserDto;
import com.manolo.jdbc.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper( UserMapper.class );

    UserDto toUserDto(User user);

    UserDto toUserDto(User user, Set<AuthorityDto> authorities);

    User toUser(CreateUserDto createUserDto);

    default User toUser(User userToUpdate, UpdateUserDto updateUserDto) {
        return new User(
                userToUpdate.id(),
                updateUserDto.username(),
                updateUserDto.password(),
                userToUpdate.accountNonExpired(),
                userToUpdate.accountNonLocked(),
                userToUpdate.credentialsNonExpired(),
                userToUpdate.enabled(),
                updateUserDto.firstName(),
                updateUserDto.lastName(),
                updateUserDto.emailAddress(),
                updateUserDto.birthdate(),
                userToUpdate.authorityRefs()
        );
    }
}
