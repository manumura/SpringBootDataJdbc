package com.manolo.jdbc.repository;

import com.manolo.jdbc.entity.Comment;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends ListCrudRepository<Comment, Integer> {

}
