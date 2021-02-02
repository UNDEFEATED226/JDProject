package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Repository for 用户实体
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT max(id) FROM User")
    public Long maxId();
}