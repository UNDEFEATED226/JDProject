package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import com.jd.iot.admin.IotCoreApplication;
import com.jd.iot.admin.entity.RoleAuth;
import com.jd.iot.admin.service.RoleAuthService;
import com.jd.iot.admin.vo.RoleAuthVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotCoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleAuthControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RoleAuthService roleauthservice;

    @Test
    @Sql({ "classpath:sql/integration-test-roleauth.sql" })
    public void emptyTest() {
    }

    @Test
    @Sql({ "classpath:sql/integration-test-roleauth.sql" })
    // 测试是否能成功获得角色权限列表
    public void getAllRoleAuth_test() {
        String res = this.restTemplate.getForObject("http://localhost:" + port + "/roleauth/findallroleauth",
                String.class);
        System.out.println("***");
        System.out.println(res);
        System.out.println("***");
    }

    @Test
    @Sql({ "classpath:sql/integration-test-roleauth.sql" })
    // 处理不存在参数
    public void findById_test() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            roleauthservice.findById(900L);
        });
    }

    @Test
    @Sql({ "classpath:sql/integration-test-roleauth.sql" })
    // 是否可以正确查找指定角色权限
    public void findById2_test() {
        RoleAuthVO roleauth = this.restTemplate.getForObject("http://localhost:" + port + "/roleauth/findbyid?id={id}",
                RoleAuthVO.class, 1);
        assertEquals(roleauth.getId().longValue(), roleauthservice.findById(1L).getId().longValue());
    }

    @Test
    @Sql({ "classpath:sql/integration-test-roleauth.sql" })
    // 是否可以正确添加新角色权限并且赋值正确
    public void addRoleAuth_Test() {
        RoleAuth r = new RoleAuth();
        r.setIsdeleted(0);
        RoleAuthVO roleauth = this.restTemplate.postForObject("http://localhost:" + port + "/roleauth/addroleauth", r,
                RoleAuthVO.class);
        assertEquals(roleauth.getId().longValue(), 3L);
    }

    @Test
    @Sql({ "classpath:sql/integration-test-roleauth.sql" })
    // 检查是否可以成功修改指定角色权限
    public void editRoleAuth_test() {
        RoleAuthVO ravo = new RoleAuthVO();
        ravo.setId(1L);
        ravo.setRoleid(68L);
        ravo.setAuthid(69L);
        RoleAuthVO roleauthvo = this.restTemplate
                .postForObject("http://localhost:" + port + "/roleauth/editroleauth/{id}", ravo, RoleAuthVO.class, 1);
        assertEquals(roleauthvo.getRoleid(), roleauthservice.findById(1L).getRoleid());
        assertEquals(roleauthvo.getAuthid(), roleauthservice.findById(1L).getAuthid());
    }

    @Test
    @Sql({ "classpath:sql/integration-test-roleauth.sql" })
    // 检查是否可以成功删除指定角色权限
    public void deleteRoleAuth_Test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/roleauth/deleteroleauth?id={id}", void.class, 1);
        assertSame(roleauthservice.findById(1L).getIsdeleted(), 1);
    }

    @Test
    @Sql({ "classpath:sql/integration-test-roleauth.sql" })
    // 删除角色权限/处理不存在参数
    public void deleteRoleAuth2_Test() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            roleauthservice.deleteRoleAuth(900L);
        });
    }

    // 检查是否可以成功分页
    @Test
    @Sql({ "classpath:sql/integration-test-roleauth.sql" })
    public void pagination_test() {
        String res = this.restTemplate.getForObject(
                "http://localhost:" + port + "/roleauth/findallroleauthpaginated?pageNo={pageNo}", String.class, 1);
        System.out.println("***");
        System.out.println(res);
        System.out.println("***");
    }

    // 检查是否可以成功查询用户总数
    @Test
    @Sql({ "classpath:sql/integration-test-roleauth.sql" })
    public void count_test() {
        Long count = this.restTemplate.getForObject("http://localhost:" + port + "/roleauth/count", long.class);
        assertEquals(count.longValue(), 2L);
    }

    // 检查是否可以成功查询总页数
    @Test
    @Sql({ "classpath:sql/integration-test-roleauth.sql" })
    public void page_test() {
        Long page = this.restTemplate.getForObject("http://localhost:" + port + "/roleauth/page", long.class);
        assertEquals(page.longValue(), 1L);
    }
}
