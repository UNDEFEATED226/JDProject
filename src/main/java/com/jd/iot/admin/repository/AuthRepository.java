package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.Auth;
import com.jd.iot.admin.vo.AuthVO;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//Repository for 权限
@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    @Query(value = "SELECT new com.jd.iot.admin.vo.AuthVO(a,r.resname) FROM Auth a INNER JOIN Resource r ON a.resid = r.id WHERE a.isdeleted = 0 AND r.isdeleted = 0 ORDER BY a.id")
    public List<AuthVO> findAllAuth();

    @Query(value = "SELECT new com.jd.iot.admin.vo.AuthVO(a,r.resname) FROM Auth a INNER JOIN Resource r ON a.resid = r.id WHERE a.isdeleted = 0 AND r.isdeleted = 0 ORDER BY a.id")
    public List<AuthVO> findAllAuthPaginated(Pageable pageable);

    @Query(value = "SELECT COUNT(a) FROM Auth a INNER JOIN Resource r ON a.resid = r.id WHERE a.isdeleted = 0 AND r.isdeleted = 0")
    public long count();

    @Query(value = "SELECT new com.jd.iot.admin.vo.AuthVO(a,r.resname) FROM Auth a INNER JOIN Resource r ON a.resid = r.id WHERE a.isdeleted = 0 AND r.isdeleted = 0 AND a.id = :id")
    public AuthVO findAuthById(@Param(value = "id") Long id);
}
