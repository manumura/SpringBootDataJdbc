package com.manolo.jdbc.todo.repository;

import java.util.List;

import com.manolo.jdbc.todo.entity.Owner;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.manolo.jdbc.todo.entity.Todo;

@Repository
public interface TodoRepository extends  ListCrudRepository<Todo, Integer>{
	
	List<Todo> findAllByOwnerRef(AggregateReference<Owner, Integer> ownerRef);

}
