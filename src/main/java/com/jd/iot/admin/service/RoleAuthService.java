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
import java.util.List;
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
        RoleAuth r = new RoleAuth(roleauthvo);
        r.setCreatetime(new Timestamp(System.currentTimeMillis()));
        r.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new RoleAuthVO(roleauthrepository.save(r));
    }

    /**
     * 删除角色权限
     * 
     * @param id 需删除角色权限的id
     */
    public void deleteRoleAuth(Long id) {
        try {
            RoleAuth r = roleauthrepository.findById(id).get();
            r.setIsdeleted(1);
            r.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            roleauthrepository.save(r);
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
        RoleAuth r = new RoleAuth(roleauthvo);
        r.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new RoleAuthVO(roleauthrepository.save(r));
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
     * 根据指定role id查询角色权限列表
     * 
     * @param roleid 指定role id
     * 
     * @return 角色权限列表
     */
    public List<RoleAuthVO> findByRoleid(Long roleid) {
        List<RoleAuthVO> lv = new ArrayList<RoleAuthVO>();
        roleauthrepository.findByRoleid(roleid).stream().forEach(r -> {
            RoleAuthVO rv = new RoleAuthVO(r);
            try {
                String str = rolerepository.getRolename(rv.getRoleid());
                if (str == null) {
                    rv.setRolename("角色不存在或已删除");
                } else {
                    rv.setRolename(str);
                }
            } catch (Exception e) {
                rv.setRolename("角色不存在或已删除");
            }
            try {
                String str = resourcerepository.getResname(authrepository.findById(rv.getAuthid()).get().getResid());
                if (str == null) {
                    rv.setResname("资源不存在或已删除");
                } else {
                    rv.setResname(str);
                }
            } catch (Exception e) {
                rv.setResname("资源不存在或已删除");
            }
            try {
                String str = authrepository.getAuthname(rv.getAuthid());
                if (str == null) {
                    rv.setAuthname("权限不存在或已删除");
                } else {
                    rv.setAuthname(str);
                }
            } catch (Exception e) {
                rv.setAuthname("权限不存在或已删除");
            }
            lv.add(rv);
        });
        return lv;
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
    public void changeAuth(Long roleid, List<List<AuthVO>> l) {
        List<List<AuthVO>> original = findAuthByRoleid(roleid);
        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < l.get(i).size(); j++) {
                if (l.get(i).get(j).isSelected() != original.get(i).get(j).isSelected()) {
                    if (l.get(i).get(j).isSelected() == true) {
                        RoleAuth r = new RoleAuth();
                        r.setRoleid(roleid);
                        r.setAuthid(l.get(i).get(j).getId());
                        r.setIsdeleted(0);
                        r.setCreatetime(new Timestamp(System.currentTimeMillis()));
                        r.setUpdatetime(new Timestamp(System.currentTimeMillis()));
                        roleauthrepository.save(r);
                    } else {
                        roleauthrepository.findByRoleidAndAuthid(roleid, l.get(i).get(j).getId()).stream()
                                .forEach(r -> {
                                    deleteRoleAuth(r.getId());
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
        List<List<AuthVO>> rRes = new ArrayList<List<AuthVO>>();
        List<Long> authid = roleauthrepository.findAuthidByRoleid(roleid);
        List<AuthVO> res = roleauthrepository.findAuthOrderbyResid();
        res.stream().forEach(a -> {
            if (authid.contains(a.getId())) {
                a.setSelected(true);
            }
        });
        if (res.size() == 1) {
            rRes.add(res);
            return rRes;
        }
        int index = 0;
        for (int i = 0; i < res.size() - 1; i++) {
            if (res.get(i).getResid() != res.get(i + 1).getResid()) {
                rRes.add(res.subList(index, i + 1));
                index = i + 1;
            }
        }
        if (res.get(res.size() - 1).getResid() == res.get(res.size() - 2).getResid()) {
            rRes.add(res.subList(index, res.size()));
        } else {
            rRes.add(Arrays.asList(res.get(res.size() - 1)));
        }
        return rRes;
    }
}
