package com.manolo.jdbc.user.mapper;

import com.manolo.jdbc.user.dto.AuthorityDto;
import com.manolo.jdbc.user.dto.CreateUserDto;
import com.manolo.jdbc.user.dto.UpdateUserDto;
import com.manolo.jdbc.user.dto.UserDto;
import com.manolo.jdbc.user.entity.User;
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
