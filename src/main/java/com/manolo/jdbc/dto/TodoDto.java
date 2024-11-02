package com.manolo.jdbc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TodoDto(
	    String title,
	    String content,
	    LocalDateTime publishedOn,
	    LocalDateTime updatedOn,
		OwnerDto owner,
	    Set<CommentDto> comments
) {
}
