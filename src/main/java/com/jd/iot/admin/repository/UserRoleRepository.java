package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.UserRole;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Repository for 用户角色
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query(value = "SELECT u FROM UserRole u WHERE isdeleted = 0")
    public List<UserRole> findAllUserRole();

    @Query(value = "SELECT u FROM UserRole u WHERE isdeleted = 0")
    public List<UserRole> findAllUserRolePaginated(Pageable pageable);

    @Query(value = "SELECT COUNT(u) FROM UserRole u WHERE isdeleted = 0")
    public long count();
}
