package com.jd.iot.admin.controller;

import com.google.gson.Gson;
import com.jd.iot.admin.service.ResourceService;
import com.jd.iot.admin.vo.ResourceVO;
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

@RequestMapping("/resource")
@RestController
@Slf4j
public class ResourceController {

    @Autowired
    ResourceService resourceservice;

    public static final Logger log = LoggerFactory.getLogger(ResourceController.class);

    Gson gson = new Gson();

    /**
     * 添加资源
     * 
     * @param resourcevo 需添加的资源
     * 
     * @return 成功添加的资源
     */
    @PostMapping("/addresource")
    public ResourceVO addResource(@RequestBody @Validated ResourceVO resourcevo) {
        log.info("添加资源:[{}]", gson.toJson(resourcevo));
        return resourceservice.addResource(resourcevo);
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

    /**
     * 修改指定资源实体
     * 
     * @param id         需修改资源的id
     * @param resourcevo 修改过的资源
     * 
     * @return 成功修改的资源
     */
    @PostMapping("/editresource/{id}")
    public ResourceVO editResouce(@PathVariable Long id, @RequestBody @Validated ResourceVO resourcevo) {
        try {
            log.info("修改资源id:[{}],资源:{}", id, gson.toJson(resourcevo));
            return resourceservice.editResource(id, resourcevo);
        } catch (ResponseStatusException e) {
            log.error("修改资源id:[{}]失败", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "EDIT RESOURCE FAILURE");
        }
    }

    /**
     * 通过id查找指定资源
     * 
     * @param id 需查找资源的id
     * 
     * @return 资源
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
     * 查询资源列表
     * 
     * @return 资源列表
     */
    @GetMapping("/findallresource")
    public List<ResourceVO> findAllResource() {
        log.info("查找所有资源:{}", gson.toJson(resourceservice.findAllResource()));
        return resourceservice.findAllResource();
    }

    /**
     * 根绝页号查询指定资源列表
     * 
     * @param pageNo 页号
     * 
     * @return 指定资源列表
     */
    @GetMapping("/findallresourcepaginated")
    public Page<ResourceVO> findAllResourcePaginated(int pageNo) {
        log.info("查询第{}页资源列表:[{}]", pageNo, gson.toJson(resourceservice.findAllResourcePaginated(pageNo)));
        return resourceservice.findAllResourcePaginated(pageNo);
    }

    /**
     * 查询资源总数
     * 
     * @return 资源总数
     */
    @GetMapping("/count")
    public long count() {
        log.info("查询总资源数量:{}", resourceservice.count());
        return resourceservice.count();
    }

    /**
     * 根据res type id查询总资源数量
     * 
     * @param restypeid 指定res type id
     * 
     * @return 总资源数量
     */
    @GetMapping("/countbyrestypeid")
    public long countByRestype(Long restypeid) {
        log.info("查询res type id为{}的总资源数量:{}", restypeid, resourceservice.countByRestypeid(restypeid));
        return resourceservice.countByRestypeid(restypeid);
    }

    /**
     * 查询总页数
     * 
     * @return 总页数
     */
    @GetMapping("/page")
    public long page() {
        log.info("查询总资源页数:{}", resourceservice.page());
        return resourceservice.page();
    }

    /**
     * 根据指定res type id查询总页数
     * 
     * @param restypeid 指定res type id
     * 
     * @return 总页数
     */
    @GetMapping("/pagebyrestypeid")
    public long pageByRestypeid(Long restypeid) {
        log.info("查询res type id为{}的总资源页数:{}", restypeid, resourceservice.pageByRestypeid(restypeid));
        return resourceservice.pageByRestypeid(restypeid);
    }

    /**
     * 根据资源type id查询资源列表
     * 
     * @return 指定type id的资源列表
     */
    @GetMapping("/resourcemenu")
    public List<ResourceVO> resourceMenu(Long restypeid) {
        log.info("查找所有物管平台资源:{}", gson.toJson(resourceservice.resourceMenu(restypeid)));
        return resourceservice.resourceMenu(restypeid);
    }

    /**
     * 根据页号和指定res type id查询资源列表
     * 
     * @param restypeid 指定res type id
     * @param pageNo    页号
     * 
     * @return 资源列表
     */
    @GetMapping("/resourcemenupaginated")
    public Page<ResourceVO> resourceMenuPaginated(Long restypeid, int pageNo) {
        log.info("查找第{}页res type id为{}的资源列表:{}", pageNo, restypeid,
                gson.toJson(resourceservice.resourceMenuPaginated(restypeid, pageNo)));
        return resourceservice.resourceMenuPaginated(restypeid, pageNo);
    }
}
