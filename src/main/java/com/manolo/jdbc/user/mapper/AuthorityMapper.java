package com.manolo.jdbc.user.mapper;

import com.manolo.jdbc.user.dto.AuthorityDto;
import com.manolo.jdbc.user.dto.CreateAuthorityDto;
import com.manolo.jdbc.user.dto.UpdateAuthorityDto;
import com.manolo.jdbc.user.entity.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface AuthorityMapper {

    AuthorityMapper MAPPER = Mappers.getMapper( AuthorityMapper.class );

    AuthorityDto toAuthorityDto(Authority authority);

    Set<AuthorityDto> toAuthorityDtos(Set<Authority> authorities);

    List<AuthorityDto> toAuthorityDtos(List<Authority> authorities);

    Authority toAuthority(CreateAuthorityDto createAuthorityDto);

    default Authority toAuthority(Authority authorityToUpdate, UpdateAuthorityDto updateAuthorityDto) {
        return new Authority(
                authorityToUpdate.id(),
                updateAuthorityDto.authority()
        );
    }
}
