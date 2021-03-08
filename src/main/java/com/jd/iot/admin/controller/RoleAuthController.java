package com.jd.iot.admin.controller;

import com.jd.iot.admin.service.RoleAuthService;
import com.jd.iot.admin.vo.AuthVO;
import com.jd.iot.admin.vo.RoleAuthVO;
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
import com.google.gson.Gson;

@RestController
@RequestMapping("/roleauth")
public class RoleAuthController {

    @Autowired
    RoleAuthService roleauthservice;

    public static final Logger log = LoggerFactory.getLogger(RoleAuthController.class);

    Gson gson = new Gson();

    /**
     * 添加角色权限
     * 
     * @param roleauthvo 需添加的角色权限
     * 
     * @return 成功添加的角色权限
     */
    @PostMapping("/addroleauth")
    public RoleAuthVO addRoleAuth(@RequestBody @Validated RoleAuthVO roleauthvo) {
        log.info("添加角色权限:[{}]", gson.toJson(roleauthvo));
        return roleauthservice.addRoleAuth(roleauthvo);
    }

    /**
     * 删除角色权限
     * 
     * @param id 需删除角色权限的id
     */
    @GetMapping("/deleteroleauth")
    public void deleteRoleAuth(Long id) {
        try {
            log.info("删除角色权限id:[{}]", id);
            roleauthservice.deleteRoleAuth(id);
        } catch (ResponseStatusException e) {
            log.error("删除角色权限id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLEAUTH NOT FOUND");
        }
    }

    /**
     * 修改角色权限
     * 
     * @param id         需修改角色权限的id
     * @param roleauthvo 修改过的角色权限
     * 
     * @return 成功修改的角色权限
     */
    @PostMapping("/editroleauth/{id}")
    public RoleAuthVO editRoleAuth(@PathVariable Long id, @RequestBody @Validated RoleAuthVO roleauthvo) {
        try {
            log.info("修改角色权限id:[{}], 角色权限:{}", id, gson.toJson(roleauthvo));
            return roleauthservice.editRoleAuth(id, roleauthvo);
        } catch (ResponseStatusException e) {
            log.error("修改角色权限id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLEAUTH NOT FOUND");
        }
    }

    /**
     * 查询角色权限列表
     * 
     * @return 角色权限列表
     */
    @GetMapping("/findallroleauth")
    public List<RoleAuthVO> findAllRoleAuth() {
        log.info("查询角色权限列表:[{}]", gson.toJson(roleauthservice.findAllRoleAuth()));
        return roleauthservice.findAllRoleAuth();
    }

    /**
     * 根据页号查询指定角色权限列表
     * 
     * @param pageNo 页号
     * 
     * @return 指定角色权限列表
     */
    @GetMapping("/findallroleauthpaginated")
    public Page<RoleAuthVO> findAllRoleAuthPaginated(int pageNo) {
        log.info("查询第{}页角色权限列表:[{}]", pageNo, gson.toJson(roleauthservice.findAllRoleAuthPaginated(pageNo)));
        return roleauthservice.findAllRoleAuthPaginated(pageNo);
    }

    /**
     * 查询总角色权限数量
     * 
     * @return 总角色权限数量
     */
    @GetMapping("/count")
    public long count() {
        log.info("查询总角色权限数量:{}", roleauthservice.count());
        return roleauthservice.count();
    }

    /**
     * 查询总页数
     * 
     * @return 总页数
     */
    @GetMapping("/page")
    public long page() {
        log.info("查询总角色权限页数:{}", roleauthservice.page());
        return roleauthservice.page();
    }

    /**
     * 更新指定角色的权限
     * 
     * @param roleid 指定角色id
     * @param l      更新后的权限列表
     * 
     */
    @PostMapping("/changeauth/{roleid}")
    public void changeAuth(@PathVariable Long roleid, @RequestBody List<List<AuthVO>> l) {
        log.info("更新指定角色权限...");
        roleauthservice.changeAuth(roleid, l);
    }

    /**
     * 根据指定角色id查询权限列表(权限列表中角色拥有权限的selected属性为true,反之则为false)
     * 
     * @param roleid 指定角色id
     * 
     * @return 权限列表
     */
    @GetMapping("/findauthbyroleid")
    public List<List<AuthVO>> findAuthByRoleid(Long roleid) {
        log.info("根据角色id查询权限列表:[]", roleid, gson.toJson(roleauthservice.findAuthByRoleid(roleid)));
        return roleauthservice.findAuthByRoleid(roleid);
    }
}
