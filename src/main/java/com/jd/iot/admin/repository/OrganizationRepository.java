package com.jd.iot.admin.repository;

import com.jd.iot.admin.entity.Organization;
import com.jd.iot.admin.vo.OrganizationVO;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//Repository for 组织
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Query(value = " SELECT new com.jd.iot.admin.vo.OrganizationVO(o,t.name) FROM Organization o LEFT JOIN Tenant t ON o.tenantid = t.id WHERE o.isdeleted = 0")
    public List<OrganizationVO> findAllOrganization();

    @Query(value = " SELECT new com.jd.iot.admin.vo.OrganizationVO(o,t.name) FROM Organization o LEFT JOIN Tenant t ON o.tenantid = t.id WHERE o.isdeleted = 0")
    public List<OrganizationVO> findAllOrganizationPaginated(Pageable pageable);

    @Query(value = "SELECT new com.jd.iot.admin.vo.OrganizationVO(o,t.name) FROM Organization o LEFT JOIN Tenant t ON o.tenantid = t.id WHERE o.isdeleted = 0 AND o.id = :id")
    public OrganizationVO findOrganzationById(@Param(value = "id") Long id);

    @Query(value = " SELECT COUNT(o) FROM Organization o LEFT JOIN Tenant t ON o.tenantid = t.id WHERE o.isdeleted = 0")
    public long count();

    @Query(value = "SELECT o.orgname FROM Organization o WHERE isdeleted = 0 AND id = :id")
    public String getOrgname(@Param(value = "id") Long id);
}
