package com.manolo.jdbc.todo.mapper;

import com.manolo.jdbc.todo.dto.CreateCommentDto;
import com.manolo.jdbc.todo.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;

@Mapper
public interface CommentMapper {

    CommentMapper MAPPER = Mappers.getMapper(CommentMapper.class);

    default Comment toComment(CreateCommentDto createCommentDto, Integer todoId) {
        return new Comment(null, createCommentDto.name(), createCommentDto.content(),
                LocalDateTime.now(), LocalDateTime.now(), new AggregateReference.IdOnlyAggregateReference<>(todoId));
    }
}
