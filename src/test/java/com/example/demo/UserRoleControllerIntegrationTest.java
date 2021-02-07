package com.example.demo;

import static org.junit.Assert.assertEquals;

import com.jd.iot.admin.IotCoreApplication;
import com.jd.iot.admin.entity.UserRole;
import com.jd.iot.admin.service.UserRoleService;
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
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotCoreApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRoleControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    UserRoleService userroleservice;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql" })
    public void emptyTest() {
    }

    // 验证是否能成功返回所有角色
    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql" })
    public void getAllRole_Test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/userrole/findalluserrole", List.class);
    }

    // 验证返回角色示例是否是预期值
    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql" })
    public void compareUserEntity_Test() {
        UserRole r = this.restTemplate.getForObject("http://localhost:" + port + "/userrole/findbyid?id={id}", UserRole.class, 8);
        assertEquals(8L, r.getId().longValue());
    }

    // 是否可以正确添加新角色并且赋值正确
    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql" })
    public void addNewUser_Test() {
        UserRole r = this.restTemplate.postForObject("http://localhost:" + port + "/userrole/adduserrole", new UserRole(), UserRole.class);
        assertEquals(r.getId().longValue(), 9L);
    }

    // 处理不存在参数
    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql" })
    public void findById_Test() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            userroleservice.findById(100L);
        });
    }

    // 处理非法参数，期待数字输入字符
    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql" })
    public void findById_Test2() {
        UserRole r = this.restTemplate.getForObject("http://localhost:" + port + "/userrole/findbyid?id={id}", UserRole.class,
                "abc");
        assertEquals(r.getId(), null);
    }
}