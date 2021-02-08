package com.jd.iot.admin.controller;

import com.jd.iot.admin.service.ResourceService;
import com.jd.iot.admin.vo.ResourceVO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
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

@RequestMapping("/resource")
@RestController
@Slf4j
public class ResourceController {

    @Autowired
    ResourceService resourceservice;

    public static final Logger log = LoggerFactory.getLogger(ResourceController.class);

    Gson gson = new Gson();

    /**
     * 通过id查找指定资源
     * 
     * @param id 需查找资源的id
     * 
     * @return 资源VO
     */
    @GetMapping("/findbyid")
    public ResourceVO findById(Long id) {
        try {
            log.info("查找资源id:[{}]", id);
            return resourceservice.findById(id);
        } catch (ResponseStatusException e) {
            log.error("查找资源id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "RESOURCE NOT FOUND");
        }
    }

    /**
     * 添加资源
     * 
     * @param resourcevo 需添加的资源
     * 
     * @return 成功添加的资源
     */
    @PostMapping("/addresource")
    public ResourceVO addResource(@RequestBody ResourceVO resourcevo) {
        log.info("添加资源:[{}]", gson.toJson(resourcevo));
        return resourceservice.addResource(resourcevo);
    }

    /**
     * 查找所有资源实体
     * 
     * @return 所有资源VO
     */
    @GetMapping("/findallresource")
    public List<ResourceVO> findAllResource() {
        log.info("查找所有资源:{}", gson.toJson(resourceservice.findAllResource()));
        return resourceservice.findAllResource();
    }

    /**
     * 根据资源type id查找不同平台的所有资源
     * 
     * @return 指定平台的所有资源
     */
    @GetMapping("/resourcemenu")
    public List<ResourceVO> resourceMenu(Long resourcetypeid) {
        log.info("查找所有物管平台资源:{}", gson.toJson(resourceservice.resourceMenu(resourcetypeid)));
        return resourceservice.resourceMenu(resourcetypeid);
    }

    /**
     * 修改指定资源实体
     * 
     * @param id         需修改资源的id
     * 
     * @param resourcevo 修改完的资源VO
     * 
     * @return 成功修改的资源VO
     */
    @PostMapping("/editresource/{id}")
    public ResourceVO editResouce(@PathVariable Long id, @RequestBody ResourceVO resourcevo) {
        try {
            log.info("修改资源id:[{}],资源:{}", id, gson.toJson(resourcevo));
            return resourceservice.editResource(id, resourcevo);
        } catch (ResponseStatusException e) {
            log.error("修改资源id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "EDIT RESOURCE FAILURE");
        }
    }

    /**
     * 根据id删除指定资源
     * 
     * @param id 需删除资源的id
     */
    @GetMapping("/deleteresource")
    public void deleteResource(Long id) {
        try {
            log.info("删除资源id:[{}]", id);
            resourceservice.deleteResource(id);
        } catch (ResponseStatusException e) {
            log.error("删除资源id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DELETE RESOURCE FAILURE");
        }
    }
}
