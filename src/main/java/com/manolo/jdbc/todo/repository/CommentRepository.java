package com.manolo.jdbc.todo.repository;

import com.manolo.jdbc.todo.entity.Comment;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends ListCrudRepository<Comment, Integer> {

}
