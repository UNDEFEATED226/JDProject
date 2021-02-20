package com.jd.iot.admin.repository;

import java.util.List;

import com.jd.iot.admin.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Repository for 用户
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT max(id) FROM User")
    public Long maxId();
    
    @Query(value = "SELECT u FROM User u WHERE isdeleted=0")
    public List<User> findAllUser();

    @Query(value = "SELECT u FROM User u WHERE isdeleted=0")
    public List<User> findAllUserPaginated(Pageable pageable);

    @Query(value = "SELECT COUNT(u) FROM User u WHERE isdeleted=0")
    public long count();
}