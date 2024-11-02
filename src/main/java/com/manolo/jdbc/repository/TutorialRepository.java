package com.manolo.jdbc.repository;

import com.manolo.jdbc.entity.Tutorial;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends ListCrudRepository<Tutorial, Integer> {

}
