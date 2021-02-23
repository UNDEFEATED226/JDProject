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

    // 测试是否能成功获得角色列表
    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    public void getAllRole_test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/role/findallrole", List.class);

    }

    // 测试是否能获得指定角色种类的列表
    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    public void roleMenu_test() {
        String res = this.restTemplate.getForObject("http://localhost:" + port + "/role/rolemenu?roletype={roletype}",
                String.class, 1);
        System.out.println(res);
    }

    // 是否可以正确添加新角色并且赋值正确
    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    public void addNewRole_Test() {
        RoleVO r = new RoleVO();
        RoleVO role = this.restTemplate.postForObject("http://localhost:" + port + "/role/addrole", r, RoleVO.class);
        assertEquals(role.getId().longValue(), 3L);
    }

    // 处理不存在参数
    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    public void findById_Test() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            roleservice.findById(900L);
        });
    }

    // 处理非法参数，期待数字输入字符
    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    public void findById_Test2() {
        RoleVO r = this.restTemplate.getForObject("http://localhost:" + port + "/role/findbyid?id={id}", RoleVO.class,
                "abc");
        assertEquals(r.getId(), null);
    }

    // 检查是否可以成功修改指定角色
    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    public void editRole_Test() {
        RoleVO r = roleservice.findById(1L);
        r.setDescription("优秀的角色");
        r.setRolename("优秀的管理员");
        RoleVO user = this.restTemplate.postForObject("http://localhost:" + port + "/role/editrole/{id}", r,
                RoleVO.class, 1);
        assertEquals(user.getDescription(), roleservice.findById(1L).getDescription());
        assertEquals(user.getRolename(), roleservice.findById(1L).getRolename());
    }

    // 检查是否可以成功删除指定角色
    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    public void deleteRole_Test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/role/deleterole?id={id}", void.class, 1);
        assertSame(roleservice.findById(1L).getIsdeleted(), 1);
    }

    // 检查是否可以成功分页
    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    public void pagination_test() {
        String res = this.restTemplate.getForObject(
                "http://localhost:" + port + "/role/findallrolepaginated?pageNo={pageNo}", String.class, 1);
        System.out.println("***");
        System.out.println(res);
        System.out.println("***");
    }

    // 检查是否可以成功查询角色总数
    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    public void count_test() {
        Long count = this.restTemplate.getForObject("http://localhost:" + port + "/role/count", long.class);
        assertEquals(count.longValue(), 2L);
    }

    // 检查是否可以成功查询总页数
    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    public void page_test() {
        Long page = this.restTemplate.getForObject("http://localhost:" + port + "/role/page", long.class);
        assertEquals(page.longValue(), 1L);
    }

    // 检查指定role type角色列表是否可以成功分页
    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    public void pagination1_test() {
        String res = this.restTemplate.getForObject(
                "http://localhost:" + port + "/role/rolemenupaginated?roletype={roletype}&pageNo={pageNo}",
                String.class, 1L, 1L);
        System.out.println("***");
        System.out.println(res);
        System.out.println("***");
    }

    // 检查是否可以成功查询指定role type角色总数
    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    public void count1_test() {
        Long count = this.restTemplate
                .getForObject("http://localhost:" + port + "/role/countbyroletype?roletype={roletype}", long.class, 1L);
        assertEquals(count.longValue(), 1L);
    }

    // 检查是否可以成功查询指定role type总页数
    @Test
    @Sql({ "classpath:sql/integration-test-role.sql" })
    public void page1_test() {
        Long page = this.restTemplate
                .getForObject("http://localhost:" + port + "/role/pagebyroletype?roletype={roletype}", long.class, 2L);
        assertEquals(page.longValue(), 1L);
    }
}
