package com.example.demo.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.Entity.Organization;
import com.example.demo.Repository.OrganizationRepository;

//Service for 组织实体
@Service
public class OrganizationService {

	@Autowired
	OrganizationRepository organizationrepository;

	/**
	 * 查找并返回所有组织实体
	 * 
	 * @return
	 */
	public List<Organization> findAllOrganization() {
		List<Organization> l = new ArrayList<Organization>();
		organizationrepository.findAll().forEach(l::add);
		return l;
	}

	/**
	 * 返回最大id值
	 * 
	 * @return
	 */
	public Long maxId() {
		return organizationrepository.maxId();
	}

	/**
	 * 返回最大租户id值
	 * 
	 * @return
	 */
	public String maxTenantid() {
		return organizationrepository.maxTenantid();
	}

	/**
	 * 通过id删除指定组织
	 * 
	 * @param id
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
	 * @return
	 */
	public Organization addOrganization(Organization organization) {
		Long id = maxId();
		if (id == null) {
			organization.setId(1l);
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
	 * 通过id查找指定组织
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public Organization findById(Long id) {
		try {
			return organizationrepository.findById(id).get();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGANIZATION NOT FOUND");
		}
	}

	/**
	 * 通过id编辑某个指定组织
	 * 
	 * @param id
	 * @param organization 修改完的组织实体
	 */
	public void editOrganization(Long id, Organization organization) {
		try {
			organizationrepository.findById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, " ORGANIZATION NOT FOUND");
		}
		organization.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		organizationrepository.save(organization);
	}
}
