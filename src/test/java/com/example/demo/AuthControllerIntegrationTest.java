package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.jd.iot.admin.IotCoreApplication;
import com.jd.iot.admin.service.AuthService;
import com.jd.iot.admin.vo.AuthVO;
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
public class AuthControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AuthService authservice;

    @Test
    @Sql({ "classpath:sql/integration-test-auth.sql" })
    public void emptyTest() {
    }

    @Test
    @Sql({ "classpath:sql/integration-test-auth.sql" })
    // 测试是否能成功获得权限列表
    public void getAllAuth_test() {
        String res = this.restTemplate.getForObject("http://localhost:" + port + "/auth/findallauth", String.class);
        System.out.println("***");
        System.out.println(res);
        System.out.println("***");
    }

    @Test
    @Sql({ "classpath:sql/integration-test-auth.sql" })
    // 处理不存在参数
    public void findById_test() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            authservice.findById(900L);
        });
    }

    @Test
    @Sql({ "classpath:sql/integration-test-auth.sql" })
    // 是否可以正确查找指定权限
    public void findById2_test() {
        AuthVO auth = this.restTemplate.getForObject("http://localhost:" + port + "/auth/findbyid?id={id}",
                AuthVO.class, 1);
        assertEquals(auth.getDescription(), authservice.findById(1L).getDescription());
        assertEquals(auth.getAuthname(), authservice.findById(1L).getAuthname());
    }

    @Test
    @Sql({ "classpath:sql/integration-test-auth.sql" })
    // 是否可以正确添加新权限并且赋值正确
    public void addNewRole_Test() {
        AuthVO a = new AuthVO();
        AuthVO auth = this.restTemplate.postForObject("http://localhost:" + port + "/auth/addauth", a, AuthVO.class);
        assertEquals(auth.getId().longValue(), 3L);
    }

    @Test
    @Sql({ "classpath:sql/integration-test-auth.sql" })
    // 检查是否可以成功修改指定权限
    public void editAuth_test() {
        AuthVO avo = new AuthVO();
        avo.setId(1L);
        avo.setAuthname("修改_permission");
        avo.setDescription("修改描述完毕");
        AuthVO authvo = this.restTemplate.postForObject("http://localhost:" + port + "/auth/editauth/{id}", avo,
                AuthVO.class, 1);
        assertEquals(authvo.getDescription(), authservice.findById(1L).getDescription());
        assertEquals(authvo.getAuthname(), authservice.findById(1L).getAuthname());
    }

    @Test
    @Sql({ "classpath:sql/integration-test-auth.sql" })
    // 检查是否可以成功删除指定权限
    public void deleteRole_Test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/auth/deleteauth?id={id}", void.class, 1);
        assertSame(authservice.findById(1L).getIsdeleted(), 1);
    }

    @Test
    @Sql({ "classpath:sql/integration-test-auth.sql" })
    // 删除权限/处理不存在参数
    public void deleteAuth2_Test() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            authservice.deleteAuth(900L);
        });
    }
    
    // 检查是否可以成功分页
    @Test
    @Sql({ "classpath:sql/integration-test-auth.sql" })
    public void pagination_test() {
        String res = this.restTemplate.getForObject(
                "http://localhost:" + port + "/auth/findallauthpaginated?pageNo={pageNo}", String.class,
                1);
        System.out.println("***");
        System.out.println(res);
        System.out.println("***");
    }

    // 检查是否可以成功查询组织总数
    @Test
    @Sql({ "classpath:sql/integration-test-auth.sql" })
    public void count_test() {
        Long count = this.restTemplate.getForObject("http://localhost:" + port + "/auth/count", long.class);
        assertEquals(count.longValue(), 2L);
    }

    // 检查是否可以成功查询总页数
    @Test
    @Sql({ "classpath:sql/integration-test-auth.sql" })
    public void page_test() {
        Long page = this.restTemplate.getForObject("http://localhost:" + port + "/auth/page", long.class);
        assertEquals(page.longValue(), 1L);
    }
}
