package com.example.demo;

import com.jd.smart.szairlines.WebApplication;
import com.jd.smart.szairlines.flightbag.model.AttachmentModel;
import com.jd.smart.szairlines.flightbag.model.AuditModel;
import com.jd.smart.szairlines.flightbag.model.ContractModel;
import com.jd.smart.szairlines.flightbag.model.DeviceModel;
import com.jd.smart.szairlines.flightbag.response.AuditQueryResponse;
import com.jd.smart.szairlines.flightbag.response.CommonResponse;
import com.jd.smart.szairlines.flightbag.response.ContractQueryResponse;
import com.jd.smart.szairlines.flightbag.service.ContractService;
import com.jd.smart.szairlines.flightbag.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import static com.jd.smart.szairlines.flightbag.controller.rest.ContractAttachmentControllerIntegrationTests.getMockedAttachmentModel;
import static com.jd.smart.szairlines.flightbag.controller.rest.ContractControllerIntegrationTests.getMockedContractModel;
import static com.jd.smart.szairlines.flightbag.controller.rest.DeviceControllerIntegrationTests.getMockedDeviceModel;
import static com.jd.smart.szairlines.flightbag.response.ResponseConstant.RESPONSE_CODE_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"integration"})
public class UserControllerIntegrationTest {
    private static final String hostUriPrefix = "http://localhost:";
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Sql({"classpath:sql/integration-test-device-schema.sql", "classpath:sql/integration-test-device-data.sql"})
    public void testGetAttachmentsByContractId_ExpectAllVisibleInOrderedResult() {

    }

    @Test
    @Sql({"classpath:sql/integration-test-device-schema.sql", "classpath:sql/integration-test-device-data.sql"})
    public void testContractInsertActionLogged() {
        String url = getContractUrl();
        TestUtil.assertCommonResponse_201(TestUtil.Contract.add(url, getMockedContractModel(true), this.restTemplate));

        AuditModel model = getAuditById(1L);
        assertEquals(AuditModel.NS_CONTRACT, model.getNamespace());
        assertEquals(AuditModel.ACTION_POST, model.getAction());
        assertTrue(StringUtils.isNotBlank(model.getDiffStr()));
        assertEquals("6", model.getExternalId());
    }

    @Test
    @Sql({"classpath:sql/integration-test-device-schema.sql", "classpath:sql/integration-test-device-data.sql"})
    public void testContractUpdateActionLogged() {
        String url = getContractUrl() + "1";
        ContractModel contractModel = getMockedContractModel(true);
        TestUtil.assertCommonResponse_200(TestUtil.Contract.update(url, contractModel, this.restTemplate));

        AuditModel model = getAuditById(1L);
        assertEquals(AuditModel.NS_CONTRACT, model.getNamespace());
        assertEquals(AuditModel.ACTION_PUT, model.getAction());
        assertTrue(StringUtils.isNotBlank(model.getDiffStr()));
        assertEquals("1", model.getExternalId());
    }

    @Test
    @Sql({"classpath:sql/integration-test-device-schema.sql", "classpath:sql/integration-test-device-data.sql"})
    public void testContractDeleteActionLogged() {
        String url = getContractUrl() + "?id=1";
        TestUtil.assertCommonResponse_200(TestUtil.Contract.delete(url, this.restTemplate));

        AuditModel model = getAuditById(1L);
        assertEquals(AuditModel.NS_CONTRACT, model.getNamespace());
        assertEquals(AuditModel.ACTION_SOFT_DELETE, model.getAction());
        assertTrue(StringUtils.isNotBlank(model.getDiffStr()));
        assertEquals("1", model.getExternalId());
    }

    private AuditModel getAuditById(Long id) {
        AuditQueryResponse response = this.restTemplate
                .getForObject(getAuditUrl() + id, AuditQueryResponse.class);
        asserArraytEquals(RESPONSE_CODE_OK, response.getCode());
        assertEquals(1, response.getData().getList().size());

        return response.getData().getList().get(0);
    }
    
    private User getUserById(Long id) {
    	
    }

    private AuditModel queryAuditLogs() {
        AuditQueryResponse response = this.restTemplate
                .getForObject(getAuditUrl(), AuditQueryResponse.class);
        assertEquals(RESPONSE_CODE_OK, response.getCode());
        assertEquals(1, response.getData().getList().size());

        return response.getData().getList().get(0);
    }

    private AuditModel queryAuditLogsByConditjon(String url) {
        AuditQueryResponse response = this.restTemplate
                .getForObject(url, AuditQueryResponse.class);
        assertEquals(RESPONSE_CODE_OK, response.getCode());
        assertEquals(1, response.getData().getList().size());

        return response.getData().getList().get(0);
    }
    
    private String getUserUrl() {
        return hostUriPrefix + port + "/user";
    }
}
