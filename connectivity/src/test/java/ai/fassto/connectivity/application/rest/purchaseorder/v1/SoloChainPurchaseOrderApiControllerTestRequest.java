package ai.fassto.connectivity.application.rest.purchaseorder.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.common.utility.HttpTestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 12001, stubs = "classpath:/stubs/purchase-order/*.json")
class SoloChainPurchaseOrderApiControllerTestRequest {
    private static final String API_CONNECTIVITY_V1_PURCHASE_ORDER = "/api/solochain/v1/purchase-order";
    private static final String RESULT_SUCCESS = "success";
    private static final String RESULT_FAILURE = "failure";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("입고 생성 - 모든 파라미터가 정상일 때 - OK 응답 확인")
    void postPurchaseOrder_whenAllRequestParametersAreValid_receiveOk() {
        // given
        HttpEntity<LinkedHashMap<String, Object>> request = new HttpEntity<>(getPurchaseOrderWithOneItem(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_PURCHASE_ORDER,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<>() {
                }
        );

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("입고 생성 - 모든 파라미터가 정상일 때 - success result 응답 확인")
    void postPurchaseOrder_whenAllRequestParametersAreValid_receiveResultAsSuccess() {
        // given
        HttpEntity<LinkedHashMap<String, Object>> request = new HttpEntity<>(getPurchaseOrderWithOneItem(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_PURCHASE_ORDER,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<>() {
                }
        );
        GenericResponse<Object> genericResponse = response.getBody();

        // then
        assertThat(genericResponse).isNotNull();
        assertThat(genericResponse.getResult()).isEqualTo(RESULT_SUCCESS);
    }

    @Test
    @DisplayName("입고 반품 생성 - 모든 파라미터가 정상일 때 - OK 응답 확인")
    void postPurchaseReturnOrder_whenAllRequestParametersAreValid_receiveOk() {
        // given
        HttpEntity<LinkedHashMap<String, Object>> request = new HttpEntity<>(getPurchaseOrderWithOneItem(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_PURCHASE_ORDER,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<>() {
                }
        );

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("입고 반품 생성 - 모든 파라미터가 정상일 때 - success result 응답 확인")
    void postPurchaseReturnOrder_whenAllRequestParametersAreValid_receiveResultAsSuccess() {
        // given
        HttpEntity<LinkedHashMap<String, Object>> request = new HttpEntity<>(getPurchaseOrderWithOneItem(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_PURCHASE_ORDER,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<>() {
                }
        );
        GenericResponse<Object> genericResponse = response.getBody();

        // then
        assertThat(genericResponse).isNotNull();
        assertThat(genericResponse.getResult()).isEqualTo(RESULT_SUCCESS);
    }

    private LinkedHashMap<String, Object> getPurchaseOrderWithOneItem() {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        List<LinkedHashMap<String, Object>> items = new ArrayList<>();
        items.add(getOneItem());
        params.put("orderType", "PurchaseOrder");
        params.put("dataList", items);
        return params;
    }

    private LinkedHashMap<String, Object> getOneItem() {
        LinkedHashMap<String, Object> itemParams = new LinkedHashMap<>();
        itemParams.put("cstCd", "00809");
        itemParams.put("cstNm", "윤다영(고객사)༼ つ ◕_◕ ༽つ");
        itemParams.put("godCd", "00809AXT00009");
        itemParams.put("cstGodCd", "AXT00009");
        itemParams.put("godNm", "젤리");
        itemParams.put("godBarcd", "");
        itemParams.put("ordQty", 123);
        itemParams.put("distTermMgtYn", "N");
        itemParams.put("godInFirstYn", "Y");
        itemParams.put("outStopDay", 5);
        itemParams.put("distNearDay", 5);
        itemParams.put("row_state", "created");
        itemParams.put("ordDt", "20221125");
        itemParams.put("whCd", "TEST");
        itemParams.put("slipNo", "TESTIO221125003");
        itemParams.put("supCd", "");
        itemParams.put("inWay", "02");
        itemParams.put("parcelComp", "");
        itemParams.put("parcelInvoiceNo", "");
        itemParams.put("courierName", "운송차량1");
        itemParams.put("carNumber", "17머4044");
        itemParams.put("loginUserId", "cd988ee9-a92d-11ea-9bd4-0a5b64398cfa");
        itemParams.put("emgrYn", "N");
        itemParams.put("remark", "Connectivity 테스트용");
        itemParams.put("preArv", "N");
        itemParams.put("releaseDt", "2022-11-29");
        itemParams.put("releaseGbn", "2");
        itemParams.put("ordSeq", "1");

        return itemParams;
    }
}

