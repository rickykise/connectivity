package ai.fassto.connectivity.application.rest;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StatusApiControllerTest {
    public static final String API_HEALTH = "/api/status/health";
    public static final String API_MEM_INFO = "/api/status/mem-info";
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Health API 호출 - 서버 상태 정상일 때 - OK 응답 확인")
    void getHealth_whenServerStatusIsValid_receiveOk() {
        // given
        // when
        ResponseEntity<Object> response = testRestTemplate.getForEntity(API_HEALTH, Object.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Health API 호출 - 서버 상태 정상일 때 - 응답 메시지 확인")
    void getHealth_whenServerStatusIsValid_receiveMessage() {
        // given
        // when
        ResponseEntity<Object> response = testRestTemplate.getForEntity(API_HEALTH, Object.class);
        Map<String, Object> body = (Map<String, Object>) response.getBody();

        // then
        assertThat(body.get("message")).isNotNull();
    }

    @Test
    @DisplayName("Health API 요청 - 서버 상태 정상일 때 - 프로젝트명, 버전, 시간정보 확인")
    void getHealth_serverStatusIsValid_projectNameVersionTimestampAreExist() {
        // given
        // when
        ResponseEntity<GenericResponse> response = testRestTemplate.getForEntity(API_HEALTH, GenericResponse.class);
        Map<String, String> data = (Map<String, String>) (response.getBody().getData());

        // then

        assertThat(data.get("name")).isNotBlank();
        assertThat(data.get("version")).isNotBlank();
        assertThat(data.get("timestamp")).isNotBlank();
    }

    @Test
    @DisplayName("mem-info API 호출 - 서버 상태 정상일 때 - OK 응답 확인")
    void getMemInfo_whenServerStatusIsValid_receiveOk() {
        // given
        // when
        ResponseEntity<Object> response = testRestTemplate.getForEntity(API_MEM_INFO, Object.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("mem-info API 호출 - 서버 상태 정상일 때 - 응답 메시지 확인")
    void getMemInfo_whenServerStatusIsValid_receiveMessage() {
        // given
        // when
        ResponseEntity<Object> response = testRestTemplate.getForEntity(API_MEM_INFO, Object.class);
        Map<String, Object> body = (Map<String, Object>) response.getBody();

        // then
        assertThat(body.get("message")).isNotNull();
    }

    @Test
    @DisplayName("mem-info API 요청 - 서버 상태 정상일 때 - max, free, total Memory 확인")
    void getMemInfo_serverStatusIsValid_projectNameVersionTimestampAreExist() {
        ResponseEntity<GenericResponse> response = testRestTemplate.getForEntity(API_MEM_INFO, GenericResponse.class);
        Map<String, Integer> data = (Map<String, Integer>) (response.getBody().getData());

        assertThat(data.get("maxMemory")).isNotNull();
        assertThat(data.get("freeMemory")).isNotNull();
        assertThat(data.get("totalMemory")).isNotNull();
    }
}