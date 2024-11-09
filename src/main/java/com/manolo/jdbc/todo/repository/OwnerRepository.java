package com.manolo.jdbc.todo.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.manolo.jdbc.todo.entity.Owner;

@Repository
public interface OwnerRepository extends ListCrudRepository<Owner, Integer> {

}
