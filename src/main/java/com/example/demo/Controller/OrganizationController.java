package com.example.demo.Controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

//Controller for organization
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/organization")
public class OrganizationController {

	@Autowired
	OrganizationService organizationservice;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	Gson gson = new Gson();
	
	/**
	 * 查找所有组织实体
	 * 
	 * @return
	 */
	@GetMapping("/findallorganization")
	public List<Organization> findAllOrganization() {
		log.info("查找所有组织:[{}]",organizationservice.findAllOrganization());
		return organizationservice.findAllOrganization();
	}

	/**
	 * 用id查找指定组织实体
	 * 
	 * @param id
	 * 
	 * @return 组织实体
	 */
	@GetMapping("/findbyid")
	public Organization findById(Long id) {
		try {
			log.info("查找组织id:[{}],组织:",id,organizationservice.findById(id));
			return organizationservice.findById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGANIZATION NOT FOUND");
		}
	}

	/**
	 * 添加单个组织实体
	 * 
	 * @param organization 单个组织实体
	 * 
	 * @return 添加的组织实体
	 */
	@PostMapping("/addorganization")
	public Organization addOrganization(@RequestBody Organization organization) {
		log.info("添加组织:[{}]",gson.toJson(organization));
		return organizationservice.addOrganization(organization);
	}
}
