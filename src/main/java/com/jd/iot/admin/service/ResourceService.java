package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.Resource;
import com.jd.iot.admin.repository.ResourceRepository;
import com.jd.iot.admin.vo.ResourceVO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//Service for 资源实体
@Service
public class ResourceService {

    @Autowired
    ResourceRepository resourcerepository;

    public static final Logger log = LoggerFactory.getLogger(ResourceService.class);

    /**
     * 查询资源列表
     * 
     * @return 资源列表
     */
    public List<ResourceVO> findAllResource() {
        List<Resource> l = new ArrayList<Resource>();
        List<ResourceVO> lv = new ArrayList<ResourceVO>();
        resourcerepository.findAll().forEach(l::add);
        l.stream().filter(r -> r.getIsdeleted() != 1).map(s -> lv.add(new ResourceVO(s))).collect(Collectors.toList());
        return lv;
    }

    /**
     * 根据资源type id查找不同平台的所有资源
     * 
     * @return 指定平台的所有资源
     */
    public List<ResourceVO> resourceMenu(Long resourcetypeid) {
        List<Resource> l = resourcerepository.findAllByRestypeid(resourcetypeid);
        List<ResourceVO> lv = new ArrayList<ResourceVO>();
        l.stream().filter(r -> r.getIsdeleted() != 1).map(r -> lv.add(new ResourceVO(r))).collect(Collectors.toList());
        return lv;

    }

    /**
     * 添加资源
     * 
     * @param resourcevo 需添加的资源
     * 
     * @return 成功添加的资源
     */
    public ResourceVO addResource(ResourceVO resourcevo) {
        Resource resource = new Resource(resourcevo);
        Long max;
        if (resource.getRestypeid() == 1L || resource.getRestypeid() == 2L) {
            max = resourcerepository.maxId1();
        } else {
            max = resourcerepository.maxId2();
        }
        if (max == null) {
            if (resource.getRestypeid() == 1L || resource.getRestypeid() == 2L) {
                resource.setId(1L);
            } else {
                resource.setId(1000001L);
            }
        } else {
            resource.setId(max + 1);
        }
        resource.setCreatetime(new Timestamp(System.currentTimeMillis()));
        resource.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new ResourceVO(resourcerepository.save(resource));
    }

    /**
     * 通过id查找指定资源
     * 
     * @param id 需查找资源的id
     * 
     * @return 资源VO
     */
    public ResourceVO findById(Long id) {
        try {
            return new ResourceVO(resourcerepository.findById(id).get());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }
    }

    /**
     * 删除资源
     * 
     * @param id 需删除资源的id
     */
    public void deleteResource(Long id) {
        try {
            Resource r = resourcerepository.findById(id).get();
            r.setIsdeleted(1);
            r.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            resourcerepository.save(r);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DELETE RESOURCE FAILURE");
        }
    }

    /**
     * 修改资源
     * 
     * @param id         需修改资源的id
     * @param resourcevo 修改过的资源
     * 
     * @return 成功修改的资源
     */
    public ResourceVO editResource(Long id, ResourceVO resourcevo) {
        try {
            resourcerepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " ORGANIZATION NOT FOUND");
        }
        Resource resource = new Resource(resourcevo);
        resource.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new ResourceVO(resourcerepository.save(resource));
    }
}
