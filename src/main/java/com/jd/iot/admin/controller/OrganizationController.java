package com.jd.iot.admin.controller;

import com.google.gson.Gson;
import com.jd.iot.admin.service.OrganizationService;
import com.jd.iot.admin.vo.OrganizationVO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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

//Controller for organization
@Slf4j
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    OrganizationService organizationservice;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    Gson gson = new Gson();

    /**
     * 查找所有组织实体
     * 
     * @return
     */
    @GetMapping("/findallorganization")
    public List<OrganizationVO> findAllOrganization() {
        log.info("查找所有组织:[{}]", gson.toJson(organizationservice.findAllOrganization()));
        return organizationservice.findAllOrganization();
    }

    /**
     * 用id查找指定组织实体
     * 
     * @param id
     * 
     * @return 组织实体
     */
    @GetMapping("/findbyid")
    public OrganizationVO findById(Long id) {
        try {
            log.info("查找组织id:[{}],组织:", id, gson.toJson(organizationservice.findById(id)));
            return organizationservice.findById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGANIZATION NOT FOUND");
        }
    }

    /**
     * 添加单个组织实体
     * 
     * @param organization 单个组织实体
     * 
     * @return 添加的组织实体
     */
    @PostMapping("/addorganization")
    public OrganizationVO addOrganization(@RequestBody OrganizationVO organizationvo) {
        log.info("添加组织:[{}]", gson.toJson(organizationvo));
        return organizationservice.addOrganization(organizationvo);
    }

    /**
     * 修改指定组织
     * 
     * @param id           需修改组织的id
     * @param organization 修改完的组织
     * @return 成功修改完的组织
     */
    @PostMapping("/editorganization/{id}")
    public OrganizationVO editOrganization(@PathVariable Long id, @RequestBody OrganizationVO organizationvo) {
        try {
            log.info("修改组织id:[{}],组织:{}", id, gson.toJson(organizationvo));
            return organizationservice.editOrganization(id, organizationvo);
        } catch (ResponseStatusException e) {
            log.error("修改组织id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }
    }

    /**
     * 软删组织
     * 
     * @param id 需删除组织的id
     */
    @GetMapping("/deleteorganization")
    public void deleteOrganization(Long id) {
        try {
            organizationservice.deleteOrganization(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }
    }
}