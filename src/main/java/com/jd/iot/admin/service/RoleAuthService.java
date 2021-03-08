package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.RoleAuth;
import com.jd.iot.admin.repository.AuthRepository;
import com.jd.iot.admin.repository.ResourceRepository;
import com.jd.iot.admin.repository.RoleAuthRepository;
import com.jd.iot.admin.repository.RoleRepository;
import com.jd.iot.admin.vo.AuthVO;
import com.jd.iot.admin.vo.RoleAuthVO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//Service for 角色权限
@Service
public class RoleAuthService {

    @Autowired
    RoleAuthRepository roleauthrepository;

    @Autowired
    RoleRepository rolerepository;

    @Autowired
    AuthRepository authrepository;

    @Autowired
    ResourceRepository resourcerepository;

    @Autowired
    AuthService authservice;

    /**
     * 添加角色权限
     * 
     * @param roleauthvo 需添加的角色权限
     * 
     * @return 成功添加的角色权限
     */
    public RoleAuthVO addRoleAuth(RoleAuthVO roleauthvo) {
        RoleAuth role = new RoleAuth(roleauthvo);
        role.setCreatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        role.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        return new RoleAuthVO(roleauthrepository.save(role));
    }

    /**
     * 删除角色权限
     * 
     * @param id 需删除角色权限的id
     */
    public void deleteRoleAuth(Long id) {
        try {
            RoleAuth roleauth = roleauthrepository.findById(id).get();
            roleauth.setIsdeleted(1);
            roleauth.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            roleauthrepository.save(roleauth);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLEAUTH NOT FOUND");
        }
    }

    /**
     * 修改角色权限
     * 
     * @param id         需修改角色权限的id
     * @param roleauthvo 修改过的角色权限
     * 
     * @return 成功修改的角色权限
     */
    public RoleAuthVO editRoleAuth(Long id, RoleAuthVO roleauthvo) {
        try {
            roleauthrepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLEAUTH NOT FOUND");
        }
        RoleAuth roleauth = new RoleAuth(roleauthvo);
        roleauth.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        return new RoleAuthVO(roleauthrepository.save(roleauth));
    }

    /**
     * 查询角色权限列表
     * 
     * @return 角色权限列表
     */
    public List<RoleAuthVO> findAllRoleAuth() {
        return roleauthrepository.findAllRoleAuth();
    }

    /**
     * 根据页号查询指定角色权限列表
     * 
     * @param pageNo 页号
     * 
     * @return 指定角色权限列表
     */
    public Page<RoleAuthVO> findAllRoleAuthPaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 20);
        List<RoleAuthVO> lv = roleauthrepository.findAllRoleAuthPaginated(pageable);
        return new PageImpl<RoleAuthVO>(lv);
    }

    /**
     * 查询总角色权限数量
     * 
     * @return 总角色权限数量
     */
    public long count() {
        return roleauthrepository.count();
    }

    /**
     * 查询总页数
     * 
     * @return 总页数
     */
    public long page() {
        if (roleauthrepository.count() % 20 != 0) {
            return roleauthrepository.count() / 20 + 1;
        }
        return roleauthrepository.count() / 20;

    }

    /**
     * 更新指定角色的权限
     * 
     * @param roleid 指定角色id
     * @param l      更新后的权限列表
     * 
     */
    public void changeAuth(Long roleid, List<List<AuthVO>> newAuthList) {
        List<List<AuthVO>> originalAuthList = findAuthByRoleid(roleid);
        for (int i = 0; i < newAuthList.size(); i++) {
            for (int j = 0; j < newAuthList.get(i).size(); j++) {
                if (newAuthList.get(i).get(j).isSelected() != originalAuthList.get(i).get(j).isSelected()) {
                    if (newAuthList.get(i).get(j).isSelected() == true) {
                        RoleAuth roleauth = new RoleAuth();
                        roleauth.setRoleid(roleid);
                        roleauth.setAuthid(newAuthList.get(i).get(j).getId());
                        roleauth.setIsdeleted(0);
                        roleauth.setCreatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                        roleauth.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                        roleauthrepository.save(roleauth);
                    } else {
                        roleauthrepository.findByRoleidAndAuthid(roleid, newAuthList.get(i).get(j).getId()).stream()
                                .forEach(roleauth -> {
                                    deleteRoleAuth(roleauth.getId());
                                });
                    }
                }
            }
        }
    }

    /**
     * 根据指定角色id查询权限列表(权限列表中角色拥有权限的selected属性为true,反之则为false)
     * 
     * @param roleid 指定角色id
     * 
     * @return 权限列表
     */
    public List<List<AuthVO>> findAuthByRoleid(Long roleid) {
        List<List<AuthVO>> sortedAuthList = new ArrayList<List<AuthVO>>();
        HashMap<Long, Long> authid = new HashMap<Long, Long>();
        List<AuthVO> authList = roleauthrepository.findAuthOrderbyResid();
        roleauthrepository.findAuthidByRoleid(roleid).forEach(auth -> {
            authid.put(auth, auth);
        });
        authList.stream().forEach(auth -> {
            if (authid.containsKey(auth.getId())) {
                auth.setSelected(true);
            }
        });
        if (authList.size() == 1) {
            sortedAuthList.add(authList);
            return sortedAuthList;
        }
        int index = 0;
        for (int i = 0; i < authList.size() - 1; i++) {
            if (authList.get(i).getResid() != authList.get(i + 1).getResid()) {
                sortedAuthList.add(authList.subList(index, i + 1));
                index = i + 1;
            }
        }
        if (authList.get(authList.size() - 1).getResid() == authList.get(authList.size() - 2).getResid()) {
            sortedAuthList.add(authList.subList(index, authList.size()));
        } else {
            sortedAuthList.add(Arrays.asList(authList.get(authList.size() - 1)));
        }
        return sortedAuthList;
    }
}
