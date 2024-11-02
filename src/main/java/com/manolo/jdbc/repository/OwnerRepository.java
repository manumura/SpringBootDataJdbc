package com.manolo.jdbc.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.manolo.jdbc.entity.Owner;

@Repository
public interface OwnerRepository extends ListCrudRepository<Owner, Integer> {

}
