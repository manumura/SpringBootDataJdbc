package com.manolo.jdbc.todo.repository;

import com.manolo.jdbc.todo.entity.Tutorial;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends ListCrudRepository<Tutorial, Integer> {

}
