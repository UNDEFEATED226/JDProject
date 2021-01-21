package com.example.demo.Controller;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.Entity.Organization;
import com.example.demo.Service.OrganizationService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/organization")
@RestController
public class OrganizationController {

	@Autowired
	OrganizationService organizationservice;

	// return all the Organization instances
	@GetMapping("/findallorganization")
	public List<Organization> findAllOrganization() {
		return organizationservice.findAllOrganization();
	}

	// find a Organization instance by its id
	@PostMapping("/findbyid/{id}")
	public Organization findById(@PathVariable Long id) {
		try {
			return organizationservice.findById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGANIZATION NOT FOUND");
		}
	}

	// delete an existing Organization instance
	@DeleteMapping("/deleteorganization/{id}")
	public void deleteOrganization(@PathVariable Long id) {
		try {
			organizationservice.findById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGANIZATION NOT FOUND");
		}
		organizationservice.deleteOrganization(id);
	}

	// add new Organization instance
	@PostMapping("/addorganization")
	public void addOrganization(@RequestBody Organization organization) {
		organization.setTenantid(organizationservice.maxTenantid() + 1);
		organization.setCreatetime(new Timestamp(System.currentTimeMillis()));
		organization.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		organizationservice.addOrganization(organization);
	}
	
	// edit an existing Organization Entity
	@PutMapping("editorganization/{id}")
	public void editOrganization(@PathVariable Long id, @RequestBody Organization organization) {
		try {
			organizationservice.findById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGANIZATION NOT FOUND");
		}
		organization.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		organizationservice.editOrganization(id,organization);
	}
}
