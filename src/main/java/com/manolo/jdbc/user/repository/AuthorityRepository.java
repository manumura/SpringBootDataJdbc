package com.manolo.jdbc.user.repository;

import com.manolo.jdbc.user.entity.Authority;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorityRepository extends ListCrudRepository<Authority, Integer> {

    @Query("SELECT a.* FROM authority a left join user_authority ua on a.id = ua.authority_id WHERE ua.user_id = :id")
    List<Authority> findAllAuthoritiesByUsers(@Param("id") Integer id);

    Optional<Authority> findByAuthority(String authority);
}
