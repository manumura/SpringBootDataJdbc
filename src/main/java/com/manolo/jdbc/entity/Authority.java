package com.manolo.jdbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;

@Table("authority")
@Builder
public record Authority(
		@Id Integer id, 
		String authority
) {

}
