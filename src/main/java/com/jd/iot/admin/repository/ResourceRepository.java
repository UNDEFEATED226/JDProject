package com.jd.iot.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jd.iot.admin.entity.Organization;
import com.jd.iot.admin.entity.Resource;

//Repository for 资源
@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long> {

    @Query(value = "SELECT max(id) FROM Resource WHERE restypeid IN (1,2)")
    public Long maxId1();

    @Query(value = "SELECT max(id) FROM Resource WHERE restypeid IN (3,4,5,6)")
    public Long maxId2();

    public List<Resource> findAllByRestypeid(Long restypeid);
    
    @Query(value = "SELECT o FROM Organization o WHERE isdeleted=0")
    public List<Organization> findAllOrganization();

    @Query(value = "SELECT o FROM Organization o WHERE isdeleted=0")
    public List<Organization> findAllOrganizationPaginated(Pageable pageable);

    @Query(value = "SELECT COUNT(o) FROM Organization o WHERE isdeleted=0")
    public long count();
}
