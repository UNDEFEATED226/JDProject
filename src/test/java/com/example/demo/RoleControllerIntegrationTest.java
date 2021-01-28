package com.example.demo;

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
import com.example.demo.Entity.Role;
import com.example.demo.Service.RoleService;
import lombok.extern.slf4j.Slf4j;

@ActiveProfiles({"integration"})
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotCoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleControllerIntegrationTest {

	@LocalServerPort
	private int port;
	
	@Autowired 
	RoleService roleservice;
	
	@Autowired 
	TestRestTemplate restTemplate;
	
	@Test
	@Sql({ "classpath:sql/integration-test-role.sql" })
	public void emptyTest() {
	}

	//验证是否能成功返回所有角色
	@Test
	@Sql({ "classpath:sql/integration-test-role.sql" })
	public void getAllRole_Test() {
		this.restTemplate.getForObject("http://localhost:" + port + "/role/findallrole", List.class);
	}

	// 验证返回角色示例是否是预期值
	@Test
	@Sql({ "classpath:sql/integration-test-role.sql" })
	public void compareUserEntity_Test() {
		Role r = this.restTemplate.getForObject("http://localhost:" + port + "/role/findbyid?id={id}", Role.class, 8);
		assertEquals(8l,r.getId().longValue());
	}

	// 是否可以正确添加新角色并且赋值正确
	@Test
	@Sql({ "classpath:sql/integration-test-role.sql" })
	public void addNewUser_Test() {
		Role r = this.restTemplate.postForObject("http://localhost:" + port + "/role/addrole", new Role(), Role.class);
		assertEquals(r.getId().longValue(),9l);
	}

	// 处理不存在参数
	@Test
	@Sql({ "classpath:sql/integration-test-role.sql" })
	public void findById_Test() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			roleservice.findById(100l);
		});
	}

	// 处理非法参数，期待数字输入字符
	@Test
	@Sql({ "classpath:sql/integration-test-role.sql" })
	public void findById_Test2() {
		Role r = this.restTemplate.getForObject("http://localhost:" + port + "/role/findbyid?id={id}", Role.class,
				"abc");
		assertEquals(r.getId(), null);
	}
	
}
