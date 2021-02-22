package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.jd.iot.admin.IotCoreApplication;
import com.jd.iot.admin.service.OrganizationService;
import com.jd.iot.admin.vo.OrganizationVO;
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
public class OrganizationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrganizationService organizationservice;

    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    public void emptyTest() {
    }

    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    public void getAllOrganization_Test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/organization/findallorganization", List.class);
    }

    // 验证返回公司示例是否是预期值
    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    public void compareOrganizationEntity_Test() {
        OrganizationVO o = organizationservice.findById(1L);
        OrganizationVO f = this.restTemplate.getForObject("http://localhost:" + port + "/organization/findbyid?id={id}",
                OrganizationVO.class, 1L);
        assertEquals(o.getId().longValue(), f.getId().longValue());
    }

    // 是否可以正确添加新公司并且赋值正确
    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    public void addNewOrganization_Test() {
        OrganizationVO org = new OrganizationVO();
        org.setOrgname("org");
        OrganizationVO o = this.restTemplate.postForObject("http://localhost:" + port + "/organization/addorganization",
                org, OrganizationVO.class);
        assertEquals(o.getId().longValue(), 3L);
    }

    // 处理不存在参数
    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    public void findById_Test() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            organizationservice.findById(900L);
        });
    }

    // 处理非法参数，期待数字输入字符
    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    public void findById_Test2() {
        OrganizationVO u = this.restTemplate.getForObject("http://localhost:" + port + "/organization/findbyid?id={id}",
                OrganizationVO.class, "abc");
        assertEquals(u.getId(), null);
    }

    // 检查是否可以成功修改指定组织
    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    public void editOrganization_test() {
        OrganizationVO temp = new OrganizationVO();
        temp.setId(1L);
        temp.setOrgname("上海无名企业");
        temp.setFullparentid("N/A");
        OrganizationVO o = this.restTemplate.postForObject(
                "http://localhost:" + port + "/organization/editorganization/{id}", temp, OrganizationVO.class, 1);
        assertEquals(o.getOrgname(), "上海无名企业");
        assertEquals(o.getFullparentid(), "N/A");
    }

    // 检查是否可以成功删除指定组织
    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    public void deleteOrganization_test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/organization/deleteorganization?id={id}",
                void.class, 1);
        assertSame(organizationservice.findById(1L).getIsdeleted(), 1);
    }

    // 检查是否可以成功分页
    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    public void pagination_test() {
        String res = this.restTemplate.getForObject(
                "http://localhost:" + port + "/organization/findallorganizationpaginated?pageNo={pageNo}", String.class,
                1);
        System.out.println("***");
        System.out.println(res);
        System.out.println("***");
    }

    // 检查是否可以成功查询组织总数
    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    public void count_test() {
        Long count = this.restTemplate.getForObject("http://localhost:" + port + "/organization/count", long.class);
        assertEquals(count.longValue(), 2L);
    }

    // 检查是否可以成功查询总页数
    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    public void page_test() {
        Long page = this.restTemplate.getForObject("http://localhost:" + port + "/organization/page", long.class);
        assertEquals(page.longValue(), 1L);
    }
}