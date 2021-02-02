package com.jd.iot.admin.controller;

import com.google.gson.Gson;
import com.jd.iot.admin.entity.Role;
import com.jd.iot.admin.service.RoleService;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
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

@CrossOrigin("http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleService roleservice;
	
	private static final Logger log = LoggerFactory.getLogger(RoleController.class);
	
	Gson gson = new Gson();

	/**
	 * 返回所有角色实体
	 * 
	 * @return
	 */
	@GetMapping("/findallrole")
	public List<Role> findAllRole() {
		log.info("查找所有角色:[{}]", gson.toJson(roleservice.findAllRole()));
		return roleservice.findAllRole();
	}

	/**
	 * 通过id查找指定用户
	 * 
	 * @param id
	 * 
	 * @return 指定用户实体
	 */
	@GetMapping("/findbyid")
	public Role findById(Long id) {
		try {
			log.info("查找角色id:[{}],角色:", id, gson.toJson(roleservice.findById(id)));
			return roleservice.findById(id);
		} catch (Exception e) {
			log.error("查找角色id:[{}]失败", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
		}
	}

	/**
	 * 添加单个角色实体
	 * 
	 * @param role 单个角色实体
	 * 
	 * @return
	 */
	@PostMapping("/addrole")
	public Role addRole(@RequestBody Role role) {
		log.info("添加角色:[{}]", gson.toJson(role));
		return roleservice.addRole(role);
	}
}