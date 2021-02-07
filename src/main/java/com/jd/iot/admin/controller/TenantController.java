package com.jd.iot.admin.controller;

import com.jd.iot.admin.service.TenantService;
import com.jd.iot.admin.vo.TenantVO;
import com.google.gson.Gson;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
     * 添加租户
     * 
     * @param tenantvo 需添加的租户
     * 
     * @return 成功添加的租户
     */
    @PostMapping("/addtenant")
    public TenantVO addTenant(@RequestBody TenantVO tenantvo) {
        log.info("添加租户:[{}]", gson.toJson(tenantvo));
        return tenantservice.addTenant(tenantvo);
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
    public TenantVO editTenant(@PathVariable Long id, @RequestBody TenantVO tenantvo) {
        try {
            log.info("修改租户id:[{}],租户:{}", id, gson.toJson(tenantvo));
            return tenantservice.editTenant(id, tenantvo);
        } catch (ResponseStatusException e) {
            log.error("修改租户id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TENANT NOT FOUND");
        }
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
}
