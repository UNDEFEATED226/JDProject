package com.example.demo;

import static org.junit.Assert.assertSame;

import com.jd.iot.admin.IotCoreApplication;
import com.jd.iot.admin.service.RoleService;
import com.jd.iot.admin.vo.RoleVO;
import static org.junit.Assert.assertEquals;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotCoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RoleService roleservice;

    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    public void emptyTest() {
    }

    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    // 测试是否能成功获得角色列表
    public void getAllRole_test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/role/findallrole", List.class);
       
    }

    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    // 测试是否能获得指定角色种类的列表
    public void roleMenu_test() {
        String res = this.restTemplate.getForObject("http://localhost:" + port + "/role/rolemenu?roletype={roletype}",
                String.class, 1);
        System.out.println(res);
    }

    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    // 是否可以正确添加新角色并且赋值正确
    public void addNewRole_Test() {
        RoleVO r = new RoleVO();
        RoleVO role = this.restTemplate.postForObject("http://localhost:" + port + "/role/addrole", r, RoleVO.class);
        assertEquals(role.getId().longValue(), 3L);
    }

    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    // 处理不存在参数
    public void findById_Test() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            roleservice.findById(900L);
        });
    }

    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    // 处理非法参数，期待数字输入字符
    public void findById_Test2() {
        RoleVO r = this.restTemplate.getForObject("http://localhost:" + port + "/role/findbyid?id={id}", RoleVO.class,
                "abc");
        assertEquals(r.getId(), null);
    }

    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    // 检查是否可以成功修改指定角色
    public void editRole_Test() {
        RoleVO r = roleservice.findById(1L);
        r.setDescription("优秀的角色");
        r.setRolename("优秀的管理员");
        RoleVO user = this.restTemplate.postForObject("http://localhost:" + port + "/role/editrole/{id}", r,
                RoleVO.class, 1);
        assertEquals(user.getDescription(), roleservice.findById(1L).getDescription());
        assertEquals(user.getRolename(), roleservice.findById(1L).getRolename());
    }

    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    // 检查是否可以成功删除指定角色
    public void deleteRole_Test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/role/deleterole?id={id}", void.class, 1);
        assertSame(roleservice.findById(1L).getIsdeleted(), 1);
    }
}
