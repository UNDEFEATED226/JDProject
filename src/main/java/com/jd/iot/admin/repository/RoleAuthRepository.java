package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.RoleAuth;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//Repository for 角色权限
@Repository
public interface RoleAuthRepository extends JpaRepository<RoleAuth, Long> {
    @Query(value = "SELECT r FROM RoleAuth r WHERE isdeleted = 0")
    public List<RoleAuth> findAllRoleAuth();

    @Query(value = "SELECT r FROM RoleAuth r WHERE isdeleted = 0")
    public List<RoleAuth> findAllRoleAuthPaginated(Pageable pageable);

    @Query(value = "SELECT COUNT(r) FROM RoleAuth r WHERE isdeleted = 0")
    public long count();

    @Query(value = "SELECT r FROM RoleAuth r WHERE isdeleted = 0 AND roleid = :roleid")
    public List<RoleAuth> findByRoleid(@Param(value = "roleid") Long roleid);

    @Query(value = "SELECT r FROM RoleAuth r WHERE isdeleted = 0 AND roleid = :roleid AND authid = :authid")
    public List<RoleAuth> findByRoleidAndAuthid(@Param(value = "roleid") Long roleid,
            @Param(value = "authid") Long authid);
}
