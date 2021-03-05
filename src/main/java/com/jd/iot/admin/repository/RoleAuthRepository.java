package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.RoleAuth;
import com.jd.iot.admin.vo.AuthWithResname;

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

    @Query(value = "SELECT a.id FROM RoleAuth r INNER JOIN Auth a ON r.authid = a.id WHERE a.isdeleted = 0 AND r.isdeleted = 0 AND r.roleid = :roleid")
    public List<Long> findAuthidByRoleid(@Param(value = "roleid") Long roleid);

    @Query(value = " SELECT new com.jd.iot.admin.vo.AuthWithResname(a,r.resname) FROM Auth a INNER JOIN Resource r on a.resid = r.id WHERE a.isdeleted = 0 AND r.isdeleted = 0 ORDER BY a.resid")
    public List<AuthWithResname> findAuthOrderbyResid();
}
