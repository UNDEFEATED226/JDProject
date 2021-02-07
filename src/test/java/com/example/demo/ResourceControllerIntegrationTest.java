package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import com.google.gson.Gson;
import com.jd.iot.admin.IotCoreApplication;
import com.jd.iot.admin.service.ResourceService;
import com.jd.iot.admin.vo.ResourceVO;
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

@Slf4j
@ActiveProfiles({ "integration" })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotCoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResourceControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ResourceService resourceservice;

    Gson gson = new Gson();

    // 测试是否能成功返回所有资源实体
    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    public void findAllResource_test() {
        String res = this.restTemplate.getForObject("http://localhost:" + port + "/resource/findallresource",
                String.class);
        System.out.println(res);
    }

    // 测试是否能成功指定平台所有资源实体
    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    public void IotMenuResource_test() {
        String res = this.restTemplate.getForObject("http://localhost:" + port + "/resource/resourcemenu?resourcetypeid=3",
                String.class);
        System.out.println(res);
    }

   
    // 测试能否成功软删指定资源实体
    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    public void deleteResource_test() {
        System.out.println("是否删除:"+resourceservice.findById(1L).getIsdeleted());
        this.restTemplate.getForObject("http://localhost:" + port + "/resource/deleteresource?id=1", void.class);
        ResourceVO r = resourceservice.findById(1L);
        System.out.println("是否删除2:"+resourceservice.findById(1L).getIsdeleted());
        assertSame(r.getIsdeleted(), 1);
    }

    // 测试是否能成功通过id查找指定资源实体
    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    public void findById_test() {
        ResourceVO rvo = resourceservice.findById(1L);
        ResourceVO r = this.restTemplate.getForObject("http://localhost:" + port + "/resource/findbyid?id=1",
                ResourceVO.class);
        assertEquals(r.getResname(), rvo.getResname());
        assertEquals(r.getId().longValue(), 1L);
    }

    // findById处理不存在参数
    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    public void findById1_Test() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            resourceservice.findById(900L);
        });
    }

    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    // findById处理非法参数，期待数字输入字符
    public void findById_Test2() {
        ResourceVO r = this.restTemplate.getForObject("http://localhost:" + port + "/user/findbyid?id={id}",
                ResourceVO.class, "abc");
        assertEquals(r.getId(), null);
    }

    // 测试能否成功修改指定资源实体
    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    public void editResource_test() {
        ResourceVO rvo = new ResourceVO();
        rvo.setId(1L);
        rvo.setResname("资资源源");
        ResourceVO r = this.restTemplate.postForObject("http://localhost:" + port + "/resource/editresource/{id}", rvo,
                ResourceVO.class, 1);
        assertEquals(r.getResname(), "资资源源");
        assertEquals(r.getId().longValue(), 1L);
    }
}