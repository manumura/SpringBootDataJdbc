package com.manolo.jdbc.entity;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_authority")
public record UserAuthority(

		@Column("authority_id")
		AggregateReference<Authority, Integer> authorityId
) {
}
