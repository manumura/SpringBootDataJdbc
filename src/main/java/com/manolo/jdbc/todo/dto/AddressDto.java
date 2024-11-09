package com.manolo.jdbc.todo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record AddressDto(
		String addressLine
) {

}
