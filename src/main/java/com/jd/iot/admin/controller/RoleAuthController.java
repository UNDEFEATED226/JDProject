package com.jd.iot.admin.controller;

import com.jd.iot.admin.service.RoleAuthService;
import com.jd.iot.admin.vo.RoleAuthVO;
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
import com.google.gson.Gson;

@RestController
@RequestMapping("/roleauth")
public class RoleAuthController {

    @Autowired
    RoleAuthService roleauthservice;

    public static final Logger log = LoggerFactory.getLogger(RoleAuthController.class);

    Gson gson = new Gson();

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
     * 通过id查找指定角色权限
     * 
     * @param id 需查找角色权限的id
     * 
     * @return 指定角色权限
     */
    @GetMapping("/findbyid")
    public RoleAuthVO findById(Long id) {
        try {
            log.info("查找角色权限id:[{}],角色权限:{}", id, gson.toJson(roleauthservice.findById(id)));
            return roleauthservice.findById(id);
        } catch (ResponseStatusException e) {
            log.error("查找角色权限id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLEAUTH NOT FOUND");
        }
    }

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
}
