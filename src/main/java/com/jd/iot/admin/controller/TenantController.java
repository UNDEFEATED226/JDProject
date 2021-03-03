package com.jd.iot.admin.controller;

import com.jd.iot.admin.service.TenantService;
import com.jd.iot.admin.vo.TenantVO;
import com.google.gson.Gson;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    TenantService tenantservice;

    public static final Logger log = LoggerFactory.getLogger(TenantController.class);

    Gson gson = new Gson();
    
    /**
     * 添加租户
     * 
     * @param tenantvo 需添加的租户
     * 
     * @return 成功添加的租户
     */
    @PostMapping("/addtenant")
    public TenantVO addTenant(@RequestBody @Validated TenantVO tenantvo) {
        log.info("添加租户:[{}]", gson.toJson(tenantvo));
        return tenantservice.addTenant(tenantvo);
    }

    /**
     * 删除租户
     * 
     * @param id 需删除租户的id
     */
    @GetMapping("/deletetenant")
    public void deleteTenant(Long id) {
        try {
            log.info("删除租户id:[{}]", id);
            tenantservice.deleteTenant(id);
        } catch (ResponseStatusException e) {
            log.error("删除租户id:[{}]失败", id);
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
    @PostMapping("/edittenant/{id}")
    public TenantVO editTenant(@PathVariable Long id, @RequestBody @Validated TenantVO tenantvo) {
        try {
            log.info("修改租户id:[{}],租户:{}", id, gson.toJson(tenantvo));
            return tenantservice.editTenant(id, tenantvo);
        } catch (ResponseStatusException e) {
            log.error("修改租户id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TENANT NOT FOUND");
        }
    }

    /**
     * 通过id查找指定租户
     * 
     * @param id 需查找租户的id
     * 
     * @return 指定租户
     */
    @GetMapping("/findbyid")
    public TenantVO findById(Long id) {
        try {
            log.info("查找租户id:[{}],租户:{}", id, gson.toJson(tenantservice.findById(id)));
            return tenantservice.findById(id);
        } catch (ResponseStatusException e) {
            log.error("查找租户id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TENANT NOT FOUND");
        }
    }

    /**
     * 查询租户列表
     * 
     * @return 租户列表
     */
    @GetMapping("/findalltenant")
    public List<TenantVO> findAllTenant() {
        log.info("查询租户列表:[{}]", gson.toJson(tenantservice.findAllTenant()));
        return tenantservice.findAllTenant();
    }

    /**
     * 根据页号查询指定租户列表
     * 
     * @param pageNo 页号
     * 
     * @return 指定租户列表
     */
    @GetMapping("/findalltenantpaginated")
    public Page<TenantVO> findAllTenantPaginated(int pageNo) {
        log.info("查询第{}页租户列表:[{}]", pageNo, gson.toJson(tenantservice.findAllTenantPaginated(pageNo)));
        return tenantservice.findAllTenantPaginated(pageNo);
    }

    /**
     * 查询总租户数量
     * 
     * @return 总租户数量
     */
    @GetMapping("/count")
    public long count() {
        log.info("查询总租户数量:{}",gson.toJson(tenantservice.count()));
        return tenantservice.count();
    }
    
    /**
     * 查询总租户页数
     * 
     * @return 总租户页数
     */
    @GetMapping("/page")
    public long page() {
        log.info("查询总租户页数:{}",gson.toJson(tenantservice.page()));
        return tenantservice.page();
    }
}
