package com.jd.iot.admin.controller;

import com.google.gson.Gson;
import com.jd.iot.admin.service.OrganizationService;
import com.jd.iot.admin.vo.OrganizationVO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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

//组织controller
@Slf4j
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    OrganizationService organizationservice;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    Gson gson = new Gson();

    /**
     * 添加组织
     * 
     * @param organization 需添加的组织
     * 
     * @return 成功添加的组织
     */
    @PostMapping("/addorganization")
    public OrganizationVO addOrganization(@RequestBody @Validated OrganizationVO organizationvo) {
        log.info("添加组织:[{}]", gson.toJson(organizationvo));
        return organizationservice.addOrganization(organizationvo);
    }

    /**
     * 删除组织
     * 
     * @param id 需删除组织的id
     */
    @GetMapping("/deleteorganization")
    public void deleteOrganization(Long id) {
        try {
            log.info("删除组织id:[{}]", id);
            organizationservice.deleteOrganization(id);
        } catch (ResponseStatusException e) {
            log.error("删除组织id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }
    }

    /**
     * 修改组织
     * 
     * @param id             需修改组织的id
     * @param organizationvo 修改过的组织
     * @return 成功修改的组织
     */
    @PostMapping("/editorganization/{id}")
    public OrganizationVO editOrganization(@PathVariable Long id,
            @RequestBody @Validated OrganizationVO organizationvo) {
        try {
            log.info("修改组织id:[{}],组织:{}", id, gson.toJson(organizationvo));
            return organizationservice.editOrganization(id, organizationvo);
        } catch (ResponseStatusException e) {
            log.error("修改组织id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }
    }

    /**
     * 用id查找指定组织
     * 
     * @param id
     * 
     * @return 组织
     */
    @GetMapping("/findbyid")
    public OrganizationVO findById(Long id) {
        try {
            log.info("查找组织id:[{}],组织:", id, gson.toJson(organizationservice.findById(id)));
            return organizationservice.findById(id);
        } catch (Exception e) {
            log.error("查找组织id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ORGANIZATION NOT FOUND");
        }
    }

    /**
     * 查询组织列表
     * 
     * @return 组织列表
     */
    @GetMapping("/findallorganization")
    public List<OrganizationVO> findAllOrganization() {
        log.info("查找所有组织:[{}]", gson.toJson(organizationservice.findAllOrganization()));
        return organizationservice.findAllOrganization();
    }

    /**
     * 查询指定页号的组织列表
     * 
     * @param pageNo 页号
     * 
     * @return 指定页号的组织列表
     */
    @GetMapping("/findallorganizationpaginated")
    public Page<OrganizationVO> findAllOrganizationPaginated(int pageNo) {
        log.info("查询第{}页组织列表:[{}]", pageNo, gson.toJson(organizationservice.findAllOrganizationPaginated(pageNo)));
        return organizationservice.findAllOrganizationPaginated(pageNo);
    }

    /**
     * 查询总组织数量
     * 
     * @return 总组织数量
     */
    @GetMapping("/count")
    public long count() {
        log.info("查询总组织数量:[{}]", organizationservice.count());
        return organizationservice.count();
    }

    /**
     * 查询总页数
     * 
     * @return 总页数
     */
    @GetMapping("/page")
    public long page() {
        log.info("查询组织页数:[{}]", organizationservice.page());
        return organizationservice.page();
    }
}