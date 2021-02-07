package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Repository for 用户角色实体
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
	@Query(value = "SELECT max(id) FROM UserRole")
	public Long maxId();
}
