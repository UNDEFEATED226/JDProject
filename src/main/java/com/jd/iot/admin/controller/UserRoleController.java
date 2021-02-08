package com.jd.iot.admin.controller;

import com.google.gson.Gson;
import com.jd.iot.admin.entity.UserRole;
import com.jd.iot.admin.service.UserRoleService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/userrole")
public class UserRoleController {

    @Autowired
    UserRoleService userroleservice;

    private static final Logger log = LoggerFactory.getLogger(UserRoleController.class);

    Gson gson = new Gson();

    /**
     * 返回所有用户角色实体
     * 
     * @return
     */
    @GetMapping("/findalluserrole")
    public List<UserRole> findAllRole() {
        log.info("查找所有用户角色:[{}]", gson.toJson(userroleservice.findAllUserRole()));
        return userroleservice.findAllUserRole();
    }

    /**
     * 通过id查找指定用户角色
     * 
     * @param id
     * 
     * @return 指定用户实体
     */
    @GetMapping("/findbyid")
    public UserRole findById(Long id) {
        try {
            log.info("查找用户角色id:[{}],角色:{}", id, gson.toJson(userroleservice.findById(id)));
            return userroleservice.findById(id);
        } catch (Exception e) {
            log.error("查找用户角色id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
        }
    }

    /**
     * 添加单个用户角色实体
     * 
     * @param role 单个用户角色实体
     * 
     * @return
     */
    @PostMapping("/adduserrole")
    public UserRole addRole(@RequestBody UserRole userrole) {
        log.info("添加用户角色:[{}]", gson.toJson(userrole));
        return userroleservice.addUserRole(userrole);
    }
}