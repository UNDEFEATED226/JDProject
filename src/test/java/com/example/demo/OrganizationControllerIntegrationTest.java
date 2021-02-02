package com.example.demo;

import static org.junit.Assert.assertEquals;

import com.example.demo.entity.Organization;
import com.example.demo.service.OrganizationService;
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

    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    // 验证返回公司示例是否是预期值
    public void compareOrganizationEntity_Test() {
        Organization o = organizationservice.findById(1L);
        Organization f = this.restTemplate.getForObject("http://localhost:" + port + "/organization/findbyid?id={id}",
                Organization.class, 1L);
        assertEquals(o.getId().longValue(), f.getId().longValue());
    }

    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    // 是否可以正确添加新公司并且赋值正确
    public void addNewOrganization_Test() {
        Organization org = new Organization();
        org.setOrgname("org");
        Organization o = this.restTemplate.postForObject("http://localhost:" + port + "/organization/addorganization",
                org, Organization.class);
        assertEquals(o.getId().longValue(), 3L);
        assertEquals(o.getTenantid(), "335");
    }

    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    // 处理不存在参数
    public void findById_Test() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            organizationservice.findById(900L);
        });
    }

    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    // 处理非法参数，期待数字输入字符
    public void findById_Test2() {
        Organization u = this.restTemplate.getForObject("http://localhost:" + port + "/organization/findbyid?id={id}",
                Organization.class, "abc");
        assertEquals(u.getId(), null);
    }

    @Test
    @Sql({ "classpath:sql/integration-test-organization.sql" })
    // 检查是否可以成功修改指定组织
    public void editOrganization_test() {
        Organization temp = new Organization();
        temp.setId(1L);
        temp.setOrgname("上海无名企业");
        temp.setFullparentid("N/A");
        Organization o = this.restTemplate.postForObject(
                "http://localhost:" + port + "/organization/editorganization/{id}", temp, Organization.class, 1);
        System.out.println(o.getOrgname());
        System.out.println(o.getFullparentid());
        assertEquals(o.getOrgname(), "上海无名企业");
        assertEquals(o.getFullparentid(), "N/A");
    }
}