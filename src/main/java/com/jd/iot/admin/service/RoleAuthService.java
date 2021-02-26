package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.RoleAuth;
import com.jd.iot.admin.repository.AuthRepository;
import com.jd.iot.admin.repository.RoleAuthRepository;
import com.jd.iot.admin.repository.RoleRepository;
import com.jd.iot.admin.vo.AuthVO;
import com.jd.iot.admin.vo.RoleAuthVO;
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

//Service for 角色权限
@Service
public class RoleAuthService {

    @Autowired
    RoleAuthRepository roleauthrepository;

    @Autowired
    RoleRepository rolerepository;

    @Autowired
    AuthRepository authrepository;

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
     * 通过id查找指定角色权限
     * 
     * @param id 需查找角色权限的id
     * 
     * @return 指定角色权限
     */
    public RoleAuthVO findById(Long id) {
        try {
            return new RoleAuthVO(roleauthrepository.findById(id).get());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ROLEAUTH NOT FOUND");
        }
    }

    /**
     * 查询角色权限列表
     * 
     * @return 角色权限列表
     */
    public List<RoleAuthVO> findAllRoleAuth() {
        List<RoleAuthVO> lv = new ArrayList<RoleAuthVO>();
        roleauthrepository.findAllRoleAuth().stream().forEach(r -> {
            RoleAuthVO rv = new RoleAuthVO(r);
            try {
                rv.setRolename(rolerepository.getRolename(rv.getRoleid()));
            } catch (Exception e) {
                rv.setRolename("角色不存在或已删除");
            }
            try {
                rv.setAuthname(authrepository.getAuthname(rv.getAuthid()));
            } catch (Exception e) {
                rv.setAuthname("权限不存在或已删除");
            }
            lv.add(rv);
        });
        return lv;
    }

    /**
     * 根据页号查询指定角色权限列表
     * 
     * @param pageNo 页号
     * 
     * @return 指定角色权限列表
     */
    public Page<RoleAuthVO> findAllRoleAuthPaginated(int pageNo) {
        List<RoleAuthVO> lv = new ArrayList<RoleAuthVO>();
        Pageable pageable = PageRequest.of(pageNo - 1, 20);
        roleauthrepository.findAllRoleAuthPaginated(pageable).stream().forEach(r -> {
            RoleAuthVO rv = new RoleAuthVO(r);
            try {
                rv.setRolename(rolerepository.getRolename(rv.getRoleid()));
            } catch (Exception e) {
                rv.setRolename("角色不存在或已删除");
            }
            try {
                rv.setAuthname(authrepository.getAuthname(rv.getAuthid()));
            } catch (Exception e) {
                rv.setAuthname("权限不存在或已删除");
            }
            lv.add(rv);
        });
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
                rv.setRolename(rolerepository.getRolename(rv.getRoleid()));
            } catch (Exception e) {
                rv.setRolename("角色不存在或已删除");
            }
            try {
                rv.setAuthname(authrepository.getAuthname(rv.getAuthid()));
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
        } else {
            return roleauthrepository.count() / 20;
        }
    }

}
