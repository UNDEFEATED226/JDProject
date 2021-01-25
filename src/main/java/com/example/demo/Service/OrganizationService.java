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

//Service for Organization entity
@Service
public class OrganizationService {

	@Autowired
	OrganizationRepository organizationrepository;

	// return all the Organization instances
	public List<Organization> findAllOrganization() {
		List<Organization> l = new ArrayList<Organization>();
		organizationrepository.findAll().forEach(l::add);
		return l;
	}

	// get max id
	public Long maxId() {
		return organizationrepository.maxId();
	}

	// get max tenant id
	public String maxTenantid() {
		return organizationrepository.maxTenantid();
	}

	// delete an existing Organization instance
	public void deleteOrganization(Long id) {
		try {
			organizationrepository.findById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, " ORGANIZATION NOT FOUND");
		}
		organizationrepository.deleteById(id);
	}

	// add new organization instance
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

	public Organization findById(Long id) {
		return organizationrepository.findById(id).get();
	}

	// edit an existing organization instance
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
