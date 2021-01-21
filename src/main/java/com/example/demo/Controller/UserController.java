package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;

//controller for 用户实体
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userservice;

	//add new user instance
	@PostMapping("/adduser")
	public void addUser(@RequestBody User user) {
		userservice.addUser(user);
	}

	//return all the user instances
	@GetMapping("/findalluser")
	public List<User> findAllUser() {
		return userservice.findAllUser();
	}

	//find an existing user instance by its id
	@PostMapping("/findbyid/{id}")
	public User findById(@PathVariable Long id) {
		return userservice.findById(id);
	}

	//edit an existing user instance
	@PutMapping("/edituser/{id}")
	public void editUser(@PathVariable Long id, @RequestBody User user) {
		userservice.editUser(id, user);
	}
	
	//delete an existing user instance
	@DeleteMapping("/deleteuser/{id}")
	public void deleteUser(@PathVariable Long id) {
		userservice.deleteUser(id);
	}
}
