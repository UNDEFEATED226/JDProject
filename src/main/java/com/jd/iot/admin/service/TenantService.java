package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.Tenant;
import com.jd.iot.admin.repository.TenantRepository;
import com.jd.iot.admin.repository.UserRepository;
import com.jd.iot.admin.vo.TenantVO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//Service for 租户
@Service
public class TenantService {

    @Autowired
    TenantRepository tenantrepository;

    @Autowired
    UserRepository userrepository;

    /**
     * 添加租户
     * 
     * @param tenantvo 需添加的租户
     * 
     * @return 成功添加的租户
     */
    public TenantVO addTenant(TenantVO tenantvo) {
        Tenant tenant = new Tenant(tenantvo);
        tenant.setCreatetime(new Timestamp(System.currentTimeMillis()));
        tenant.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new TenantVO(tenantrepository.save(tenant));
    }

    /**
     * 删除租户
     * 
     * @param id 需删除租户的id
     */
    public void deleteTenant(Long id) {
        try {
            Tenant tenant = tenantrepository.findById(id).get();
            tenant.setIsdeleted(1);
            tenant.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            tenantrepository.save(tenant);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TENANT NOT FOUND");
        }
    }

    /**
     * 修改租户
     * 
     * @param id       需修改租户的id
     * @param tenantvo 修改过的租户
     * 
     * @return 成功修改的租户
     */
    public TenantVO editTenant(Long id, TenantVO tenantvo) {
        try {
            tenantrepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TENANT NOT FOUND");
        }
        Tenant tenant = new Tenant(tenantvo);
        tenant.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new TenantVO(tenantrepository.save(tenant));
    }

    /**
     * 通过id查找指定租户
     * 
     * @param id 需查找租户的id
     * 
     * @return 指定租户
     */
    public TenantVO findById(Long id) {
        try {
            TenantVO t = new TenantVO(tenantrepository.findById(id).get());
            try {
                String r = userrepository.getUsername(t.getAdminuserid());
                if (r == null) {
                    t.setAdminname("租户管理员用户不存在或已删除");
                } else {
                    t.setAdminname(r);
                }
            } catch (Exception e) {
                t.setAdminname("租户管理员用户不存在或已删除");
            }
            return t;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TENANT NOT FOUND");
        }
    }

    /**
     * 查询租户列表
     * 
     * @return 租户列表
     */
    public List<TenantVO> findAllTenant() {
        List<TenantVO> lv = new ArrayList<TenantVO>();
        tenantrepository.findAllTenant().stream().forEach(t -> {
            TenantVO tv = new TenantVO(t);
            try {
                String r = userrepository.getUsername(t.getAdminuserid());
                if (r == null) {
                    tv.setAdminname("租户管理员用户不存在或已删除");
                } else {
                    tv.setAdminname(r);
                }
            } catch (Exception e) {
                tv.setAdminname("租户管理员用户不存在或已删除");
            }
            lv.add(tv);
        });
        return lv;
    }

    /**
     * 根据页号查询指定租户列表
     * 
     * @param pageNo 页号
     * 
     * @return 指定租户列表
     */
    public Page<TenantVO> findAllTenantPaginated(int pageNo) {
        List<TenantVO> lv = new ArrayList<TenantVO>();
        Pageable pageable = PageRequest.of(pageNo - 1, 20);
        tenantrepository.findAllTenantPaginated(pageable).stream().forEach(t -> {
            TenantVO tv = new TenantVO(t);
            try {
                String r = userrepository.getUsername(t.getAdminuserid());
                if (r == null) {
                    tv.setAdminname("租户管理员用户不存在或已删除");
                } else {
                    tv.setAdminname(r);
                }
            } catch (Exception e) {
                tv.setAdminname("租户管理员用户不存在或已删除");
            }
            lv.add(tv);
        });
        return new PageImpl<TenantVO>(lv);
    }

    /**
     * 查询总租户数量
     * 
     * @return 总租户数量
     */
    public long count() {
        return tenantrepository.count();
    }

    /**
     * 查询总页数
     * 
     * @return 总页数
     */
    public long page() {
        if (tenantrepository.count() % 20 != 0) {
            return tenantrepository.count() / 20 + 1;
        }
            return tenantrepository.count() / 20;
    }
}
