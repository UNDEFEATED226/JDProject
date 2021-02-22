package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.UserRole;
import com.jd.iot.admin.repository.UserRoleRepository;
import com.jd.iot.admin.vo.UserRoleVO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//Service for 用户角色
@Service
public class UserRoleService {

    @Autowired
    UserRoleRepository userrolerepository;

    /**
     * 查询用户角色列表
     * 
     * @return 用户角色列表
     */
    public List<UserRoleVO> findAllUserRole() {
        List<UserRoleVO> lv = new ArrayList<UserRoleVO>();
        userrolerepository.findAllUserRole().stream().map(u -> lv.add(new UserRoleVO(u))).collect(Collectors.toList());
        return lv;
    }

    /**
     * 根据页号查询指定用户角色列表
     * 
     * @param pageNo 页号
     * 
     * @return 指定用户角色列表
     */
    public Page<UserRoleVO> findAllUserRolePaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 20);
        List<UserRoleVO> lv = new ArrayList<UserRoleVO>();
        userrolerepository.findAllUserRolePaginated(pageable).stream().map(u -> lv.add(new UserRoleVO(u)))
                .collect(Collectors.toList());
        return new PageImpl<UserRoleVO>(lv);
    }

    /**
     * 查询总用户角色数量
     * 
     * @return 总用户角色数量
     */
    public long count() {
        return userrolerepository.count();
    }

    /**
     * 查询总页数
     * 
     * @return 总页数
     */
    public long page() {
        if (userrolerepository.count() % 20 != 0) {
            return userrolerepository.count() / 20 + 1;
        } else {
            return userrolerepository.count() / 20;
        }
    }

    /**
     * 添加用户角色
     * 
     * @param role 需添加的用户角色
     * 
     * @return 成功添加的用户角色
     */
    public UserRoleVO addUserRole(UserRoleVO userrolevo) {
        UserRole userrole = new UserRole(userrolevo);
        Long max = userrolerepository.maxId();
        if (max == null) {
            userrole.setId(1L);
        } else {
            userrole.setId(max + 1);
        }
        userrole.setCreatetime(new Timestamp(System.currentTimeMillis()));
        userrole.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new UserRoleVO(userrolerepository.save(userrole));
    }

    /**
     * 通过id查找指定用户角色
     * 
     * @param id 需查找用户角色的id
     * 
     * @return 指定用户角色
     */
    public UserRoleVO findById(Long id) {
        try {
            return new UserRoleVO(userrolerepository.findById(id).get());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USERROLE NOT FOUND");
        }
    }

    /**
     * 删除用户角色
     * 
     * @param id 需删除用户角色的id
     */
    public void deleteUserRole(Long id) {
        try {
            UserRole userrole = userrolerepository.findById(id).get();
            userrole.setIsdeleted(1);
            userrole.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            userrolerepository.save(userrole);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USERROLE NOT FOUND");
        }
    }
}