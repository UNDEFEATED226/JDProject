package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.Tenant;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Repository for 租户
@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    @Query("SELECT max(id) FROM Tenant")
    public Long maxId();

    @Query("SELECT t FROM Tenant t WHERE isdeleted = 0")
    public List<Tenant> findAllTenant();

    @Query("SELECT t FROM Tenant t WHERE isdeleted = 0")
    public List<Tenant> findAllTenantPaginated(Pageable pageable);

    @Query("SELECT COUNT(t) FROM Tenant t WHERE isdeleted = 0")
    public long count();
}
