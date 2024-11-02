package com.manolo.jdbc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record AddressDto(
		String addressLine
) {

}
