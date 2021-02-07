package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.UserRole;
import com.jd.iot.admin.repository.UserRoleRepository;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//Service for 用户角色实体
@Service
public class UserRoleService {

    @Autowired
    UserRoleRepository userrolerepository;

    /**
     * . 查询用户角色列表
     * 
     * @return 用户角色列表
     */
    public List<UserRole> findAllUserRole() {
        List<UserRole> e = new ArrayList<UserRole>();
        userrolerepository.findAll().forEach(e::add);
        return e;
    }

    /**
     * 添加用户角色
     * 
     * @param role 需添加的用户角色
     * 
     * @return 成功添加的用户角色
     */
    public UserRole addUserRole(UserRole userrole) {
        Long max = userrolerepository.maxId();
        if (max == null) {
            userrole.setId(1L);
        } else {
            userrole.setId(max + 1);
        }
        userrole.setCreatetime(new Timestamp(System.currentTimeMillis()));
        userrole.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return userrolerepository.save(userrole);
    }

    /**
     * . 通过id查找指定角色
     * 
     * @param id 需查找角色的id
     * 
     * @return 指定角色实体
     */
    public UserRole findById(Long id) {
        try {
            return userrolerepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
        }
    }
}