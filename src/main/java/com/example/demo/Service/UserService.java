package com.example.demo.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
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

	//根据id生成MD5 32位
    public static String getMD5(String str) {
        try {           
            MessageDigest md = MessageDigest.getInstance("MD5");        
            md.update(str.getBytes());  
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }
    
    //for MD5 generator
    public static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};       
        try {
            byte[] btInput = s.getBytes();
          
            MessageDigest mdInst = MessageDigest.getInstance("MD5");            
            mdInst.update(btInput);            
            byte[] md = mdInst.digest();          
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
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
	public User addUser(User user) {
		Long max = maxId();
		if (max == null) {
			user.setId(1l);
			user.setUserid("jd-iot-"+getMD5(String.valueOf(1l)));
		} else {
			user.setId(max + 1);
			user.setUserid("jd-iot-"+getMD5(String.valueOf(max+1)));
		}
		user.setCreatetime(new Timestamp(System.currentTimeMillis()));
		user.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		return userrepository.save(user);
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
	
	//返回当前最大id值
	public Long maxId() {
		return userrepository.maxId();
	}
}
