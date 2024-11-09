package com.manolo.jdbc.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record AuthorityDto(
        Integer id,
        String authority
) {

}
