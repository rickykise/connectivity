package ai.fassto.connectivity.application.rest.status;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.domain.status.application.dto.HealthResponse;
import ai.fassto.connectivity.domain.status.application.dto.MemoryResponse;
import ai.fassto.connectivity.domain.status.application.dto.RefreshConnectivityPropertyCacheRequest;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.dataaccess.common.ConnectivityPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/status")
@RequiredArgsConstructor
@Valid
public class StatusApiController {
    private final ConnectivityPropertyService propertyService;

    @GetMapping("/health")
    public GenericResponse<HealthResponse> health() {
        return new GenericResponse<>("서버 상태", new HealthResponse());
    }

    @GetMapping("/mem-info")
    public GenericResponse<MemoryResponse> memInfo() {
        return new GenericResponse<>("서버 메모리", new MemoryResponse());
    }

    @GetMapping("/refresh-all-caches")
    public GenericResponse<Object> refreshAllCaches() {
        propertyService.refreshAllLocalCaches();
        return new GenericResponse<>("모든 로컬 캐시가 초기화 되었습니다.");
    }

    @GetMapping("/refresh-connectivity-property-cache")
    public GenericResponse<Object> refreshCacheAtConnectivityProperty(
            @Validated(ValidationSequence.class) RefreshConnectivityPropertyCacheRequest request
    ) {
        propertyService.refreshCacheAtConnectivityProperty(request.getKey());
        return new GenericResponse<>("지정된 컨넥티비티 프로퍼티가 캐시에서 초기화 되었습니다.");
    }
}
