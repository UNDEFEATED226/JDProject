package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.jd.iot.admin.IotCoreApplication;
import com.jd.iot.admin.service.UserRoleService;
import com.jd.iot.admin.vo.UserRoleVO;
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
        String res = this.restTemplate.getForObject("http://localhost:" + port + "/userrole/findalluserrole",
                String.class);
        System.out.println("***");
        System.out.println(res);
        System.out.println("***");
    }

    // 验证返回角色示例是否是预期值
    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql" })
    public void compareUserEntity_Test() {
        UserRoleVO r = this.restTemplate.getForObject("http://localhost:" + port + "/userrole/findbyid?id={id}",
                UserRoleVO.class, 8);
        assertEquals(8L, r.getId().longValue());
        assertEquals(107L, r.getUserid().longValue());
    }

    // 是否可以正确添加新角色并且赋值正确
    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql" })
    public void addNewUser_Test() {
        UserRoleVO userrolevo = new UserRoleVO();
        userrolevo.setUserid(169L);
        userrolevo.setRoleid(3L);
        UserRoleVO u = this.restTemplate.postForObject("http://localhost:" + port + "/userrole/adduserrole", userrolevo,
                UserRoleVO.class);
        assertEquals(u.getId().longValue(), 10L);
    }

    // 是否能够成功查找指定用户角色
    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql" })
    public void findById_Test() {
        UserRoleVO u = this.restTemplate.getForObject("http://localhost:" + port + "/userrole/findbyid?id={id}",
                UserRoleVO.class, 8L);
        assertEquals(107L, u.getUserid().longValue());
    }

    // 处理不存在参数
    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql" })
    public void findById_Test1() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            userroleservice.findById(100L);
        });
    }

    // 处理非法参数，期待数字输入字符
    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql" })
    public void findById_Test2() {
        UserRoleVO r = this.restTemplate.getForObject("http://localhost:" + port + "/userrole/findbyid?id={id}",
                UserRoleVO.class, "abc");
        assertEquals(r.getId(), null);
    }

    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql" })
    public void deleteUserRole_test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/userrole/deleteuserrole?id={id}", void.class, 8L);
        UserRoleVO r = this.restTemplate.getForObject("http://localhost:" + port + "/userrole/findbyid?id={id}",
                UserRoleVO.class, 8L);
        assertSame(r.getIsdeleted(), 1);
    }

    // 检查是否可以成功分页
    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql" })
    public void pagination_test() {
        String res = this.restTemplate.getForObject(
                "http://localhost:" + port + "/userrole/findalluserrolepaginated?pageNo={pageNo}", String.class, 1);
        System.out.println("***");
        System.out.println(res);
        System.out.println("***");
    }

    // 检查是否可以成功查询用户角色总数
    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql", "classpath:sql/integration-test-user.sql",
            "classpath:sql/integration-test-role.sql" })
    public void count_test() {
        Long count = this.restTemplate.getForObject("http://localhost:" + port + "/userrole/count", long.class);
        assertEquals(count.longValue(), 2L);
    }

    // 检查是否可以成功查询总页数
    @Test
    @Sql({ "classpath:sql/integration-test-userrole.sql" })
    public void page_test() {
        Long page = this.restTemplate.getForObject("http://localhost:" + port + "/userrole/page", long.class);
        assertEquals(page.longValue(), 1L);
    }
}