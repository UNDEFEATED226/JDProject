package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.Role;
import com.jd.iot.admin.repository.RoleRepository;
import com.jd.iot.admin.vo.RoleVO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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

//Service for 角色
@Service
public class RoleService {

    @Autowired
    RoleRepository rolerepository;

    /**
     * 添加角色
     * 
     * @param rolevo 需添加的角色
     * 
     * @return 成功添加的角色
     */
    public RoleVO addRole(RoleVO rolevo) {
        Role role = new Role(rolevo);
        role.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        role.setCreatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        return new RoleVO(rolerepository.save(role));
    }

    /**
     * 删除角色
     * 
     * @param id 需删除角色的id
     */
    public void deleteRole(Long id) {
        try {
            Role role = rolerepository.findById(id).get();
            role.setIsdeleted(1);
            role.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            rolerepository.save(role);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
        }
    }

    /**
     * 修改角色
     * 
     * @param id     需修改角色的id
     * @param rolevo 修改过的角色
     * 
     * @return 成功修改的角色
     */
    public RoleVO editRole(Long id, RoleVO rolevo) {
        try {
            rolerepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
        }
        Role role = new Role(rolevo);
        role.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        return new RoleVO(rolerepository.save(role));
    }

    /**
     * 查找指定角色
     * 
     * @param id 需查找用户的id
     * 
     * @return 成功查找的角色
     */
    public RoleVO findById(Long id) {
        try {
            return new RoleVO(rolerepository.findById(id).get());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLE NOT FOUND");
        }
    }

    /**
     * 查询角色列表
     * 
     * @return 角色列表
     */
    public List<RoleVO> findAllRole() {
        List<RoleVO> roleList = new ArrayList<RoleVO>();
        rolerepository.findAllRole().stream().map(role -> roleList.add(new RoleVO(role))).collect(Collectors.toList());
        return roleList;
    }

    /**
     * 查询指定页号的角色列表
     * 
     * @param pageNo 指定页号
     * 
     * @return 指定页号的角色列表
     */
    public Page<RoleVO> findAllRolePaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 20);
        List<RoleVO> roleList = new ArrayList<RoleVO>();
        rolerepository.findAllRolePaginated(pageable).stream().map(role -> roleList.add(new RoleVO(role)))
                .collect(Collectors.toList());
        return new PageImpl<RoleVO>(roleList);
    }

    /**
     * 查询所有指定角色种类的角色
     * 
     * @param roletype 角色种类
     * 
     * @return 所有指定角色种类的角色
     */
    public List<RoleVO> roleMenu(Long roletype) {
        List<RoleVO> roleList = new ArrayList<RoleVO>();
        rolerepository.findAllByRoletype(roletype).stream().map(role -> roleList.add(new RoleVO(role)))
                .collect(Collectors.toList());
        return roleList;
    }

    /**
     * 查询指定角色种类和指定页号的角色列表
     * 
     * @param roletype 指定角色种类
     * @param pageNo   指定页号
     * 
     * @return 指定角色种类和指定页号的角色列表
     */
    public Page<RoleVO> roleMenuPaginated(Long roletype, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 20);
        List<RoleVO> roleList = new ArrayList<RoleVO>();
        rolerepository.findAllByRoletypePaginated(roletype, pageable).stream()
                .map(role -> roleList.add(new RoleVO(role))).collect(Collectors.toList());
        return new PageImpl<RoleVO>(roleList);
    }

    /**
     * 查询总角色数量
     * 
     * @return 总角色数量
     */
    public long count() {
        return rolerepository.count();
    }

    /**
     * 根据指定role type查询总角色数量
     * 
     * @param roletype 指定role type
     * 
     * @return 总角色数量
     */
    public long countByRoletype(Long roletype) {
        return rolerepository.countByRoletype(roletype);
    }

    /**
     * 查询总页数
     * 
     * @return 总页数
     */
    public long page() {
        if (rolerepository.count() % 20 != 0) {
            return rolerepository.count() / 20 + 1;
        }
        return rolerepository.count() / 20;
    }

    /**
     * 根据role type查询总页数
     * 
     * @param roletype 指定role type
     * 
     * @return 总页数
     */
    public long pageByRoletype(Long roletype) {
        if (rolerepository.countByRoletype(roletype) % 20 != 0) {
            return rolerepository.countByRoletype(roletype) / 20 + 1;
        }
        return rolerepository.countByRoletype(roletype) / 20;
    }
}
