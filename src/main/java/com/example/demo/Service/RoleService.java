package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//Service for 角色实体
@Service
public class RoleService {

    @Autowired
    RoleRepository rolerepository;

    /**
     * . 查找所有角色实体
     * 
     * @return 所有角色实体
     */
    public List<Role> findAllRole() {
        List<Role> e = new ArrayList<Role>();
        rolerepository.findAll().forEach(e::add);
        return e;
    }

    /**
     * . 查找最大id值
     * 
     * @return 最大id值
     */
    public Long maxId() {
        return rolerepository.maxId();
    }

    /**
     * . 添加单个角色实体
     * 
     * @param role 单个角色实体
     * 
     * @return 成功添加的角色实体
     */
    public Role addRole(Role role) {
        Long max = maxId();
        if (max == null) {
            role.setId(1L);
        } else {
            role.setId(max + 1);
        }
        role.setCreatetime(new Timestamp(System.currentTimeMillis()));
        role.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return rolerepository.save(role);
    }

    /**
     * . 通过id查找指定角色
     * 
     * @param id 需查找角色的id
     * 
     * @return 指定角色实体
     */
    public Role findById(Long id) {
        try {
            return rolerepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
        }
    }
}