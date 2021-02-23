package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.Auth;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Repository for 权限
@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    @Query(value = "SELECT max(id) FROM Auth")
    public Long maxId();

    @Query(value = "SELECT a FROM Auth a WHERE isdeleted = 0")
    public List<Auth> findAllAuth();

    @Query(value = "SELECT a FROM Auth a WHERE isdeleted = 0")
    public List<Auth> findAllAuthPaginated(Pageable pageable);

    @Query(value = " SELECT COUNT(a) FROM Auth a WHERE isdeleted = 0")
    public long count();
}
