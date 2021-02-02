package com.example.demo;

import static org.junit.Assert.assertEquals;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.google.gson.Gson;
import java.math.BigInteger;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotCoreApplication.class, 
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {
    @LocalServerPort
    private int port;

    Gson gson = new Gson();

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

    @Test
    @Sql({ "classpath:sql/integration-test-user.sql" })
    // 验证返回用户示例是否是预期值
    public void compareUserEntity_Test() {
        User g = this.restTemplate.getForObject("http://localhost:" + port + "/user/findbyid?id={id}", User.class, 107);
        assertEquals(107L, g.getId().longValue());
    }

    @Test
    @Sql({ "classpath:sql/integration-test-user.sql", 
        "classpath:sql/integration-test-organization.sql" })
    // 是否可以正确添加新用户并且赋值正确
    public void addNewUser_Test() {
        User u = new User();
        u.setLoginname("user");
        u.setPassword("password");
        u.setOrgid("1");
        User user = this.restTemplate.postForObject("http://localhost:" + port + "/user/adduser", u, User.class);
        assertEquals(user.getId().longValue(), 108L);
        assertEquals(user.getLoginname(), "user");
        assertEquals(user.getPassword(), "8eeef9c3377e87dbd9adbeac247363e5");
        assertEquals(user.getOrgid(), "1");
    }

    @Test
    @Sql({ "classpath:sql/integration-test-user.sql" })
    // 处理不存在参数
    public void findById_Test() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            userservice.findById(900L);
        });
    }

    @Test
    @Sql({ "classpath:sql/integration-test-user.sql" })
    // 处理非法参数，期待数字输入字符
    public void findById_Test2() {
        User u = this.restTemplate.getForObject("http://localhost:" + port + "/user/findbyid?id={id}", User.class,
                "abc");
        assertEquals(u.getId(), null);
    }

    @Test
    @Sql({ "classpath:sql/integration-test-user.sql",
        "classpath:sql/integration-test-organization.sql" })
    // 检查是否可以成功修改指定用户
    public void editUser_Test() {
        User u = userservice.findById(107L);
        u.setLoginname("gbaj");
        u.setPassword("gbaj1234");
        u.setOrgid("2");
        User user = this.restTemplate.postForObject("http://localhost:" + port + "/user/edituser/{id}", u, User.class,
                107);
        System.out.println(user.getPassword());
        System.out.println(user.getTenantid());
        assertEquals(user.getTenantid(), BigInteger.valueOf(334L));
        assertEquals(user.getPassword(), "5069bb29f369b7fdd96737f029c2d659");
    }
}