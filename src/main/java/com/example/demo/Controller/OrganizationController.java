package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	@GetMapping("/findbyid")
	public Organization findById(Long id) {
		try {
			return organizationservice.findById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGANIZATION NOT FOUND");
		}
	}

	// add new Organization instance
	@PostMapping("/addorganization")
	public Organization addOrganization(@RequestBody Organization organization) {
		return organizationservice.addOrganization(organization);
	}
}
