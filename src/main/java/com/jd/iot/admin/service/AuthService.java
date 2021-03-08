package com.jd.iot.admin.service;

import com.jd.iot.admin.entity.Auth;
import com.jd.iot.admin.repository.AuthRepository;
import com.jd.iot.admin.repository.ResourceRepository;
import com.jd.iot.admin.repository.RoleAuthRepository;
import com.jd.iot.admin.vo.AuthVO;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//Service for 权限
@Service
public class AuthService {

    @Autowired
    AuthRepository authrepository;

    @Autowired
    ResourceRepository resourcerepository;

    @Autowired
    RoleAuthRepository roleauthrepository;

    /**
     * 添加权限
     * 
     * @param authvo 需添加的权限
     * 
     * @return 成功添加的权限
     */
    public AuthVO addAuth(AuthVO authvo) {
        Auth auth = new Auth(authvo);
        auth.setCreatetime(new Timestamp(System.currentTimeMillis()));
        auth.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new AuthVO(authrepository.save(auth));
    }

    /**
     * 删除权限
     * 
     * @param id 需删除权限的id
     */
    public void deleteAuth(Long id) {
        try {
            Auth auth = authrepository.findById(id).get();
            auth.setIsdeleted(1);
            auth.setUpdatetime(new Timestamp(System.currentTimeMillis()));
            authrepository.save(auth);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AUTH NOT FOUND");
        }
    }

    /**
     * 修改权限
     * 
     * @param id     需修改权限的id
     * @param authvo 修改过的权限
     * 
     * @return 成功修改的权限
     */
    public AuthVO editAuthVO(Long id, AuthVO authvo) {
        try {
            authrepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AUTH NOT FOUND");
        }
        Auth auth = new Auth(authvo);
        auth.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        return new AuthVO(authrepository.save(auth));
    }

    /**
     * 通过id查找指定权限
     * 
     * @param id 需查找权限的id
     * 
     * @return 指定权限
     */
    public AuthVO findById(Long id) {
        try {
            AuthVO av = new AuthVO(authrepository.findById(id).get());
            try {
                String r = resourcerepository.getResname(av.getResid());
                if (r == null) {
                    av.setResname("资源不存在或已删除");
                } else {
                    av.setResname(r);
                }
            } catch (Exception e) {
                av.setResname("资源不存在或已删除");
            }
            return av;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AUTH NOT FOUND");
        }
    }

    /**
     * 查询权限列表
     * 
     * @return 权限列表
     */
    public List<AuthVO> findAllAuth() {
        return authrepository.findAllAuth();
    }

    /**
     * 查询权限列表并以res id排序
     * 
     * @return 以res id排序的权限列表
     */
    public List<AuthVO> findAllAuthOrderbyResid() {
        return authrepository.findAllAuth();
    }

    /**
     * 根据页号查询指定权限列表
     *
     * @param pageNo 页号
     * 
     * @return 指定权限列表
     */
    public Page<AuthVO> findAllAuthPaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 20);
        return new PageImpl<AuthVO>(authrepository.findAllAuthPaginated(pageable));
    }

    /**
     * 查询总权限刷量
     * 
     * @return 总权限数量
     */
    public long count() {
        return authrepository.count();
    }

    /**
     * 查询总页数
     * 
     * @return 总页数
     */
    public long page() {
        if (authrepository.count() % 20 != 0) {
            return authrepository.count() / 20 + 1;
        }
        return authrepository.count() / 20;
    }
}
