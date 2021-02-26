package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.Auth;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//Repository for 权限
@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    @Query(value = "SELECT a FROM Auth a WHERE isdeleted = 0")
    public List<Auth> findAllAuth();

    @Query(value = "SELECT a FROM Auth a WHERE isdeleted = 0 ORDER BY resid")
    public List<Auth> findAllAuthOrderbyResid();

    @Query(value = "SELECT a FROM Auth a WHERE isdeleted = 0")
    public List<Auth> findAllAuthPaginated(Pageable pageable);

    @Query(value = " SELECT COUNT(a) FROM Auth a WHERE isdeleted = 0")
    public long count();

    @Query(value = "SELECT a.authname FROM Auth a WHERE isdeleted = 0 and id = :id")
    public String getAuthname(@Param(value = "id") Long id);
}
