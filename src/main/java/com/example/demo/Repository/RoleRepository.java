package com.example.demo.repository;

import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Repository for 角色实体
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	@Query(value = "SELECT max(id) FROM Role")
	public Long maxId();
}
