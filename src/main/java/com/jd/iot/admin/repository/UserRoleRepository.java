package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.UserRole;
import com.jd.iot.admin.vo.UserRoleVO;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Repository for 用户角色
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query(value = "SELECT new com.jd.iot.admin.vo.UserRoleVO(ur,u.realname,r.rolename) FROM UserRole ur INNER JOIN User u ON ur.userid = u.id INNER JOIN Role r ON ur.roleid = r.id WHERE ur.isdeleted = 0 AND u.isdeleted = 0 AND r.isdeleted = 0")
    public List<UserRoleVO> findAllUserRolePaginated(Pageable pageable);

    @Query(value = "SELECT COUNT(ur) FROM UserRole ur INNER JOIN User u ON ur.userid = u.id INNER JOIN Role r ON ur.roleid = r.id WHERE ur.isdeleted = 0 AND u.isdeleted = 0 AND r.isdeleted = 0")
    public long count();

    @Query(value = "SELECT new com.jd.iot.admin.vo.UserRoleVO(ur,u.realname,r.rolename) FROM UserRole ur INNER JOIN User u ON ur.userid = u.id INNER JOIN Role r ON ur.roleid = r.id WHERE ur.isdeleted = 0 AND u.isdeleted = 0 AND r.isdeleted = 0")
    public List<UserRoleVO> findAllUserRole();

}
