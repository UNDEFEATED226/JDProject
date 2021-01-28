package com.example.demo.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Entity.Role;
import com.example.demo.Repository.RoleRepository;

//Service for 角色实体
@Service
public class RoleService {

	@Autowired
	RoleRepository rolerepository;

	/**
	 * 返回所有角色实体
	 * 
	 * @return
	 */
	public List<Role> findAllRole() {
		List<Role> e = new ArrayList<Role>();
		rolerepository.findAll().forEach(e::add);
		return e;
	}	

	/**
	 * 查找并返回最大id值
	 * 
	 * @return
	 */
	public Long maxId() {
		return rolerepository.maxId();
	}

	/**
	 * 添加单个角色实体
	 * 
	 * @param role 单个角色实体
	 * 
	 * @return
	 */
	public Role addRole(Role role) {
		Long max = maxId();
		if(max == null) {
			role.setId(1l);
		}else {
			role.setId(max+1);
		}
		role.setCreatetime(new Timestamp(System.currentTimeMillis()));
		role.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		return rolerepository.save(role);
	}

	/**
	 * 通过id查找指定角色
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public Role findById(Long id) {
		try {
			return rolerepository.findById(id).get();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
		}
	}
}
