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

	@PostMapping("/adduser/{orgid}")
	public User addUser(@PathVariable("orgid") Long orgid,@RequestBody User user) {
		User u = userservice.addUser(user, orgid);
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
			log.error("查找用户id:[{}]时报错,错误信息:{}", id, e.toString());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
		}
	}
}
