package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.Role;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Repository for 角色
@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    @Query(value = "SELECT max(id) FROM Role")
    public Long maxId();

    public List<Role> findAllByRoletype(Long roletype);
}
