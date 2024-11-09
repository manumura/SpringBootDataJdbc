package com.manolo.jdbc.todo.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("comment")
@Builder
public record Comment(
        @Id Integer id,
        String name,
        String content,
        LocalDateTime publishedOn,
        LocalDateTime updatedOn,

        @Column(value = "todo_id")
        AggregateReference<Todo, Integer> todoRef) {
}
