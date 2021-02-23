package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.Resource;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//Repository for 资源
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Query(value = "SELECT max(id) FROM Resource WHERE restypeid IN (1,2)")
    public Long maxId1();

    @Query(value = "SELECT max(id) FROM Resource WHERE restypeid IN (3,4,5,6)")
    public Long maxId2();

    @Query(value = "SELECT r FROM Resource r WHERE isdeleted = 0")
    public List<Resource> findAllResource();

    @Query(value = "SELECT r FROM Resource r WHERE isdeleted = 0")
    public List<Resource> findAllResourcePaginated(Pageable pageable);

    @Query(value = "SELECT r FROM Resource r WHERE isdeleted = 0 AND restypeid = :restypeid")
    public List<Resource> findAllByRestypeid(@Param(value = "restypeid") Long restypeid);

    @Query(value = "SELECT r FROM Resource r WHERE isdeleted = 0 AND restypeid = :restypeid")
    public List<Resource> findAllByRestypeidPaginated(@Param(value = "restypeid") Long restypeid, Pageable pageable);

    @Query(value = "SELECT COUNT(r) FROM Resource r WHERE isdeleted = 0")
    public long count();

    @Query(value = "SELECT COUNT(r) FROM Resource r WHERE isdeleted = 0 AND restypeid = :restypeid")
    public long countByRestypeid(@Param(value = "restypeid") Long restypeid);
}
