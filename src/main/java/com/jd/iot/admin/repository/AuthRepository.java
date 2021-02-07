package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.Auth;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Repository for 权限
@Repository
public interface AuthRepository extends CrudRepository<Auth, Long> {
    @Query(value = "SELECT max(id) FROM Auth")
    public Long maxId();
}
