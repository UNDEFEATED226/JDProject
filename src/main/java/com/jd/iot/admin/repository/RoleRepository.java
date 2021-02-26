package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.Role;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//Repository for 角色
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "SELECT r FROM Role r WHERE isdeleted = 0")
    public List<Role> findAllRole();

    @Query(value = "SELECT r FROM Role r WHERE isdeleted = 0")
    public List<Role> findAllRolePaginated(Pageable pageable);

    @Query(value = "SELECT r FROM Role r WHERE isdeleted=0 AND roletype = :roletype")
    public List<Role> findAllByRoletype(@Param(value = "roletype") Long roletype);

    @Query(value = "SELECT r FROM Role r WHERE isdeleted=0 AND roletype = :roletype")
    public List<Role> findAllByRoletypePaginated(@Param(value = "roletype") Long roletype, Pageable pageable);

    @Query(value = "SELECT COUNT(r) FROM Role r WHERE isdeleted=0")
    public long count();

    @Query(value = "SELECT COUNT(r) FROM Role r WHERE isdeleted=0 AND roletype = :roletype")
    public long countByRoletype(@Param(value = "roletype") Long roletype);

    @Query(value = "SELECT r.rolename FROM Role r WHERE isdeleted=0 AND id = :id")
    public String getRolename(@Param(value = "id") Long id);
}
