package com.manolo.jdbc.todo.repository;

import com.manolo.jdbc.todo.entity.Address;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends ListCrudRepository<Address, Integer> {

}
