package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.RoleAuth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Repository for 角色权限
@Repository
public interface RoleAuthRepository extends CrudRepository<RoleAuth, Long> {
    @Query(value = "SELECT max(id) FROM RoleAuth")
    public Long maxId();
}
