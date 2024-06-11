package ai.fassto.connectivity.application.rest.vendor.v1;

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
@AutoConfigureWireMock(port = 12001, stubs = "classpath:/stubs/vendor/*.json")
class SoloChainVendorApiControllerTest {

    private static final String API_CONNECTIVITY_V1_VENDOR = "/api/solochain/v1/vendor";
    private static final String RESULT_SUCCESS = "success";
    private static final String RESULT_FAILURE = "failure";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("공급사 생성 - 모든 파라미터가 정상일 때 - OK 응답 확인")
    void postVendor_whenAllRequestParametersAreValid_receiveOk() {
        // given
        HttpEntity<LinkedHashMap<String, String>> request = new HttpEntity<>(getCreateParams(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<Object> response = testRestTemplate.postForEntity(API_CONNECTIVITY_V1_VENDOR, request, Object.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("공급사 생성 - 모든 파라미터가 정상일 때 - success result 응답 확인")
    void postVendor_whenAllRequestParametersAreValid_receiveResultAsSuccess() {
        // given
        HttpEntity<LinkedHashMap<String, String>> request = new HttpEntity<>(getCreateParams(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_VENDOR,
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
    @DisplayName("공급사 수정 - 모든 파라미터가 정상일 때 - OK 응답 확인")
    void putVendor_whenAllRequestParametersAreValid_receiveOk() {
        // given
        HttpEntity<LinkedHashMap<String, String>> request = new HttpEntity<>(getUpdateParams(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_VENDOR,
                HttpMethod.PUT,
                request,
                new ParameterizedTypeReference<>() {
                }
        );

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("공급사 생성 - 모든 파라미터가 정상일 때 - success result 응답 확인")
    void putVendor_whenAllRequestParametersAreValid_receiveResultAsSuccess() {
        // given
        HttpEntity<LinkedHashMap<String, String>> request = new HttpEntity<>(getUpdateParams(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_VENDOR,
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
        params.put("cstSupCd", "");
        params.put("supNm", "공급사명");
        params.put("cstCd", "04233");
        params.put("cstNm", "[테스트]이재호고객사");
        params.put("dealStrDt_std03", "");
        params.put("dealEndDt_std03", "");
        params.put("zipNo", "06158");
        params.put("addr1", "서울 강남구 삼성로 507 (삼성동, JS타워)");
        params.put("addr2", "9층");
        params.put("ceoNm", "이재호");
        params.put("busNo", "123-45-67890");
        params.put("busSp", "업태");
        params.put("busTp", "업종");
        params.put("telNo", "010-1111-2222");
        params.put("faxNo", "010-1111-2223");
        params.put("useYn", "Y");
        params.put("internalYn", "N");
        params.put("empNm1", "담당자성명");
        params.put("empPosit1", "직위");
        params.put("empTelNo1", "010-4444-5555");
        params.put("empEmail1", "email@fassto.ai");
        params.put("empNm2", "");
        params.put("empPosit2", "");
        params.put("empTelNo2", "");
        params.put("empEmail2", "");
        params.put("loginUserId", "12883882-bc5a-11eb-b712-0ae7a94139ba");

        return params;
    }

    private LinkedHashMap<String, String> getUpdateParams() {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("supCd", "04233001");
        params.put("cstSupCd", "");
        params.put("supNm", "공급사명");
        params.put("cstCd", "04233");
        params.put("cstNm", "[테스트]이재호고객사");
        params.put("dealStrDt_std03", "");
        params.put("dealEndDt_std03", "");
        params.put("zipNo", "06158");
        params.put("addr1", "서울 강남구 삼성로 507 (삼성동, JS타워)");
        params.put("addr2", "9층");
        params.put("ceoNm", "이재호");
        params.put("busNo", "123-45-67890");
        params.put("busSp", "업태");
        params.put("busTp", "업종");
        params.put("telNo", "010-1111-2222");
        params.put("faxNo", "010-1111-2223");
        params.put("useYn", "N");
        params.put("internalYn", "N");
        params.put("empNm1", "담당자성명");
        params.put("empPosit1", "직위");
        params.put("empTelNo1", "010-4444-5555");
        params.put("empEmail1", "email@fassto.ai");
        params.put("empNm2", "");
        params.put("empPosit2", "");
        params.put("empTelNo2", "");
        params.put("empEmail2", "");
        params.put("loginUserId", "12883882-bc5a-11eb-b712-0ae7a94139ba");

        return params;
    }
}