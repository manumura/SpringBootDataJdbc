package com.manolo.jdbc.todo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;

@Table("tutorial")
@Builder
public record Tutorial(
		@Id Integer id,
        String title) {

}
