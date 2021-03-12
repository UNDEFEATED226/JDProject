package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.Role;
import com.jd.iot.admin.vo.AuthVO;
import com.jd.iot.admin.vo.RoleVO;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//Repository for 角色
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "SELECT new com.jd.iot.admin.vo.RoleVO(r,t.name) FROM Role r LEFT JOIN Tenant t ON r.tenantid = t.id WHERE r.isdeleted = 0")
    public List<RoleVO> findAllRole();

    @Query(value = "SELECT new com.jd.iot.admin.vo.RoleVO(r,t.name) FROM Role r LEFT JOIN Tenant t ON r.tenantid = t.id WHERE r.isdeleted = 0")
    public List<RoleVO> findAllRolePaginated(Pageable pageable);

    @Query(value = "SELECT new com.jd.iot.admin.vo.RoleVO(r,t.name) FROM Role r LEFT JOIN Tenant t ON r.tenantid = t.id WHERE r.isdeleted = 0 AND r.roletype = :roletype")
    public List<RoleVO> findAllByRoletype(@Param(value = "roletype") Long roletype);

    @Query(value = "SELECT new com.jd.iot.admin.vo.RoleVO(r,t.name) FROM Role r LEFT JOIN Tenant t ON r.tenantid = t.id WHERE r.isdeleted = 0 AND r.roletype = :roletype")
    public List<RoleVO> findAllByRoletypePaginated(@Param(value = "roletype") Long roletype, Pageable pageable);

    @Query(value = "SELECT COUNT(r) FROM Role r LEFT JOIN Tenant t ON r.tenantid = t.id WHERE r.isdeleted = 0")
    public long count();

    @Query(value = "SELECT COUNT(r) FROM Role r LEFT JOIN Tenant t ON r.tenantid = t.id WHERE r.isdeleted = 0 AND r.roletype = :roletype")
    public long countByRoletype(@Param(value = "roletype") Long roletype);

    @Query(value = "SELECT new com.jd.iot.admin.vo.RoleVO(r,t.name) FROM Role r LEFT JOIN Tenant t ON r.tenantid = t.id WHERE r.isdeleted = 0 AND r.id = :id ")
    public RoleVO findRoleById(@Param(value = "id") Long id);

    @Query(value = "SELECT new com.jd.iot.admin.vo.AuthVO(a,re.resname) FROM RoleAuth ra INNER JOIN Role r on ra.roleid = r.id INNER JOIN Auth a on ra.authid = a.id INNER JOIN Resource re ON a.resid = re.id WHERE ra.isdeleted  = 0 AND r.isdeleted  = 0 AND a.isdeleted = 0  AND ra.roleid = :id AND re.isdeleted =0 ORDER BY a.resid")
    public List<AuthVO> authListForRole(@Param(value = "id") Long id);
}
