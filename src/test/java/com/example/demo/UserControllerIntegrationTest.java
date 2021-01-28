package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.LocalServerPort;

@ActiveProfiles({ "integration" })
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotCoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
		assertEquals(107l, g.getId().longValue());
	}

	@Test
	@Sql({ "classpath:sql/integration-test-user.sql" })
	// 是否可以正确添加新用户并且赋值正确
	public void addNewUser_Test() {
		User u = new User();
		u.setLoginname("user");
		u.setPassword("password");
		String str = this.restTemplate.postForObject("http://localhost:" + port + "/user/adduser/{orgid}",u,String.class,1);
		User user = gson.fromJson(str, User.class);
		assertNotNull(user);
		assertEquals("user", user.getLoginname());
		assertEquals("password", user.getPassword());
	}

	@Test
	@Sql({ "classpath:sql/integration-test-user.sql" })
	// 处理不存在参数
	public void findById_Test() {
		Assertions.assertThrows(ResponseStatusException.class, () -> {
			userservice.findById(900l);
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
}
