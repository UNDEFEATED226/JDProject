package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.jd.iot.admin.IotCoreApplication;
import com.jd.iot.admin.service.UserService;
import com.jd.iot.admin.vo.UserVO;
import java.util.List;
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
public class UserControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userservice;

    @Test
    @Sql({ "classpath:sql/integration-test-user.sql" })
    public void emptyTest() {
    }

    @Test
    @Sql({ "classpath:sql/integration-test-user.sql" })
    public void getAllUser_Test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/user/findalluser", List.class);
    }

    // 验证返回用户示例是否是预期值
    @Test
    @Sql({ "classpath:sql/integration-test-user.sql" })
    public void compareUserEntity_Test() {
        UserVO g = this.restTemplate.getForObject("http://localhost:" + port + "/user/findbyid?id={id}", UserVO.class,
                107);
        assertEquals(107L, g.getId().longValue());
    }

    // 是否可以正确添加新用户并且赋值正确
    @Test
    @Sql({ "classpath:sql/integration-test-user.sql", "classpath:sql/integration-test-organization.sql" })
    public void addNewUser_Test() {
        UserVO u = new UserVO();
        u.setLoginname("user");
        u.setPassword("password");
        u.setOrgid("1");
        UserVO user = this.restTemplate.postForObject("http://localhost:" + port + "/user/adduser", u, UserVO.class);
        assertEquals(user.getId().longValue(), 108L);
        assertEquals(user.getLoginname(), "user");
        assertEquals(user.getPassword(), "8eeef9c3377e87dbd9adbeac247363e5");
        assertEquals(user.getOrgid(), "1");
    }

    // 处理不存在参数
    @Test
    @Sql({ "classpath:sql/integration-test-user.sql" })
    public void findById_Test() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            userservice.findById(900L);
        });
    }

    // 处理非法参数，期待数字输入字符
    @Test
    @Sql({ "classpath:sql/integration-test-user.sql" })
    public void findById_Test2() {
        UserVO u = this.restTemplate.getForObject("http://localhost:" + port + "/user/findbyid?id={id}", UserVO.class,
                "abc");
        assertEquals(u.getId(), null);
    }

    // 检查是否可以成功修改指定用户
    @Test
    @Sql({ "classpath:sql/integration-test-user.sql", "classpath:sql/integration-test-organization.sql" })
    public void editUser_Test() {
        UserVO u = userservice.findById(107L);
        u.setLoginname("gbaj");
        u.setPassword("gbaj1234");
        u.setOrgid("2");
        UserVO user = this.restTemplate.postForObject("http://localhost:" + port + "/user/edituser/{id}", u,
                UserVO.class, 107);
        assertEquals(user.getTenantid().longValue(), 334L);
        assertEquals(user.getPassword(), "5069bb29f369b7fdd96737f029c2d659");
    }

    // 检查是否可以成功删除指定用户
    @Test
    @Sql({ "classpath:sql/integration-test-user.sql" })
    public void deleteUser_test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/user/deleteuser?id={id}", void.class, 107);
        assertSame(userservice.findById(107L).getIsdeleted(), 1);
    }

    // 检查是否可以成功分页
    @Test
    @Sql({ "classpath:sql/integration-test-user.sql" })
    public void pagination_test() {
        String res = this.restTemplate.getForObject(
                "http://localhost:" + port + "/user/findalluserpaginated?pageNo={pageNo}", String.class, 1);
        System.out.println("***");
        System.out.println(res);
        System.out.println("***");
    }

    // 检查是否可以成功查询用户总数
    @Test
    @Sql({ "classpath:sql/integration-test-user.sql" })
    public void count_test() {
        Long count = this.restTemplate.getForObject("http://localhost:"+port+"/user/count", long.class);
        assertEquals(count.longValue(), 1L);
    }

    // 检查是否可以成功查询总页数
    @Test
    @Sql({ "classpath:sql/integration-test-user.sql" })
    public void page_test() {
        Long page = this.restTemplate.getForObject("http://localhost:"+port+"/user/page", long.class);
        assertEquals(page.longValue(), 1L);
    }
}