package com.jd.iot.admin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jd.iot.admin.entity.Role;

//Repository for 角色实体
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	@Query(value = "SELECT max(id) FROM Role")
	public Long maxId();
}
