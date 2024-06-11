package ai.fassto.connectivity.application.rest.customer.v1;

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

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 12001, stubs = "classpath:/stubs/customer/*.json")
class SoloChainCustomerApiControllerTest {
    private static final String API_CONNECTIVITY_V1_CUSTOMER = "/api/solochain/v1/customer";
    private static final String RESULT_SUCCESS = "success";
    private static final String RESULT_FAILURE = "failure";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("고객사 생성 - 모든 파라미터가 정상일 때 - OK 응답 확인")
    void postCustomer_whenAllRequestParametersAreValid_receiveOk() {
        // given
        HttpEntity<LinkedHashMap<String, String>> request = new HttpEntity<>(getCreateParams(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<Object> response = testRestTemplate.postForEntity(API_CONNECTIVITY_V1_CUSTOMER, request, Object.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("고객사 생성 - 모든 파라미터가 정상일 때 - success result 응답 확인")
    void postCustomer_whenAllRequestParametersAreValid_receiveResultAsSuccess() {
        // given
        HttpEntity<LinkedHashMap<String, String>> request = new HttpEntity<>(getCreateParams(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_CUSTOMER,
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
    @DisplayName("고객사 수정 - 모든 파라미터가 정상일 때 - OK 응답 확인")
    void putCustomer_whenAllRequestParametersAreValid_receiveOk() {
        // given
        HttpEntity<LinkedHashMap<String, String>> request = new HttpEntity<>(getUpdateParams(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_CUSTOMER,
                HttpMethod.PUT,
                request,
                new ParameterizedTypeReference<>() {
                }
        );

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("고객사 생성 - 모든 파라미터가 정상일 때 - success result 응답 확인")
    void putCustomer_whenAllRequestParametersAreValid_receiveResultAsSuccess() {
        // given
        HttpEntity<LinkedHashMap<String, String>> request = new HttpEntity<>(getUpdateParams(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_CUSTOMER,
                HttpMethod.PUT,
                request,
                new ParameterizedTypeReference<>() {
                }
        );
        GenericResponse<Object> genericResponse = response.getBody();

        // then
        assertThat(genericResponse).isNotNull();
        assertThat(genericResponse.getResult()).isEqualTo(RESULT_SUCCESS);
    }

    private LinkedHashMap<String, String> getCreateParams() {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("supCd", "04233001");
        params.put("wadizPromotion", "");
        params.put("nfaPromotion", "");
        params.put("oldStandCostYn", "Y");
        params.put("oldFeeType", "01");
        params.put("opTp", "03");
        params.put("cstCd", "01123");
        params.put("apprNm", "");
        params.put("cstNm", "이재호");
        params.put("contTp", "03");
        params.put("dealStrDt_std11", "2021-11-17");
        params.put("dealEndDt_std11", "");
        params.put("zipNo", "06158");
        params.put("addr1", "서울 강남구 삼성로 507 (삼성동, JS타워)");
        params.put("addr2", "9층");
        params.put("ceoNm", "이재호");
        params.put("busNo", "123-45-67890");
        params.put("busSp", "업태");
        params.put("busTp", "업종");
        params.put("telNo", "010-1111-2222");
        params.put("faxNo", "010-1111-2223");
        params.put("soldOutTerm", "03");
        params.put("deposit", "0");
        params.put("contEmpNm", "");
        params.put("btbUpTp", "1");
        params.put("useYn", "Y");
        params.put("rtnAddrUseYn", "N");
        params.put("expShowYn", "N");
        params.put("exitYn", "N");
        params.put("standCostYn", "Y");
        params.put("outStopYn", "N");
        params.put("wadizCstYn", "N");
        params.put("feeType", "01");
        params.put("nfaCstYn", "N");
        params.put("serialNoYn", "Y");
        params.put("inOutSameDayYn", "N");
        params.put("outReservationYn", "N");
        params.put("masterUserNm", "이재호");
        params.put("masterPosition", "팀장");
        params.put("masterHpNo", "010-1111-2222");
        params.put("masterEmail", "leejaeho114@gmail.com");
        params.put("empNm1", "");
        params.put("empPosit1", "");
        params.put("empTelNo1", "");
        params.put("empEmail1", "");
        params.put("empNm2", "");
        params.put("empPosit2", "");
        params.put("empTelNo2", "");
        params.put("empEmail2", "");
        params.put("cstMemo", "");
        params.put("outMemo", "");
        params.put("dawnDeliveryYn", "N");
        params.put("sameDayDeliveryYn", "N");
        params.put("oneDayDeliveryYn", "N");
        params.put("oneDayDeliveryHandYn", "N");
        params.put("expressDeliveryYn", "N");
        params.put("standardDeliveryYn", "N");
        params.put("enterpriseDeliveryYn", "N");
        params.put("loginUserId", "2714e6d4-bc6d-11eb-9394-0a5b64398cfa");
        params.put("dealStrDt", "20211117");
        params.put("dealEndDt", "");

        return params;
    }

    private LinkedHashMap<String, String> getUpdateParams() {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("supCd", "04233001");
        params.put("wadizPromotion", "");
        params.put("nfaPromotion", "");
        params.put("oldStandCostYn", "Y");
        params.put("oldFeeType", "01");
        params.put("opTp", "03");
        params.put("cstCd", "01123");
        params.put("apprNm", "");
        params.put("cstNm", "이재호");
        params.put("contTp", "03");
        params.put("dealStrDt_std11", "2021-11-17");
        params.put("dealEndDt_std11", "");
        params.put("zipNo", "06158");
        params.put("addr1", "서울 강남구 삼성로 507 (삼성동, JS타워)");
        params.put("addr2", "9층");
        params.put("ceoNm", "이재호");
        params.put("busNo", "123-45-67890");
        params.put("busSp", "업태");
        params.put("busTp", "업종");
        params.put("telNo", "010-1111-2222");
        params.put("faxNo", "010-1111-2223");
        params.put("soldOutTerm", "03");
        params.put("deposit", "0");
        params.put("contEmpNm", "");
        params.put("btbUpTp", "1");
        params.put("useYn", "N");
        params.put("rtnAddrUseYn", "N");
        params.put("expShowYn", "N");
        params.put("exitYn", "N");
        params.put("standCostYn", "Y");
        params.put("outStopYn", "N");
        params.put("wadizCstYn", "N");
        params.put("feeType", "01");
        params.put("nfaCstYn", "N");
        params.put("serialNoYn", "Y");
        params.put("inOutSameDayYn", "N");
        params.put("outReservationYn", "N");
        params.put("masterUserNm", "이재호");
        params.put("masterPosition", "팀장");
        params.put("masterHpNo", "010-1111-2222");
        params.put("masterEmail", "leejaeho114@gmail.com");
        params.put("empNm1", "");
        params.put("empPosit1", "");
        params.put("empTelNo1", "");
        params.put("empEmail1", "");
        params.put("empNm2", "");
        params.put("empPosit2", "");
        params.put("empTelNo2", "");
        params.put("empEmail2", "");
        params.put("cstMemo", "");
        params.put("outMemo", "");
        params.put("dawnDeliveryYn", "N");
        params.put("sameDayDeliveryYn", "N");
        params.put("oneDayDeliveryYn", "N");
        params.put("oneDayDeliveryHandYn", "N");
        params.put("expressDeliveryYn", "N");
        params.put("standardDeliveryYn", "N");
        params.put("enterpriseDeliveryYn", "N");
        params.put("loginUserId", "2714e6d4-bc6d-11eb-9394-0a5b64398cfa");
        params.put("dealStrDt", "20211117");
        params.put("dealEndDt", "");

        return params;
    }

}