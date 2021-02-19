package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.Auth;
import com.jd.iot.admin.repository.AuthRepository;
import com.jd.iot.admin.vo.AuthVO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//Service for 授权
@Service
public class AuthService {

    @Autowired
    AuthRepository authrepository;

    /**
     * 查询权限列表
     * 
     * @return 权限列表
     */
    public List<AuthVO> findAllAuth() {
        List<Auth> l = new ArrayList<Auth>();
        List<AuthVO> lv = new ArrayList<AuthVO>();
        authrepository.findAll().forEach(l::add);
        l.stream().filter(a -> a.getIsdeleted() != 1).map(b -> lv.add(new AuthVO(b))).collect(Collectors.toList());
        return lv;
    }

    /**
     * 添加权限
     * 
     * @param authvo 需添加的权限
     * 
     * @return 成功添加的权限
     */
    public AuthVO addAuth(AuthVO authvo) {
        Auth auth = new Auth(authvo);
        Long max = authrepository.maxId();
        if (max.equals(null)) {
            auth.setId(1L);
        } else {
            auth.setId(max + 1);
        }
        auth.setCreatetime(new Timestamp(System.currentTimeMillis()));
        auth.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new AuthVO(authrepository.save(auth));
    }

    /**
     * 删除权限
     * 
     * @param id 需删除权限的id
     */
    public void deleteAuth(Long id) {
        try {
            Auth auth = authrepository.findById(id).get();
            auth.setIsdeleted(1);
            auth.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            authrepository.save(auth);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AUTH NOT FOUND");
        }
    }

    /**
     * 修改权限
     * 
     * @param id     需修改权限的id
     * @param authvo 修改过的权限
     * 
     * @return 成功修改的权限
     */
    public AuthVO editAuthVO(Long id, AuthVO authvo) {
        try {
            authrepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AUTH NOT FOUND");
        }
        Auth auth = new Auth(authvo);
        auth.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new AuthVO(authrepository.save(auth));
    }

    /**
     * 通过id查找指定权限
     * 
     * @param id 需查找权限的id
     * 
     * @return 指定权限
     */
    public AuthVO findById(Long id) {
        try {
            return new AuthVO(authrepository.findById(id).get());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AUTH NOT FOUND");
        }
    }
}
