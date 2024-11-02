package com.manolo.jdbc.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("address")
@Builder
public record Address(
		@Id Integer id,
		String addressLine
) {

}
