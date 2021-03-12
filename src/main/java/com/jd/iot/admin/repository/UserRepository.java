package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.User;
import com.jd.iot.admin.vo.UserVO;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Repository for 用户
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT max(id) FROM User")
    public Long maxId();

    @Query(value = " SELECT new com.jd.iot.admin.vo.UserVO(u, o.orgname , t.name) FROM User u LEFT JOIN Organization o on u.orgid = o.id LEFT JOIN Tenant t on u.tenantid = t.id WHERE u.isdeleted = 0")
    public List<UserVO> findAllUser();

    @Query(value = " SELECT new com.jd.iot.admin.vo.UserVO(u, o.orgname , t.name) FROM User u LEFT JOIN Organization o on u.orgid = o.id LEFT JOIN Tenant t on u.tenantid = t.id WHERE u.isdeleted = 0")
    public List<UserVO> findAllUserPaginated(Pageable pageable);

    @Query(value = "SELECT COUNT(u) FROM User u LEFT JOIN Organization o ON u.orgid = o.id WHERE u.isdeleted = 0")
    public long count();
}