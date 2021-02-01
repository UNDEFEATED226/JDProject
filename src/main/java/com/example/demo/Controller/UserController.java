package com.example.demo.Controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

//Controller for 用户实体
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userservice;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	Gson gson = new Gson();

	/**
	 * 添加单个用户
	 * 
	 * @param user
	 * 
	 * @return
	 */
	@PostMapping("/adduser")
	public User addUser(@RequestBody User user) {
		User u = new User();
		try {
			u = userservice.addUser(user);
		} catch (ResponseStatusException e) {
			log.error("添加用户[{}]失败,组织ID:{}不存在", gson.toJson(u), user.getOrgid());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGID DOES NOT EXIST");
		}
		log.info("添加用户[{}]", gson.toJson(u));
		return u;
	}

	/**
	 * 查找所有用户实体
	 * 
	 * @return
	 */
	@GetMapping("/findalluser")
	public List<User> findAllUser() {
		log.info("查找所有用户:[{}]", gson.toJson(userservice.findAllUser()));
		return userservice.findAllUser();
	}

	/**
	 * 用id查找指定用户
	 * 
	 * @param id
	 * 
	 * @return 用户实体
	 */
	@GetMapping("/findbyid")
	public User findById(Long id) {
		try {
			log.info("查找用户id:[{}],用户:", id, gson.toJson(userservice.findById(id)));
			return userservice.findById(id);
		} catch (Exception e) {
			log.error("查找用户id:[{}]:{}", id, e.toString());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
		}
	}

	/**
	 * 编辑指定用户
	 * 
	 * @param id 用户id
	 * @param user 修改后的用户实体
	 * 
	 * @return
	 */
	@PostMapping("/edituser/{id}")
	public User editUser(@PathVariable Long id, @RequestBody User user) {
		try {
			log.info("编辑用户id:[{}],用户:",id,gson.toJson(user));
			return userservice.editUser(id, user);
		}catch(ResponseStatusException e) {
			log.error("编辑用户id:[{}]失败",id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"NOT FOUND");
		}
	}
}
