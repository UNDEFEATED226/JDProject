package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.Organization;
import com.jd.iot.admin.repository.OrganizationRepository;
import com.jd.iot.admin.repository.TenantRepository;
import com.jd.iot.admin.vo.OrganizationVO;
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

//组织服务
@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationrepository;

    @Autowired
    TenantRepository tenantrepository;

    /**
     * 添加组织
     * 
     * @param organizationvo 需添加的组织
     * 
     * @return 成功添加的组织
     */
    public OrganizationVO addOrganization(OrganizationVO organizationvo) {
        Organization organization = new Organization(organizationvo);
        organization.setCreatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        organization.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        return new OrganizationVO(organizationrepository.save(organization));
    }

    /**
     * 删除组织
     * 
     * @param id 需删除组织的id
     */
    public void deleteOrganization(Long id) {
        try {
            Organization organization = organizationrepository.findById(id).get();
            organization.setIsdeleted(1);
            organization.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            organizationrepository.save(organization);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " ORGANIZATION NOT FOUND");
        }
    }

    /**
     * 修改组织
     * 
     * @param id           需修改组织的id
     * @param organizationvo 修改过的组织
     * 
     * @return 成功修改的组织
     */
    public OrganizationVO editOrganization(Long id, OrganizationVO organizationvo) {
        try {
            organizationrepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " ORGANIZATION NOT FOUND");
        }
        Organization organization = new Organization(organizationvo);
        organization.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        return new OrganizationVO(organizationrepository.save(organization));
    }

    /**
     * 通过id查找指定组织
     * 
     * @param id 需查找组织的id
     * 
     * @return 指定组织实体
     */
    public OrganizationVO findById(Long id) {
        try {
            OrganizationVO o = new OrganizationVO(organizationrepository.findById(id).get());
            try {
                String tenantname = tenantrepository.getTenantname(Long.parseLong(o.getTenantid()));
                if (tenantname == null) {
                    o.setTenantname("租户不存在或已删除");
                } else {
                    o.setTenantname(tenantname);
                }
            } catch (Exception e) {
                o.setTenantname("租户不存在或已删除");
            }
            return o;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGANIZATION NOT FOUND");
        }
    }

    /**
     * 查询组织列表
     * 
     * @return 组织列表
     */
    public List<OrganizationVO> findAllOrganization() {
        return organizationrepository.findAllOrganization();
    }

    /**
     * 查询指定页号的组织列表
     * 
     * @param pageNo 页号
     * 
     * @return 指定页号的组织列表
     */
    public Page<OrganizationVO> findAllOrganizationPaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 20);
        List<OrganizationVO> lv = organizationrepository.findAllOrganizationPaginated(pageable);
        return new PageImpl<OrganizationVO>(lv);
    }

    /**
     * 查询总组织数量
     * 
     * @return 总组织数量
     */
    public long count() {
        return organizationrepository.count();
    }

    /**
     * 查询总页数
     * 
     * @return 总页数
     */
    public long page() {
        if (organizationrepository.count() % 20 != 0) {
            return organizationrepository.count() / 20 + 1;
        }
        return organizationrepository.count() / 20;
    }
}