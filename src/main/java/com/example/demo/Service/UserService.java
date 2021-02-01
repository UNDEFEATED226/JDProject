package com.example.demo.Service;

import java.math.BigInteger;
import org.slf4j.Logger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.Entity.Organization;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.PasswordEncrypt.passEncrypt;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

//Service for User Entity
@Slf4j
@Service
public class UserService {

	@Autowired
	UserRepository userrepository;

	@Autowired
	RoleService roleservice;

	@Autowired
	OrganizationService organizationservice;
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	/**
	 * 为id生成MD5值
	 * 
	 * @param id 需要生成MD5值的id
	 * 
	 * @return 生成的MD5值
	 */
	public static String getMD5(String id) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(id.getBytes());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			log.info("生成MD5失败:"+e.toString());
			return null;
		}
	}

	/**
	 * 查找并返回所有用户实体
	 * 
	 * @return
	 */
	public List<User> findAllUser() {
		List<User> l = new ArrayList<User>();
		userrepository.findAll().forEach(l::add);
		return l;
	}

	/**
	 * 通过id查找指定用户
	 * 
	 * @param id
	 * 
	 * @return 指定用户实体
	 */
	public User findById(Long id) {
		try {
			return userrepository.findById(id).get();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
		}
	}

	/**
	 * 通过id删除指定用户
	 * 
	 * @param id
	 */
	public void deleteUser(Long id) {
		try {
			userrepository.findById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
		}
		userrepository.deleteById(id);
	}

	/**
	 * 添加单个用户
	 * 
	 * @param user
	 * 
	 * @return
	 */
	public User addUser(User user) {
		Organization organization;
		try {
			organization = organizationservice.findById(Long.parseLong(user.getOrgid()));
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGANIZATION NOT FOUND");
		}
		user.setTenantid(BigInteger.valueOf(Long.parseLong(organization.getTenantid())));
		Long max = maxId();
		if (max == null) {
			user.setId(1l);
			user.setUserid("jd-iot-" + getMD5(String.valueOf(1l)));
		} else {
			user.setId(max + 1);
			user.setUserid("jd-iot-" + getMD5(String.valueOf(max + 1)));
		}
		user.setPassword(passEncrypt.getMD5(user.getPassword()));
		user.setCreatetime(new Timestamp(System.currentTimeMillis()));
		user.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		User u = userrepository.save(user);
		Role r = new Role();
		r.setUserid(u.getId());
		r.setRoleid(1l);
		roleservice.addRole(r);
		return u;
	}

	/**
	 * 通过id编辑指定用户
	 * 
	 * @param id   想要修改的用户的id
	 * @param user 修改完的用户实体
	 */
	public User editUser(Long id, User user) {
		try {
			userrepository.findById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
		}
		user.setPassword(passEncrypt.getMD5(user.getPassword()));
		user.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		return userrepository.save(user);
	}

	/**
	 * 返回最大id值
	 * 
	 * @return
	 */
	public Long maxId() {
		return userrepository.maxId();
	}
}
