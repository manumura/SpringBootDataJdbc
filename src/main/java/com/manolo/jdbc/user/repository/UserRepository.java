package com.manolo.jdbc.user.repository;

import com.manolo.jdbc.user.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends ListCrudRepository<User, Integer>, ListPagingAndSortingRepository<User, Integer> {

    @Query("SELECT * FROM \"user\" u join user_authority ua on u.id = ua.user_id WHERE ua.authority_id = :id")
    List<User> findAllUsersByAuthorities(@Param("id") Integer id);
}
