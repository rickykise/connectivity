package ai.fassto.connectivity.application.rest.item.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.common.utility.HttpTestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 12001, stubs = "classpath:/stubs/item/*.json")
class SoloChainItemApiControllerTest {
    private static final String API_CONNECTIVITY_V1_ITEM = "/api/solochain/v1/item";
    private static final String RESULT_SUCCESS = "success";
    private static final String RESULT_FAILURE = "failure";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("상품 생성 - 모든 파라미터가 정상일 때 - OK 응답 확인")
    void postItem_whenAllRequestParametersAreValid_receiveOk() {
        // given
        HttpEntity<LinkedHashMap<String, Object>> request = new HttpEntity<>(getCreateParams(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_ITEM,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<>() {
                }
        );

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("상품 생성 - 모든 파라미터가 정상일 때 - success result 응답 확인")
    void postItem_whenAllRequestParametersAreValid_receiveResultAsSuccess() {
        // given
        HttpEntity<LinkedHashMap<String, Object>> request = new HttpEntity<>(getCreateParams(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_ITEM,
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
    @DisplayName("상품 수정 - 모든 파라미터가 정상일 때 - OK 응답 확인")
    void putItem_whenAllRequestParametersAreValid_receiveOk() {
        // given
        HttpEntity<LinkedHashMap<String, Object>> request = new HttpEntity<>(getUpdateParams(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_ITEM,
                HttpMethod.PUT,
                request,
                new ParameterizedTypeReference<>() {
                }
        );

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("상품 수정 - 모든 파라미터가 정상일 때 - success result 응답 확인")
    void putItem_whenAllRequestParametersAreValid_receiveResultAsSuccess() {
        // given
        HttpEntity<LinkedHashMap<String, Object>> request = new HttpEntity<>(getUpdateParams(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_ITEM,
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

    @Test
    @DisplayName("상품 삭제 - 모든 파라미터가 정상일 때 - OK 응답 확인")
    void patchItem_whenAllRequestParametersAreValid_receiveOk() {
        // given
        HttpEntity<LinkedHashMap<String, Object>> request = new HttpEntity<>(getDeleteParams(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_ITEM,
                HttpMethod.PATCH,
                request,
                new ParameterizedTypeReference<>() {
                }
        );

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("상품 삭제 - 모든 파라미터가 정상일 때 - success result 응답 확인")
    void patchItem_whenAllRequestParametersAreValid_receiveResultAsSuccess() {
        // given
        HttpEntity<LinkedHashMap<String, Object>> request = new HttpEntity<>(getDeleteParams(), HttpTestUtil.getJsonHeaders());

        // when
        ResponseEntity<GenericResponse<Object>> response = testRestTemplate.exchange(
                API_CONNECTIVITY_V1_ITEM,
                HttpMethod.PATCH,
                request,
                new ParameterizedTypeReference<>() {
                }
        );
        GenericResponse<Object> genericResponse = response.getBody();

        // then
        assertThat(genericResponse).isNotNull();
        assertThat(genericResponse.getResult()).isEqualTo(RESULT_SUCCESS);
    }

    private LinkedHashMap<String, Object> getDeleteParams() {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        List<LinkedHashMap<String, Object>> items = new ArrayList<>();

        LinkedHashMap<String, Object> itemParams = new LinkedHashMap<>();
        itemParams.put("orgGodCd", "01076HAA00024");
        itemParams.put("godCd", "01076HAA00024");
        itemParams.put("godNm", "스킨(21년 제조)");
        itemParams.put("godType", "1");
        itemParams.put("godTypeNm", "단품");
        itemParams.put("cstGodCd", "HAA00024");
        itemParams.put("cstCd", "01076");
        itemParams.put("cstNm", "youngwoo choi");
        itemParams.put("giftDiv", "01");
        itemParams.put("giftDivNm", "본품");
        itemParams.put("godBarcd", "");
        itemParams.put("distTermMgtYn", "Y");
        itemParams.put("useYn", "Y");
        itemParams.put("distNearDay", "5");
        itemParams.put("outStopDay", "5");
        itemParams.put("abroadDlvrYn", "false");
        itemParams.put("loginUserId", "e6a7d5a2-fef3-11eb-9394-0a5b64398cfa");
        items.add(itemParams);

        params.put("items", items);

        return params;
    }

    private LinkedHashMap<String, Object> getUpdateParams() {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        List<LinkedHashMap<String, Object>> items = new ArrayList<>();

        LinkedHashMap<String, Object> itemParams0 = new LinkedHashMap<>();
        itemParams0.put("ordNoBlock", "Y");
        itemParams0.put("godCd", "01076HAAB00024");
        itemParams0.put("cstGodCd", "HAAB00024");
        itemParams0.put("godNm", "강아지 장난감1");
        itemParams0.put("godBarcd", "");
        itemParams0.put("godSerialNoYn", "Y");
        itemParams0.put("godSerialNoYnNm", "사용함");
        itemParams0.put("supCd", "");
        itemParams0.put("supNm", "");
        itemParams0.put("distTermMgtYn", "N");
        itemParams0.put("distTermMgtYnNm", "필요 없음");
        itemParams0.put("dealTemp", "03");
        itemParams0.put("dealTempNm", "상온");
        itemParams0.put("strTempNm", "해당없음");
        itemParams0.put("bufGodYn", "N");
        itemParams0.put("bufGodYnNm", "필요 없음");
        itemParams0.put("giftDiv", "01");
        itemParams0.put("giftDivNm", "본품");
        itemParams0.put("boxDiv", "1");
        itemParams0.put("boxDivNm", "파스토 박스");
        itemParams0.put("invGodNm", "럭키박스 이벤트 상품");
        itemParams0.put("invGodNmUseYn", "Y");
        itemParams0.put("safetyStock", "50");
        itemParams0.put("useYn", "Y");
        itemParams0.put("useYnNm", "사용함");
        itemParams0.put("errCd", "OK");
        itemParams0.put("errMsg", "정상");
        itemParams0.put("row_state", "none");
        itemParams0.put("cstCd", "01076");
        itemParams0.put("loginUserId", "e6a7d5a2-fef3-11eb-9394-0a5b64398cfa");
        items.add(itemParams0);

        LinkedHashMap<String, Object> itemParams1 = new LinkedHashMap<>();
        itemParams1.put("ordNoBlock", "Y");
        itemParams1.put("godCd", "01076HAAB00025");
        itemParams1.put("cstGodCd", "HAAB00025");
        itemParams1.put("godNm", "강아지 장난감2");
        itemParams1.put("godBarcd", "");
        itemParams1.put("godSerialNoYn", "Y");
        itemParams1.put("godSerialNoYnNm", "사용함");
        itemParams1.put("supCd", "");
        itemParams1.put("supNm", "");
        itemParams1.put("distTermMgtYn", "N");
        itemParams1.put("distTermMgtYnNm", "필요 없음");
        itemParams1.put("dealTemp", "03");
        itemParams1.put("dealTempNm", "상온");
        itemParams1.put("strTempNm", "해당없음");
        itemParams1.put("bufGodYn", "Y");
        itemParams1.put("bufGodYnNm", "기본완충재");
        itemParams1.put("giftDiv", "01");
        itemParams1.put("giftDivNm", "본품");
        itemParams1.put("boxDiv", "2");
        itemParams1.put("boxDivNm", "자사 박스");
        itemParams1.put("invGodNm", "");
        itemParams1.put("invGodNmUseYn", "N");
        itemParams1.put("useYn", "Y");
        itemParams1.put("useYnNm", "사용함");
        itemParams1.put("errCd", "OK");
        itemParams1.put("errMsg", "정상");
        itemParams1.put("row_state", "none");
        itemParams1.put("cstCd", "01076");
        itemParams1.put("loginUserId", "e6a7d5a2-fef3-11eb-9394-0a5b64398cfa");
        items.add(itemParams1);

        LinkedHashMap<String, Object> itemParams2 = new LinkedHashMap<>();
        itemParams2.put("ordNoBlock", "Y");
        itemParams2.put("godCd", "01076HAAB00026");
        itemParams2.put("cstGodCd", "HAAB00026");
        itemParams2.put("godNm", "강아지 장난감3");
        itemParams2.put("godBarcd", "");
        itemParams2.put("godSerialNoYn", "Y");
        itemParams2.put("godSerialNoYnNm", "사용함");
        itemParams2.put("supCd", "");
        itemParams2.put("supNm", "");
        itemParams2.put("distTermMgtYn", "N");
        itemParams2.put("distTermMgtYnNm", "필요 없음");
        itemParams2.put("dealTemp", "03");
        itemParams2.put("dealTempNm", "상온");
        itemParams2.put("strTempNm", "해당없음");
        itemParams2.put("bufGodYn", "A");
        itemParams2.put("bufGodYnNm", "추가완충재");
        itemParams2.put("giftDiv", "01");
        itemParams2.put("giftDivNm", "본품");
        itemParams2.put("boxDiv", "4");
        itemParams2.put("boxDivNm", "파스토 폴리백");
        itemParams2.put("invGodNm", "");
        itemParams2.put("invGodNmUseYn", "N");
        itemParams2.put("useYn", "Y");
        itemParams2.put("useYnNm", "사용함");
        itemParams2.put("errCd", "OK");
        itemParams2.put("errMsg", "정상");
        itemParams2.put("row_state", "none");
        itemParams2.put("cstCd", "01076");
        itemParams2.put("loginUserId", "e6a7d5a2-fef3-11eb-9394-0a5b64398cfa");
        items.add(itemParams2);


        LinkedHashMap<String, Object> itemParams3 = new LinkedHashMap<>();
        itemParams3.put("ordNoBlock", "Y");
        itemParams3.put("godCd", "01076HAAB00027");
        itemParams3.put("cstGodCd", "HAAB00027");
        itemParams3.put("godNm", "강아지 장난감4");
        itemParams3.put("godBarcd", "");
        itemParams3.put("godSerialNoYn", "N");
        itemParams3.put("godSerialNoYnNm", "사용하지 않음");
        itemParams3.put("supCd", "");
        itemParams3.put("supNm", "");
        itemParams3.put("distTermMgtYn", "Y");
        itemParams3.put("distTermMgtYnNm", "필요");
        itemParams3.put("dealTemp", "03");
        itemParams3.put("dealTempNm", "상온");
        itemParams3.put("strTempNm", "해당없음");
        itemParams3.put("distNearDay", "5");
        itemParams3.put("outStopDay", "5");
        itemParams3.put("bufGodYn", "A");
        itemParams3.put("bufGodYnNm", "추가완충재");
        itemParams3.put("giftDiv", "01");
        itemParams3.put("giftDivNm", "본품");
        itemParams3.put("boxDiv", "5");
        itemParams3.put("boxDivNm", "자사 폴리백");
        itemParams3.put("invGodNm", "");
        itemParams3.put("invGodNmUseYn", "N");
        itemParams3.put("useYn", "Y");
        itemParams3.put("useYnNm", "사용함");
        itemParams3.put("errCd", "OK");
        itemParams3.put("errMsg", "정상");
        itemParams3.put("row_state", "none");
        itemParams3.put("cstCd", "01076");
        itemParams3.put("loginUserId", "e6a7d5a2-fef3-11eb-9394-0a5b64398cfa");
        items.add(itemParams3);

        params.put("items", items);

        return params;
    }

    private LinkedHashMap<String, Object> getCreateParams() {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();

        List<LinkedHashMap<String, Object>> items = new ArrayList<>();

        LinkedHashMap<String, Object> itemParams = new LinkedHashMap<>();
        itemParams.put("orgGodCd", "01076HA00027");
        itemParams.put("fileSeq", "");
        itemParams.put("origin", "");
        itemParams.put("abroadGdsMsdsSeq", "13365");
        itemParams.put("orgBoxDiv", "2");
        itemParams.put("orgBufGodYn", "Y");
        itemParams.put("orgLoadingDirection", "NONE");
        itemParams.put("oriGodSerialNoYn", "Y");
        itemParams.put("stockQty", "");
        itemParams.put("giftDiv", "01");
        itemParams.put("subMate", "");
        itemParams.put("godNm", "곰탕");
        itemParams.put("godEnNm", "gomtang3");
        itemParams.put("invGodNmUseYn", "N");
        itemParams.put("invGodNm", "");
        itemParams.put("cstGodCd", "HA00027");
        itemParams.put("godBarcd", "");
        itemParams.put("godSerialNoYn", "Y");
        itemParams.put("useYn", "Y");
        itemParams.put("dealTemp", "03");
        itemParams.put("distTermMgtYn", "N");
        itemParams.put("distNearDay", "5");
        itemParams.put("outStopDay", "5");
        itemParams.put("boxDiv", "2");
        itemParams.put("mpckYn", 1);
        itemParams.put("loadingDirection", "NONE");
        itemParams.put("bufGodYn", "Y");
        itemParams.put("std01aSafetyStock", "0");
        itemParams.put("oneDayDlvUseYn", "Y");
        itemParams.put("oneDayDlvConf", "1");
        itemParams.put("oneday_dlv_str_dt", "2022-11-21");
        itemParams.put("oneday_dlv_end_dt", "2022-11-21");
        itemParams.put("cstCd", "01076");
        itemParams.put("cstNm", "youngwoo choi");
        itemParams.put("supCd", "");
        itemParams.put("supNm", "");
        itemParams.put("godInOutSameDayYn", "Y");
        itemParams.put("godOutReservationYn", "N");
        itemParams.put("separateYn", "");
        itemParams.put("abroadDlvrYn", 1);
        itemParams.put("cateCd", "10012009");
        itemParams.put("lCateNm", "가구/인테리어");
        itemParams.put("cateNm", "홈데코");
        itemParams.put("godType", "1");
        itemParams.put("seasonCd", "0");
        itemParams.put("genderCd", "A");
        itemParams.put("pickFac", "");
        itemParams.put("makeYr", "");
        itemParams.put("externalGodImgUrl", "");
        itemParams.put("cstGodImgUrl", "");
        itemParams.put("inPr", "0");
        itemParams.put("salPr", "0");
        itemParams.put("godPr", "0");
        itemParams.put("godVolume", "");
        itemParams.put("godWidth", "0");
        itemParams.put("godLength", "0");
        itemParams.put("godHeight", "0");
        itemParams.put("godBulk", "0");
        itemParams.put("godWeight", "0");
        itemParams.put("godSideSum", "0");
        itemParams.put("inBoxWidth", "0");
        itemParams.put("inBoxLength", "0");
        itemParams.put("inBoxHeight", "0");
        itemParams.put("inBoxBulk", "0");
        itemParams.put("inBoxWeight", "0");
        itemParams.put("inBoxSideSum", "0");
        itemParams.put("inBoxInCnt", "0");
        itemParams.put("pltInCnt", "0");
        itemParams.put("outCanDay", "0");
        itemParams.put("inCanDay", "0");
        itemParams.put("godCd", "01076HA00027");
        itemParams.put("saleUnitQty", "1");
        itemParams.put("abroadNationCd", "IC");
        itemParams.put("abroadSalDlrAmt", "21321");
        itemParams.put("abroadSalYenAmt", "213");
        itemParams.put("abroadGdsHsCd", "2133424324");
        itemParams.put("loginUserId", "e6a7d5a2-fef3-11eb-9394-0a5b64398cfa");
        itemParams.put("safetyStock", "0");
        items.add(itemParams);

        params.put("items", items);

        return params;
    }

}