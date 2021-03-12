package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.UserRole;
import com.jd.iot.admin.repository.RoleRepository;
import com.jd.iot.admin.repository.UserRepository;
import com.jd.iot.admin.repository.UserRoleRepository;
import com.jd.iot.admin.vo.UserRoleVO;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//用户角色服务
@Service
public class UserRoleService {

    @Autowired
    UserRoleRepository userrolerepository;

    @Autowired
    RoleRepository rolerepository;

    @Autowired
    UserRepository userrepository;

    /**
     * 添加用户角色
     * 
     * @param role 需添加的用户角色
     * 
     * @return 成功添加的用户角色
     */
    public UserRoleVO addUserRole(UserRoleVO userrolevo) {
        UserRole userrole = new UserRole(userrolevo);
        userrole.setCreatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        userrole.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        return new UserRoleVO(userrolerepository.save(userrole));
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
            userrole.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            userrolerepository.save(userrole);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USERROLE NOT FOUND");
        }
    }

    /**
     * 修改用户角色
     * 
     * @param id         需修改用户角色的id
     * @param userrolevo 修改过的用户角色
     * 
     * @return 成功修改的用户角色
     */
    public UserRoleVO editUserRole(Long id, UserRoleVO userrolevo) {
        try {
            userrolerepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USERROLE NOT FOUND");
        }
        UserRole userrole = new UserRole(userrolevo);
        userrole.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        return new UserRoleVO(userrolerepository.save(userrole));
    }

    /**
     * 查询用户角色列表
     * 
     * @return 用户角色列表
     */
    public List<UserRoleVO> findAllUserRole() {
        return userrolerepository.findAllUserRole();
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
        List<UserRoleVO> lv = userrolerepository.findAllUserRolePaginated(pageable);
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
        }
        return userrolerepository.count() / 20;
    }
}