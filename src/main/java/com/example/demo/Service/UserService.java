package com.example.demo.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.Repository.UserRepository;

//Service for User Entity
@Service
public class UserService {

	@Autowired
	UserRepository userrepository;

	// return all the user instances
	public List<User> findAllUser() {
		List<User> l = new ArrayList<User>();
		userrepository.findAll().forEach(l::add);
		return l;
	}

	// find a User instance by its id
	public User findById(Long id) {
		try {
			return userrepository.findById(id).get();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
		}
	}

	// delete an existing User instance
	public void deleteUser(Long id) {
		try {
			userrepository.findById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
		}
		userrepository.deleteById(id);
	}

	// add new User instance
	public void addUser(User user) {
		Long max = userrepository.maxId();
		if (max == null) {
			user.setId(1l);
		} else {
			user.setId(max + 1);
		}
		user.setCreatetime(new Timestamp(System.currentTimeMillis()));
		user.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		userrepository.save(user);
	}

	// edit an existing User Entity
	public void editUser(Long id, User user) {
		try {
			userrepository.findById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
		}
		user.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		userrepository.save(user);
	}
}
