package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.Organization;
import com.jd.iot.admin.repository.OrganizationRepository;
import com.jd.iot.admin.vo.OrganizationVO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * 查询组织列表
     * 
     * @return 组织列表
     */
    public List<OrganizationVO> findAllOrganization() {
        List<Organization> l = new ArrayList<Organization>();
        organizationrepository.findAll().forEach(l::add);
        List<OrganizationVO> lv = new ArrayList<OrganizationVO>();
        l.stream().filter(o -> o.getIsdeleted() != 1 ).map(o -> lv.add(new OrganizationVO(o)))
                .collect(Collectors.toList());
        return lv;
    }

    /**
     * 删除组织
     * 
     * @param id 需删除组织的id
     */
    public void deleteOrganization(Long id) {
        try {
            Organization organization = organizationrepository.findById(id).get();
            organization.setIsdeleted(1);
            organization.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            organizationrepository.save(organization);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " ORGANIZATION NOT FOUND");
        }
    }

    /**
     * 添加组织
     * 
     * @param 需添加的组织
     * 
     * @return 成功添加的组织
     */
    public OrganizationVO addOrganization(OrganizationVO organizationvo) {
        Organization organization = new Organization(organizationvo);
        Long id = organizationrepository.maxId();
        if (id == null) {
            organization.setId(1L);
        } else {
            organization.setId(id + 1);
        }
        String t = organizationrepository.maxTenantid();
        if (t == null) {
            organization.setTenantid("1");
        } else {
            Long max = Long.parseLong(organizationrepository.maxTenantid());
            organization.setTenantid(String.valueOf(max + 1));
        }
        organization.setCreatetime(new Timestamp(System.currentTimeMillis()));
        organization.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new OrganizationVO(organizationrepository.save(organization));
    }

    /**
     * 通过id查找指定组织
     * 
     * @param id 需查找组织的id
     * 
     * @return 指定组织实体
     */
    public OrganizationVO findById(Long id) {
        try {
            return new OrganizationVO(organizationrepository.findById(id).get());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGANIZATION NOT FOUND");
        }
    }

    /**
     * 修改组织
     * 
     * @param id           需修改组织的id
     * @param organization 修改过的组织
     * 
     * @return 成功修改的组织
     */
    public OrganizationVO editOrganization(Long id, OrganizationVO organizationvo) {
        try {
            organizationrepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " ORGANIZATION NOT FOUND");
        }
        Organization organization = new Organization(organizationvo);
        organization.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new OrganizationVO(organizationrepository.save(organization));
    }
}