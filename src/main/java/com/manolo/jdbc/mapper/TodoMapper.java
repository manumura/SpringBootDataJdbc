package com.manolo.jdbc.mapper;

import com.manolo.jdbc.dto.CreateTodoDto;
import com.manolo.jdbc.dto.TodoDto;
import com.manolo.jdbc.dto.UpdateTodoDto;
import com.manolo.jdbc.entity.Owner;
import com.manolo.jdbc.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;
import java.util.Set;

@Mapper
public interface TodoMapper {

    TodoMapper MAPPER = Mappers.getMapper(TodoMapper.class);

    TodoDto toTodoDto(Todo todo, Owner owner);

    default Todo toTodo(CreateTodoDto createTodoDto) {
        return new Todo(
                null,
                createTodoDto.title(),
                createTodoDto.content(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                new AggregateReference.IdOnlyAggregateReference<>(createTodoDto.ownerId()),
                Set.of()
        );
    }

    default Todo toTodo(Todo todoToUpdate, UpdateTodoDto updateTodoDto) {
        return new Todo(
                todoToUpdate.id(),
                updateTodoDto.title(),
                updateTodoDto.content(),
                todoToUpdate.publishedOn(),
                LocalDateTime.now(),
                todoToUpdate.ownerRef(),
                todoToUpdate.comments()
        );
    }
}
