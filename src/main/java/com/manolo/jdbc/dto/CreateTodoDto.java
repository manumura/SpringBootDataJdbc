package com.manolo.jdbc.dto;

public record CreateTodoDto(
		Integer ownerId,
	    String title,
	    String content) {

}
