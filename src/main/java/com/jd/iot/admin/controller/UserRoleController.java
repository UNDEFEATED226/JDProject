package com.jd.iot.admin.controller;

import com.google.gson.Gson;
import com.jd.iot.admin.service.UserRoleService;
import com.jd.iot.admin.vo.UserRoleVO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
     * 添加用户角色
     * 
     * @param userrole 需添加的用户角色
     * 
     * @return 成功添加的用户角色
     */
    @PostMapping("/adduserrole")
    public UserRoleVO addUserRole(@RequestBody @Validated UserRoleVO userrolevo) {
        log.info("添加用户角色:[{}]", gson.toJson(userrolevo));
        return userroleservice.addUserRole(userrolevo);
    }

    /**
     * 删除用户角色
     * 
     * @param id 需删除用户角色的id
     */
    @GetMapping("/deleteuserrole")
    public void deleteUserRole(Long id) {
        try {
            log.info("删除用户角色id:[{}]", id);
            userroleservice.deleteUserRole(id);
        } catch (Exception e) {
            log.error("删除用户角色id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USERROLE NOT FOUND");
        }
    }
    
    /**
     * 通过id查找指定用户角色
     * 
     * @param id 需查找用户角色的id
     * 
     * @return 指定用户角色
     */
    @GetMapping("/findbyid")
    public UserRoleVO findById(Long id) {
        try {
            log.info("查找用户角色id:[{}],角色:{}", id, gson.toJson(userroleservice.findById(id)));
            return userroleservice.findById(id);
        } catch (Exception e) {
            log.error("查找用户角色id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
        }
    }

    /**
     * 查询用户角色列表
     * 
     * @return 用户角色列表
     */
    @GetMapping("/findalluserrole")
    public List<UserRoleVO> findAllUserRole() {
        log.info("查找所有用户角色:[{}]", gson.toJson(userroleservice.findAllUserRole()));
        return userroleservice.findAllUserRole();
    }

    /**
     * 根据页号查询指定用户角色列表
     * 
     * @param pageNo 页号
     * 
     * @return 指定用户角色列表
     */
    @GetMapping("/findalluserrolepaginated")
    public Page<UserRoleVO> findAllUserRolePaginated(int pageNo) {
        log.info("查询第{}页用户角色列表:[{}]", pageNo, gson.toJson(userroleservice.findAllUserRolePaginated(pageNo)));
        return userroleservice.findAllUserRolePaginated(pageNo);
    }

    /**
     * 查询总用户角色数量
     * 
     * @return 总用户角色数量
     */
    @GetMapping("/count")
    public long count() {
        log.info("查询总用户角色数量:{}", gson.toJson(userroleservice.count()));
        return userroleservice.count();
    }

    /**
     * 查询总页数
     * 
     * @return 总页数
     */
    @GetMapping("/page")
    public long page() {
        log.info("查询总页数:{}", gson.toJson(userroleservice.page()));
        return userroleservice.page();
    }
}