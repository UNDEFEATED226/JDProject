package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.UserRole;
import com.jd.iot.admin.entity.User;
import com.jd.iot.admin.passwordencrypt.PassEncrypt;
import com.jd.iot.admin.repository.UserRepository;
import com.jd.iot.admin.vo.OrganizationVO;
import com.jd.iot.admin.vo.UserVO;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//Service for User Entity
@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userrepository;

    @Autowired
    UserRoleService userroleservice;

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
    public static String getMd5(String id) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(id.getBytes("UTF-8"));
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            log.info("生成MD5失败:" + e.toString());
            return null;
        }
    }

    /**
     * 查询用户列表
     * 
     * @return 用户列表
     */
    public List<UserVO> findAllUser() {
        List<User> l = new ArrayList<User>();
        List<UserVO> lv = new ArrayList<UserVO>();
        userrepository.findAll().forEach(l::add);
        l.stream().filter(u -> u.getIsdeleted() != 1).map(u -> lv.add(new UserVO(u))).collect(Collectors.toList());
        return lv;
    }

    /**
     * 通过id查找指定用户
     * 
     * @param id 需查找用户的id
     * 
     * @return 指定用户
     */
    public UserVO findById(Long id) {
        try {
            return new UserVO(userrepository.findById(id).get());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
        }
    }

    /**
     * 删除用户
     * 
     * @param id 需删除用户的id
     */
    public void deleteUser(Long id) {
        try {
            User user = userrepository.findById(id).get();
            user.setIsdeleted(1);
            user.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            userrepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
        }
    }

    /**
     * 添加用户
     * 
     * @param user 需添加的用户
     * 
     * @return 成功添加的用户
     * 
     */
    public UserVO addUser(UserVO uservo) {
        OrganizationVO organizationvo;
        try {
            organizationvo = organizationservice.findById(Long.parseLong(uservo.getOrgid()));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGANIZATION NOT FOUND");
        }
        User user = new User(uservo);
        user.setTenantid(Long.parseLong(organizationvo.getTenantid()));
        Long max = userrepository.maxId();
        if (max == null) {
            user.setId(1l);
            user.setUserid("jd-iot-" + getMd5(String.valueOf(1L)));
        } else {
            user.setId(max + 1);
            user.setUserid("jd-iot-" + getMd5(String.valueOf(max + 1)));
        }
        user.setPassword(PassEncrypt.getMd5(uservo.getPassword()));
        user.setCreatetime(new Timestamp(System.currentTimeMillis()));
        user.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        UserRole r = new UserRole();
        r.setUserid(user.getId());
        r.setRoleid(1L);
        userroleservice.addUserRole(r);
        return new UserVO(userrepository.save(user));
    }

    /**
     * 修改用户
     * 
     * @param id   需修改用户的id
     * @param user 修改过的用户
     * 
     * @return 成功修改的用户
     * 
     */
    public UserVO editUser(Long id, UserVO uservo) {
        OrganizationVO organizationvo;
        try {
            organizationvo = organizationservice.findById(Long.parseLong(uservo.getOrgid()));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGANIZATION NOT FOUND");
        }
        try {
            userrepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
        }
        User user = new User(uservo);
        user.setTenantid(Long.parseLong(organizationvo.getTenantid()));
        user.setPassword(PassEncrypt.getMd5(uservo.getPassword()));
        user.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new UserVO(userrepository.save(user));
    }
}