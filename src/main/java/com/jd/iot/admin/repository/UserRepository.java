package com.jd.iot.admin.repository;

import java.util.List;

import com.jd.iot.admin.entity.User;
import com.jd.iot.admin.vo.UserVO;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//Repository for 用户
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT max(id) FROM User")
    public Long maxId();

    @Query(value = "SELECT new com.jd.iot.admin.vo.UserVO(u,o.orgname) FROM User u LEFT JOIN Organization o ON u.orgid = o.id WHERE u.isdeleted = 0")
    public List<UserVO> findAllUser();

    @Query(value = "SELECT new com.jd.iot.admin.vo.UserVO(u,o.orgname) FROM User u LEFT JOIN Organization o ON u.orgid = o.id WHERE u.isdeleted = 0")
    public List<UserVO> findAllUserPaginated(Pageable pageable);

    @Query(value = "SELECT COUNT(u) FROM User u LEFT JOIN Organization o ON u.orgid = o.id WHERE u.isdeleted = 0")
    public long count();

    @Query(value = "SELECT u.realname FROM User u WHERE isdeleted = 0 AND id = :id")
    public String getUsername(@Param(value = "id") Long id);

    @Query(value = "SELECT u.loginname FROM User u WHERE isdeleted = 0 AND id = :id")
    public String getLoginname(@Param(value = "id") Long id);
}