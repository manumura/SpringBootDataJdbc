package com.manolo.jdbc.todo.service;

import com.manolo.jdbc.todo.dto.*;
import com.manolo.jdbc.todo.entity.Todo;
import com.manolo.jdbc.todo.mapper.CommentMapper;
import com.manolo.jdbc.todo.mapper.TodoMapper;
import com.manolo.jdbc.todo.repository.CommentRepository;
import com.manolo.jdbc.todo.repository.OwnerRepository;
import com.manolo.jdbc.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final OwnerRepository ownerRepository;
    private final CommentRepository commentRepository;
    private final TodoMapper todoMapper = TodoMapper.MAPPER;
    private final CommentMapper commentMapper = CommentMapper.MAPPER;

    public List<TodoDto> getTodos() {
        var todos = todoRepository.findAll();
        return todos.stream().map(
                this::toTodoDto
        ).toList();
    }

    public TodoDto getTodoById(Integer id) {
        var todo = todoRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Todo not found")
        );
        return toTodoDto(todo);
    }

    public TodoDto createTodo(CreateTodoDto createTodoDto) {
        var owner = Optional.ofNullable(createTodoDto.ownerId())
                .flatMap(ownerRepository::findById)
                .orElseThrow(
                        () -> new NoSuchElementException("Owner not found")
                );
        var todoCreated = todoRepository.save(todoMapper.toTodo(createTodoDto));
        return todoMapper.toTodoDto(todoCreated, owner);
    }

    public TodoDto updateTodo(Integer id, UpdateTodoDto updateTodoDto) {
        var todo = todoRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Todo not found")
        );
        var todoUpdated = todoRepository.save(todoMapper.toTodo(todo, updateTodoDto));
        return toTodoDto(todoUpdated);
    }

    public TodoDto addComment(Integer id, CreateCommentDto createCommentDto) {
        todoRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Todo not found")
        );
        var comment = commentMapper.toComment(createCommentDto, id);
        commentRepository.save(comment);
        var todoUpdated = todoRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Todo not found")
        );
        return toTodoDto(todoUpdated);
    }

    public TodoDto removeComment(Integer id, RemoveCommentDto removeCommentDto) {
        todoRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Todo not found")
        );
        var comment = commentRepository.findById(removeCommentDto.commentId()).orElseThrow(
                () -> new NoSuchElementException("Comment not found")
        );
        commentRepository.delete(comment);
        var todoUpdated = todoRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Todo not found")
        );
        return toTodoDto(todoUpdated);
    }

    public void deleteTodo(Integer id) {
        todoRepository.deleteById(id);
    }

    private TodoDto toTodoDto(Todo todo) {
        var owner = Optional.ofNullable(todo.ownerRef())
                .map(AggregateReference::getId)
                .flatMap(ownerRepository::findById)
                .orElse(null);
        return todoMapper.toTodoDto(todo, owner);
    }
}
