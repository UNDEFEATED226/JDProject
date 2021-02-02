package com.jd.iot.admin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jd.iot.admin.entity.User;

//Repository for 用户实体
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT max(id) FROM User")
    public Long maxId();
}