package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.Organization;
import com.jd.iot.admin.repository.OrganizationRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//Service for 组织实体
@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationrepository;

    /**
     * . 查找所有组织实体
     * 
     * @return 所有组织实体
     */
    public List<Organization> findAllOrganization() {
        List<Organization> l = new ArrayList<Organization>();
        organizationrepository.findAll().forEach(l::add);
        return l;
    }

    /**
     * . 查找最大id值
     * 
     * @return 最大id值
     */
    public Long maxId() {
        return organizationrepository.maxId();
    }

    /**
     * . 查找最大租户id值
     * 
     * @return 最大租户id值
     */
    public String maxTenantid() {
        return organizationrepository.maxTenantid();
    }

    /**
     * . 通过id删除指定组织
     * 
     * @param id 需删除组织的id
     */
    public void deleteOrganization(Long id) {
        try {
            organizationrepository.findById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " ORGANIZATION NOT FOUND");
        }
        organizationrepository.deleteById(id);
    }

    /**
     * 添加单个组织实体
     * 
     * @param 单个组织实体
     * 
     * @return 成功添加的组织实体
     */
    public Organization addOrganization(Organization organization) {
        Long id = maxId();
        if (id == null) {
            organization.setId(1L);
        } else {
            organization.setId(id + 1);
        }
        String t = maxTenantid();
        if (t == null) {
            organization.setTenantid("1");
        } else {
            Long max = Long.parseLong(maxTenantid());
            organization.setTenantid(String.valueOf(max + 1));
        }
        organization.setCreatetime(new Timestamp(System.currentTimeMillis()));
        organization.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return organizationrepository.save(organization);
    }

    /**
     * . 通过id查找指定组织
     * 
     * @param id 需查找组织的id
     * 
     * @return 指定组织实体
     */
    public Organization findById(Long id) {
        try {
            return organizationrepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGANIZATION NOT FOUND");
        }
    }

    /**
     * . 修改指定组织实体
     * 
     * @param id           所需修改组织的id
     * @param organization 修改后的组织实体
     * 
     * @return 成功修改后的组织实体
     */
    public Organization editOrganization(Long id, Organization organization) {
        try {
            organizationrepository.findById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " ORGANIZATION NOT FOUND");
        }
        organization.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return organizationrepository.save(organization);
    }
}