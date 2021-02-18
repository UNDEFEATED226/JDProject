package com.jd.iot.admin.controller;

import com.google.gson.Gson;
import com.jd.iot.admin.service.RoleService;
import com.jd.iot.admin.vo.RoleVO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleservice;

    public static final Logger log = LoggerFactory.getLogger(RoleController.class);

    Gson gson = new Gson();

    /**
     * 查询角色列表
     * 
     * @return 所有角色
     */
    @GetMapping("/findallrole")
    public List<RoleVO> findAllRole() {
        log.info("查询角色列表:[{}]", gson.toJson(roleservice.findAllRole()));
        return roleservice.findAllRole();
    }

    /**
     * 查询所有指定角色种类的角色
     * 
     * @param roletype 角色种类
     * 
     * @return 所有指定角色种类的角色
     */
    @GetMapping("/rolemenu")
    public List<RoleVO> roleMenu(Long roletype) {
        log.info("查询角色种类为{}的列表:[{}]", roletype, gson.toJson(roleservice.roleMenu(roletype)));
        return roleservice.roleMenu(roletype);
    }

    /**
     * 添加角色
     * 
     * @param rolevo 需添加的角色
     * 
     * @return 成功添加的角色
     */
    @PostMapping("/addrole")
    public RoleVO addRole(@RequestBody @Validated RoleVO rolevo) {
        log.info("添加角色:[{}]", gson.toJson(rolevo));
        return roleservice.addRole(rolevo);
    }

    /**
     * 修改角色
     * 
     * @param id     需修改角色的id
     * @param rolevo 修改过的角色
     * 
     * @return 成功修改的角色
     */
    @PostMapping("/editrole/{id}")
    public RoleVO editRole(@PathVariable Long id, @RequestBody @Validated RoleVO rolevo) {
        try {
            log.info("修改角色id:[{}],角色:{}", id, gson.toJson(roleservice.findAllRole()));
            return roleservice.editRole(id, rolevo);
        } catch (ResponseStatusException e) {
            log.error("修改角色id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
        }
    }

    /**
     * 删除角色
     * 
     * @param id 需删除角色的id
     */
    @GetMapping("/deleterole")
    public void deleteRole(Long id) {
        try {
            log.info("删除角色id:[{}]", id);
            roleservice.deleteRole(id);
        } catch (ResponseStatusException e) {
            log.error("删除角色id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
        }
    }

    @GetMapping("/findbyid")
    public RoleVO findById(Long id) {
        try {
            return roleservice.findById(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
        }
    }
}
