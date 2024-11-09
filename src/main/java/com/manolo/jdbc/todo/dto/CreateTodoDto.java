package com.manolo.jdbc.todo.dto;

public record CreateTodoDto(
		Integer ownerId,
	    String title,
	    String content) {

}
