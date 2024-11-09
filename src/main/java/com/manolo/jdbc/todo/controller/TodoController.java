package com.manolo.jdbc.todo.controller;

import com.manolo.jdbc.todo.dto.*;
import com.manolo.jdbc.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;
	
	@GetMapping
	public List<TodoDto> getTodos() {
		return todoService.getTodos();
	}
	
	@GetMapping("/{id}")
	public TodoDto getTodoById(@PathVariable("id") Integer id) {
		return todoService.getTodoById(id);
	}
	
	@PostMapping
	public TodoDto createTodo(@RequestBody CreateTodoDto todo) {
		return todoService.createTodo(todo);
	}
	
	@PutMapping("/{id}")
	public TodoDto updateTodo(@PathVariable("id") Integer id, @RequestBody UpdateTodoDto todo) {
		return todoService.updateTodo(id, todo);
	}

	@PutMapping("/{id}/add")
	public TodoDto addComment(@PathVariable("id") Integer id, @RequestBody CreateCommentDto createCommentDto) {
		return todoService.addComment(id, createCommentDto);
	}

	@PutMapping("/{id}/remove")
	public TodoDto removeComment(@PathVariable("id") Integer id, @RequestBody RemoveCommentDto removeCommentDto) {
		return todoService.removeComment(id, removeCommentDto);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteTodo(@PathVariable("id") Integer id) {
		todoService.deleteTodo(id);
	}
}
