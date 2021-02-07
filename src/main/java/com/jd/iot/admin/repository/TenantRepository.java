package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.Tenant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Repository for 租户
@Repository
public interface TenantRepository extends CrudRepository<Tenant,Long> {
    @Query("SELECT max(id) FROM Tenant")
    public Long maxId();
}
