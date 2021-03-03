package com.jd.iot.admin.controller;

import com.google.gson.Gson;
import com.jd.iot.admin.service.RoleService;
import com.jd.iot.admin.vo.RoleVO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
     * 查找指定角色
     * 
     * @param id 需查找用户的id
     * 
     * @return 成功查找的角色
     */
    @GetMapping("/findbyid")
    public RoleVO findById(Long id) {
        try {
            log.info("查找id为{}的角色:[{}]", id, gson.toJson(roleservice.findById(id)));
            return roleservice.findById(id);
        } catch (ResponseStatusException e) {
            log.info("查找id为{}的角色失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
        }
    }

    /**
     * 查询角色列表
     * 
     * @return 角色列表
     */
    @GetMapping("/findallrole")
    public List<RoleVO> findAllRole() {
        log.info("查询角色列表:[{}]", gson.toJson(roleservice.findAllRole()));
        return roleservice.findAllRole();
    }

    /**
     * 查询指定页号的角色列表
     * 
     * @param pageNo 指定页号
     * 
     * @return 指定页号的角色列表
     */
    @GetMapping("/findallrolepaginated")
    public Page<RoleVO> findAllRolePaginated(int pageNo) {
        log.info("查询第{}页的角色列表:[{}]", pageNo, gson.toJson(roleservice.findAllRolePaginated(pageNo)));
        return roleservice.findAllRolePaginated(pageNo);
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
     * 查询指定角色种类和指定页号的角色列表
     * 
     * @param roletype 指定角色种类
     * @param pageNo   指定页号
     * 
     * @return 指定角色种类和指定页号的角色列表
     */
    @GetMapping("/rolemenupaginated")
    public Page<RoleVO> roleMenuPaginated(Long roletype, int pageNo) {
        log.info("查询第{}页的角色列表(角色类型为{}):[{}]", pageNo, roletype,
                gson.toJson(roleservice.roleMenuPaginated(roletype, pageNo)));
        return roleservice.roleMenuPaginated(roletype, pageNo);
    }

    /**
     * 查询总角色数量
     * 
     * @return 总角色数量
     */
    @GetMapping("/count")
    public long count() {
        log.info("查询总角色数量:{}", roleservice.count());
        return roleservice.count();
    }

    /**
     * 根据指定role type查询总角色数量
     * 
     * @param roletype 指定role type
     * 
     * @return 总角色数量
     */
    @GetMapping("/countbyroletype")
    public long countByRoletype(Long roletype) {
        log.info("查询总角色数量(根据role type:{}):{}", roletype, roleservice.countByRoletype(roletype));
        return roleservice.countByRoletype(roletype);
    }

    /**
     * 查询总页数
     * 
     * @return 总页数
     */
    @GetMapping("/page")
    public long page() {
        log.info("查询角色总页数:{}", roleservice.page());
        return roleservice.page();
    }

    /**
     * 根据role type查询总页数
     * 
     * @param roletype 指定role type
     * 
     * @return 总页数
     */
    @GetMapping("/pagebyroletype")
    public long pageByRoletype(Long roletype) {
        log.info("查询角色总页数(根据role type:{}):{}", roletype, roleservice.pageByRoletype(roletype));
        return roleservice.pageByRoletype(roletype);
    }
}
