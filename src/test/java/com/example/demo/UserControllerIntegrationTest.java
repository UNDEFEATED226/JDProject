package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.Assert.assertEquals;
import java.util.List;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.LocalServerPort;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotCoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {
	@LocalServerPort
	private int port;

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
		User u = userservice.findById(107l);
		User g = this.restTemplate.getForObject("http://localhost:" + port + "/user/findbyid?id={id}", User.class, 107);
		assertEquals(u.getId().longValue(), g.getId().longValue());
	}

	@Test
	@Sql({ "classpath:sql/integration-test-user.sql" })
	// 是否可以正确添加新用户并且赋值正确
	public void addNewUser_Test() {
		Long max = userservice.maxId() + 1;
		User u = this.restTemplate.postForObject("http://localhost:" + port + "/user/adduser", new User(), User.class);
		assertEquals(u.getId().longValue(), max.longValue());
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
