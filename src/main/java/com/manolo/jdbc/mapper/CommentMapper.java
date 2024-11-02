package com.manolo.jdbc.mapper;

import com.manolo.jdbc.dto.CreateCommentDto;
import com.manolo.jdbc.entity.Comment;
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
