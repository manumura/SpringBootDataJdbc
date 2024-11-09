package com.manolo.jdbc.todo.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;

@Table("todo")
@Builder
public record Todo(
		@Id Integer id,
	    String title,
	    String content,
	    LocalDateTime publishedOn,
	    LocalDateTime updatedOn,

		@Column(value = "owner_id")
	    AggregateReference<Owner,Integer> ownerRef,

		@MappedCollection(idColumn = "todo_id", keyColumn = "id")
	    Set<Comment> comments
) {
}
