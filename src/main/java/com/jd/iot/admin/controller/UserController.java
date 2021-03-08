package com.jd.iot.admin.controller;

import com.google.gson.Gson;
import com.jd.iot.admin.service.UserService;
import com.jd.iot.admin.vo.UserVO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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

//Controller for 用户实体
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userservice;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * 删除用户
     * 
     * @param id 需删除用户的id
     */
    @GetMapping("/deleteuser")
    public void deleteUser(Long id) {
        try {
            log.info("删除用户id:[{}]", id);
            userservice.deleteUser(id);
        } catch (ResponseStatusException e) {
            log.error("删除用户id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
        }
    }

    /**
     * 修改指定用户
     * 
     * @param id   用户id
     * @param user 修改后的用户实体
     * 
     * @return 成功修改后的用户实体
     */
    @PostMapping("/edituser/{id}")
    public UserVO editUser(@PathVariable Long id, @RequestBody @Validated UserVO uservo) {
        try {
            log.info("修改用户id:[{}],用户:{}", id, gson.toJson(uservo));
            return userservice.editUser(id, uservo);
        } catch (ResponseStatusException e) {
            log.error("修改用户id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }
    }

    /**
     * 用id查找指定用户
     * 
     * @param id
     * 
     * @return 用户实体
     */
    @GetMapping("/findbyid")
    public UserVO findById(Long id) {
        try {
            log.info("查找用户id:[{}],用户:{}", id, gson.toJson(userservice.findById(id)));
            return userservice.findById(id);
        } catch (Exception e) {
            log.error("查找用户id:[{}]失败:{}", id, e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
        }
    }

    Gson gson = new Gson();

    /**
     * 添加单个用户
     * 
     * 
     * @param user
     * 
     * @return 添加成功的用户实体
     */
    @PostMapping("/adduser")
    public UserVO addUser(@RequestBody @Validated UserVO uservo) {
        try {
            log.info("添加用户:{}", gson.toJson(uservo));
            return userservice.addUser(uservo);
        } catch (ResponseStatusException e) {
            log.error("添加用户[{}]失败", gson.toJson(uservo));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ADD USER FAILURE");
        }
    }

    /**
     * 查找所有用户实体
     * 
     * @return 所有用户实体
     */
    @GetMapping("/findalluser")
    public List<UserVO> findAllUser() {
        log.info("查找所有用户:[{}]", gson.toJson(userservice.findAllUser()));
        return userservice.findAllUser();
    }

    /**
     * 查询指定页号的用户列表
     * 
     * @param pageNo 页号
     * 
     * @return 指定页号的用户列表
     */
    @GetMapping("/findalluserpaginated")
    public Page<UserVO> findAllUserPaginated(int pageNo) {
        log.info("查询第{}页用户列表:[{}]", pageNo, gson.toJson(userservice.findAllUserPaginated(pageNo)));
        return userservice.findAllUserPaginated(pageNo);
    }

    /**
     * 查询总用户数量
     * 
     * @return 总用户数量
     */
    @GetMapping("/count")
    public long count() {
        log.info("查询总用户数量:[{}]", userservice.count());
        return userservice.count();
    }

    /**
     * 查询总页数
     * 
     * @return 总页数
     */
    @GetMapping("/page")
    public long page() {
        log.info("查询用户页数:[{}]", userservice.page());
        return userservice.page();
    }
}