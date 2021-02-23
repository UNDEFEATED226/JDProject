package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.jd.iot.admin.IotCoreApplication;
import com.jd.iot.admin.service.ResourceService;
import com.jd.iot.admin.vo.ResourceVO;
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
public class ResourceControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ResourceService resourceservice;

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
        String res = this.restTemplate
                .getForObject("http://localhost:" + port + "/resource/resourcemenu?resourcetypeid=3", String.class);
        System.out.println(res);
    }

    // 测试能否成功软删指定资源实体
    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    public void deleteResource_test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/resource/deleteresource?id=1", void.class);
        ResourceVO r = resourceservice.findById(1L);
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

    // 测试能否成功添加资源
    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    public void addResource_test() {
        ResourceVO temp = new ResourceVO();
        temp.setResname("添加1");
        temp.setRestypeid(1L);
        ResourceVO temp1 = new ResourceVO();
        temp1.setResname("添加2");
        temp1.setRestypeid(6L);
        ResourceVO r1 = this.restTemplate.postForObject("http://localhost:" + port + "/resource/addresource", temp,
                ResourceVO.class);
        ResourceVO r2 = this.restTemplate.postForObject("http://localhost:" + port + "/resource/addresource", temp1,
                ResourceVO.class);
        assertEquals(r1.getId().longValue(), 6L);
        assertEquals(r2.getId().longValue(), 2001L);
    }

    // 检查是否可以成功分页
    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    public void pagination_test() {
        String res = this.restTemplate.getForObject(
                "http://localhost:" + port + "/resource/findallresourcepaginated?pageNo={pageNo}", String.class, 1);
        System.out.println("***");
        System.out.println(res);
        System.out.println("***");
    }

    // 检查是否可以成功查询组织总数
    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    public void count_test() {
        Long count = this.restTemplate.getForObject("http://localhost:" + port + "/resource/count", long.class);
        assertEquals(count.longValue(), 7L);
    }

    // 检查是否可以成功查询总页数
    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    public void page_test() {
        Long page = this.restTemplate.getForObject("http://localhost:" + port + "/resource/page", long.class);
        assertEquals(page.longValue(), 1L);
    }

    // 检查指定res type id资源列表是否可以成功分页
    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    public void pagination1_test() {
        String res = this.restTemplate.getForObject(
                "http://localhost:" + port + "/resource/resourcemenupaginated?restypeid={restypeid}&pageNo={pageNo}",
                String.class, 2L, 1L);
        System.out.println("***");
        System.out.println(res);
        System.out.println("***");
    }

    // 检查是否可以成功查询指定res type id组织总数
    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    public void count1_test() {
        Long count = this.restTemplate.getForObject(
                "http://localhost:" + port + "/resource/countbyrestypeid?restypeid={restypeid}", long.class, 1L);
        assertEquals(count.longValue(), 3L);
    }

    // 检查是否可以成功查询指定res type id总页数
    @Test
    @Sql({ "classpath:sql/integration-test-resource.sql" })
    public void page1_test() {
        Long page = this.restTemplate.getForObject(
                "http://localhost:" + port + "/resource/pagebyrestypeid?restypeid={restypeid}", long.class, 2L);
        assertEquals(page.longValue(), 1L);
    }
}
