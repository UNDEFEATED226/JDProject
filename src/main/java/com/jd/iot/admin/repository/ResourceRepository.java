package com.jd.iot.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jd.iot.admin.entity.Resource;

//Repository for 资源实体
@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long> {
    @Query(value = "SELECT max(id) FROM Resource")
    public Long maxId();

    public List<Resource> findAllByRestypeid(Long restypeid);
}
