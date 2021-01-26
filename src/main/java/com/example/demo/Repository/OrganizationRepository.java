package com.example.demo.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.Organization;

//Repository for 组织实体
@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {
	@Query(value = "SELECT max(id) FROM Organization")
	public Long maxId();

	@Query(value = "SELECT max(tenantid) FROM Organization")
	public String maxTenantid();
}
