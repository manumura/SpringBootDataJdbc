package com.manolo.jdbc.todo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CommentDto(
		String name,
        String content,
        LocalDateTime publishedOn,
        LocalDateTime updatedOn) {
}
