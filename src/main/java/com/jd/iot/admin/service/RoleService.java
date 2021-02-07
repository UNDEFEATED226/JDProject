package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.Role;
import com.jd.iot.admin.repository.RoleRepository;
import com.jd.iot.admin.vo.RoleVO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//Service for 角色
@Service
public class RoleService {

    @Autowired
    RoleRepository rolerepository;

    /**
     * 查询角色列表
     * 
     * @return 角色列表
     */
    public List<RoleVO> findAllRole() {
        List<Role> l = new ArrayList<Role>();
        List<RoleVO> lv = new ArrayList<RoleVO>();
        rolerepository.findAll().forEach(l::add);
        l.stream().filter(r -> r.getIsdeleted()!=1).map(r -> lv.add(new RoleVO(r))).collect(Collectors.toList());
        return lv;
    }

    /**
     * 查询所有指定角色种类的角色
     * 
     * @param roletype 角色种类
     * 
     * @return 所有指定角色种类的角色
     */
    public List<RoleVO> roleMenu(Long roletype) {
        List<Role> l = rolerepository.findAllByRoletype(roletype);
        List<RoleVO> lv = new ArrayList<RoleVO>();
        l.stream().filter(r -> r.getIsdeleted() != 1).map(r -> lv.add(new RoleVO(r))).collect(Collectors.toList());
        return lv;
    }

    /**
     * 添加角色
     * 
     * @param rolevo 需添加的角色
     * 
     * @return 成功添加的角色
     */
    public RoleVO addRole(RoleVO rolevo) {
        Role r = new Role(rolevo);
        Long max = rolerepository.maxId();
        if (max == null) {
            r.setId(1L);
        } else {
            r.setId(max + 1);
        }
        r.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        r.setCreatetime(new Timestamp(System.currentTimeMillis()));
        return new RoleVO(rolerepository.save(r));
    }

    /**
     * 修改角色
     * 
     * @param id     需修改角色的id
     * @param rolevo 修改过的角色
     * 
     * @return 成功修改的角色
     */
    public RoleVO editRole(Long id, RoleVO rolevo) {
        try {
            rolerepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
        }
        Role r = new Role(rolevo);
        r.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new RoleVO(rolerepository.save(r));
    }

    /**
     * 删除角色
     * 
     * @param id 需删除角色的id
     */
    public void deleteRole(Long id) {
        try {
            Role r = rolerepository.findById(id).get();
            r.setIsdeleted(1);
            r.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            rolerepository.save(r);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
        }
    }

    /**
     * 查找指定角色
     * 
     * @param id 需查找用户的id
     * 
     * @return 成功查找的角色
     */
    public RoleVO findById(Long id) {
        try {
            return new RoleVO(rolerepository.findById(id).get());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
        }
    }
}
