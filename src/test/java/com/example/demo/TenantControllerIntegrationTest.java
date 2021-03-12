package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import com.jd.iot.admin.IotCoreApplication;
import com.jd.iot.admin.service.TenantService;
import com.jd.iot.admin.vo.TenantVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotCoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TenantControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TenantService tenantservice;

    @Test
    @Sql({ "classpath:sql/integration-test-tenant.sql" })
    public void emptyTest() {
    }

    // 测试能否成功查询租户列表
    @Test
    @Sql({ "classpath:sql/integration-test-tenant.sql" })
    public void findAllTenant_test() {
        String res = this.restTemplate.getForObject("http://localhost:" + port + "/tenant/findalltenant", String.class);
        System.out.println("***");
        System.out.println(res);
        System.out.println("***");
    }

    // 测试能否成功添加新租户
    @Test
    @Sql({ "classpath:sql/integration-test-tenant.sql" })
    public void addTenant_test() {
        TenantVO tenantvo = new TenantVO();
        tenantvo.setName("新租户");
        TenantVO t = this.restTemplate.postForObject("http://localhost:" + port + "/tenant/addtenant", tenantvo,
                TenantVO.class);
        assertEquals(t.getId().longValue(), 3L);
        assertEquals(t.getName(), tenantservice.findById(3L).getName());
    }

    // 测试是否能正确修改租户
    @Test
    @Sql({ "classpath:sql/integration-test-tenant.sql" })
    public void editTenant_test() {
        TenantVO tenantvo = tenantservice.findById(1L);
        tenantvo.setName("修改名字");
        TenantVO t = this.restTemplate.postForObject("http://localhost:" + port + "/tenant/edittenant/{id}", tenantvo,
                TenantVO.class, 1);
        assertEquals(t.getName(), "修改名字");

    }

    // 测试是否能正确删除租户
    @Test
    @Sql({ "classpath:sql/integration-test-tenant.sql" })
    public void deleteTenant_test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/tenant/deletetenant?id={id}", void.class, 1);
        assertSame(tenantservice.findById(1L).getIsdeleted(), 1);
    }

    // 测试是否能通过id成功查找指定租户
    @Test
    @Sql({ "classpath:sql/integration-test-tenant.sql" })
    public void findById_test() {
        TenantVO tenantvo = this.restTemplate.getForObject("http://localhost:" + port + "/tenant/findbyid?id={id}",
                TenantVO.class, 1);
        assertSame(tenantvo.getIsdeleted(), 0);
        assertEquals(tenantvo.getId().longValue(), 1L);
        assertEquals(tenantvo.getName(), tenantservice.findById(1L).getName());
    }

    // 检查是否可以成功分页
    @Test
    @Sql({ "classpath:sql/integration-test-tenant.sql" })
    public void pagination_test() {
        String res = this.restTemplate.getForObject(
                "http://localhost:" + port + "/tenant/findalltenantpaginated?pageNo={pageNo}", String.class, 1);
        System.out.println("***");
        System.out.println(res);
        System.out.println("***");
    }

    // 检查是否可以成功查询租户总数
    @Test
    @Sql({ "classpath:sql/integration-test-tenant.sql" })
    public void count_test() {
        Long count = this.restTemplate.getForObject("http://localhost:" + port + "/tenant/count", long.class);
        assertEquals(count.longValue(), 2L);
    }

    // 检查是否可以成功查询总页数
    @Test
    @Sql({ "classpath:sql/integration-test-tenant.sql" })
    public void page_test() {
        Long page = this.restTemplate.getForObject("http://localhost:" + port + "/tenant/page", long.class);
        assertEquals(page.longValue(), 1L);
    }
}
