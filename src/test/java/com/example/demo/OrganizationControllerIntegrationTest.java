package com.example.demo;

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
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.NoSuchElementException;
import com.example.demo.Entity.Organization;
import com.example.demo.Service.OrganizationService;
import lombok.extern.slf4j.Slf4j;

@ActiveProfiles({"integration"})
@Slf4j
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

	@Test
	@Sql({ "classpath:sql/integration-test-organization.sql" })
	// 验证返回公司示例是否是预期值
	public void compareOrganizationEntity_Test() {
		Organization o = organizationservice.findById(1l);
		Organization f = this.restTemplate.getForObject("http://localhost:" + port + "/organization/findbyid?id={id}",
				Organization.class, 1l);
		assertEquals(o.getId().longValue(), f.getId().longValue());
	}

	@Test
	@Sql({ "classpath:sql/integration-test-organization.sql" })
	// 是否可以正确添加新公司并且赋值正确
	public void addNewOrganization_Test() {
		Organization o = this.restTemplate.postForObject("http://localhost:" + port + "/organization/addorganization",
				new Organization(), Organization.class);
		assertEquals(o.getId().longValue(), 2l);
		assertEquals(o.getTenantid(), "334");
	}

	@Test
	@Sql({ "classpath:sql/integration-test-user.sql" })
	// 处理不存在参数
	public void findById_Test() {
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			organizationservice.findById(900l);
		});
	}

	@Test
	@Sql({ "classpath:sql/integration-test-user.sql" })
	// 处理非法参数，期待数字输入字符
	public void findById_Test2() {
		Organization u = this.restTemplate.getForObject("http://localhost:" + port + "/organization/findbyid?id={id}",
				Organization.class, "abc");
		assertEquals(u.getId(), null);
	}
}
