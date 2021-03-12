package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.Resource;
import com.jd.iot.admin.repository.ResourceRepository;
import com.jd.iot.admin.vo.ResourceVO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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

//资源服务
@Service
public class ResourceService {

    @Autowired
    ResourceRepository resourcerepository;

    /**
     * 添加资源
     * 
     * @param resourcevo 需添加的资源
     * 
     * @return 成功添加的资源
     */
    public ResourceVO addResource(ResourceVO resourcevo) {
        Resource resource = new Resource(resourcevo);
        Long maxId;
        if (resource.getRestypeid() == 1L || resource.getRestypeid() == 2L) {
            maxId = resourcerepository.maxId1();
        } else {
            maxId = resourcerepository.maxId2();
        }
        if (maxId == null) {
            if (resource.getRestypeid() == 1L || resource.getRestypeid() == 2L) {
                resource.setId(1L);
            } else {
                resource.setId(1000001L);
            }
        } else {
            resource.setId(maxId + 1);
        }
        resource.setCreatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        resource.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        return new ResourceVO(resourcerepository.save(resource));
    }

    /**
     * 删除资源
     * 
     * @param id 需删除资源的id
     */
    public void deleteResource(Long id) {
        try {
            Resource resource = resourcerepository.findById(id).get();
            resource.setIsdeleted(1);
            resource.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            resourcerepository.save(resource);
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
        resource.setUpdatetime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        return new ResourceVO(resourcerepository.save(resource));
    }

    /**
     * 通过id查找指定资源
     * 
     * @param id 需查找资源的id
     * 
     * @return 资源
     */
    public ResourceVO findById(Long id) {
        try {
            return new ResourceVO(resourcerepository.findById(id).get());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }
    }

    /**
     * 查询资源列表
     * 
     * @return 资源列表
     */
    public List<ResourceVO> findAllResource() {
        List<ResourceVO> resourceList = new ArrayList<ResourceVO>();
        resourcerepository.findAllResource().stream().map(resource -> resourceList.add(new ResourceVO(resource)))
                .collect(Collectors.toList());
        return resourceList;
    }

    /**
     * 根据页号查询指定资源列表
     * 
     * @param pageNo 页号
     * 
     * @return 指定资源列表
     */
    public Page<ResourceVO> findAllResourcePaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 20);
        List<ResourceVO> lv = new ArrayList<ResourceVO>();
        resourcerepository.findAllResourcePaginated(pageable).stream().map(resource -> lv.add(new ResourceVO(resource)))
                .collect(Collectors.toList());
        return new PageImpl<ResourceVO>(lv);
    }

    /**
     * 查询总资源数量
     * 
     * @return 总资源数量
     */
    public long count() {
        return resourcerepository.count();
    }

    /**
     * 根据指定res type id查询总资源数量
     * 
     * @param restypeid 指定res type id
     * 
     * @return 总资源数量
     */
    public long countByRestypeid(Long restypeid) {
        return resourcerepository.countByRestypeid(restypeid);
    }

    /**
     * 查询总页数
     * 
     * @return 总页数
     */
    public long page() {
        if (resourcerepository.count() % 20 != 0) {
            return resourcerepository.count() / 20 + 1;
        }
        return resourcerepository.count() / 20;
    }

    /**
     * 根据指定res type id查询总页数
     * 
     * @param restypeid 指定res type id
     * 
     * @return 总页数
     */
    public long pageByRestypeid(Long restypeid) {
        if (resourcerepository.countByRestypeid(restypeid) % 20 != 0) {
            return resourcerepository.countByRestypeid(restypeid) / 20 + 1;
        }
        return resourcerepository.countByRestypeid(restypeid) / 20;
    }

    /**
     * 根据资源type id查询资源列表
     * 
     * @return 指定type id的资源列表
     */
    public List<ResourceVO> resourceMenu(Long restypeid) {
        List<ResourceVO> resourceList = new ArrayList<ResourceVO>();
        resourcerepository.findAllByRestypeid(restypeid).stream()
                .map(resource -> resourceList.add(new ResourceVO(resource))).collect(Collectors.toList());
        return resourceList;
    }

    /**
     * 根据页号和指定res type id查询资源列表
     * 
     * @param restypeid 指定res type id
     * @param pageNo    页号
     * 
     * @return 资源列表
     */
    public Page<ResourceVO> resourceMenuPaginated(Long restypeid, int pageNo) {
        List<ResourceVO> lv = new ArrayList<ResourceVO>();
        Pageable pageable = PageRequest.of(pageNo - 1, 20);
        resourcerepository.findAllByRestypeidPaginated(restypeid, pageable).stream().map(r -> lv.add(new ResourceVO(r)))
                .collect(Collectors.toList());
        return new PageImpl<ResourceVO>(lv);
    }
}
