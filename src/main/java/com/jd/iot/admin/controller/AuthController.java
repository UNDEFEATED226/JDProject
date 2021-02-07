package com.jd.iot.admin.controller;

import com.jd.iot.admin.service.AuthService;
import com.jd.iot.admin.vo.AuthVO;
import com.google.gson.Gson;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authservice;

    public static final Logger log = LoggerFactory.getLogger(AuthController.class);

    Gson gson = new Gson();

    /**
     * 查询权限列表
     * 
     * @return 权限列表
     */
    @GetMapping("/findallauth")
    public List<AuthVO> findAllAuth() {
        log.info("查询权限列表:[{}]", gson.toJson(authservice.findAllAuth()));
        return authservice.findAllAuth();
    }

    /**
     * 添加权限
     * 
     * @param authvo 需添加的权限
     * 
     * @return 成功添加的权限
     */
    @PostMapping("/addauth")
    public AuthVO addAuth(@RequestBody AuthVO authvo) {
        log.info("添加权限:[{}]", gson.toJson(authvo));
        return authservice.addAuth(authvo);
    }

    /**
     * 修改权限
     * 
     * @param id     需修改权限的id
     * @param authvo 修改过的权限
     * 
     * @return 成功修改的权限
     */
    @PostMapping("/editauth/{id}")
    public AuthVO editAuth(@PathVariable Long id, @RequestBody AuthVO authvo) {
        try {
            log.info("修改权限id:[{}],权限:", id, authvo);
            return authservice.editAuthVO(id, authvo);
        } catch (ResponseStatusException e) {
            log.error("修改权限id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AUTH NOT FOUND");
        }
    }

    /**
     * 删除权限
     * 
     * @param id 需删除权限的id
     */
    @GetMapping("/deleteauth")
    public void deleteAuth(Long id) {
        try {
            log.info("删除权限id:[{}]", id);
            authservice.deleteAuth(id);
        } catch (ResponseStatusException e) {
            log.error("删除权限id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AUTH NOT FOUND");
        }
    }

    /**
     * 通过id查找指定权限
     * 
     * @param id 需查找权限的id
     * 
     * @return 指定权限
     */
    @GetMapping("/findbyid")
    public AuthVO findById(Long id) {
        try {
            log.info("查找权限id:[{}],权限:", id, gson.toJson(authservice.findById(id)));
            return authservice.findById(id);
        } catch (ResponseStatusException e) {
            log.error("查找权限id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AUTH NOT FOUND");
        }
    }
}
