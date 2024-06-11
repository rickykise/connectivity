package ai.fassto.connectivity.application.rest.scheduler.v1;

import ai.fassto.connectivity.application.common.dto.GenericResponse;
import ai.fassto.connectivity.application.common.validation.ValidationSequence;
import ai.fassto.connectivity.domain.scheduler.application.dto.*;
import ai.fassto.connectivity.domain.scheduler.application.port.input.service.SchedulerApplicationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;
import static ai.fassto.connectivity.domain.scheduler.application.dto.CreateSchedulerResponse.getCreatedMessage;
import static ai.fassto.connectivity.domain.scheduler.application.dto.DeleteSchedulerResponse.getDeleteResponseMessage;
import static ai.fassto.connectivity.domain.scheduler.application.dto.GetSchedulerResponse.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/erp/v1")
@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class SchedulerApiController {

    private final SchedulerApplicationService service;

    @PostMapping("/scheduler")
    @ApiOperation(value = "Register daily stock schedule (일 배치 스케줄 등록)", notes = "Create daily stock schedule")
    public GenericResponse<CreateSchedulerResponse> createScheduler (
            @Validated(ValidationSequence.class)
            @RequestBody
            CreateSchedulerRequest request
    ) {
        log.info("POST /api/erp/v1/scheduler, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getCreatedMessage(),
                service.create(request)
        );
    }

    @GetMapping("/scheduler")
    @ApiOperation(value = "Get daily stock scheduler (일 배치 스케줄 조회)", notes = "Get daily stock scheduler")
    public GenericResponse<GetSchedulerResponse> getScheduler (
            @Validated(ValidationSequence.class)
            @RequestParam("taskId")
            String taskId
    ) {
        log.info("GET /api/erp/v1/scheduler, params: {}", toJson(taskId));

        return new GenericResponse<>(
                getGetResponseMessage(),
                service.get(taskId)
        );
    }

    @GetMapping("/schedulers")
    @ApiOperation(value = "Get daily stock scheduler list (일 배치 스케줄 리스트 조회)", notes = "Get daily stock scheduler list")
    public GenericResponse<SearchSchedulerResponse> getSchedulerList (
            @Validated(ValidationSequence.class)
            @RequestParam("type")
            String type
    ) {
        log.info("GET /api/erp/v1/schedulers, params: {}", toJson(type));

        return new GenericResponse<>(
                getGetResponseMessage(),
                service.getList(type)
        );
    }

    @DeleteMapping("/scheduler")
    @ApiOperation(value = "Delete daily stock scheduler (일 배치 스케줄 삭제)", notes = "Delete daily stock scheduler")
    public GenericResponse<DeleteSchedulerResponse> deleteScheduler (
            @Validated(ValidationSequence.class)
            @RequestParam("taskId")
            String taskId,
            @Validated(ValidationSequence.class)
            @RequestBody
            DeleteSchedulerRequest request
    ) {
        log.info("DELETE /api/erp/v1/scheduler, params: {}", toJson(request.validate()));

        return new GenericResponse<>(
                getDeleteResponseMessage(),
                service.delete(taskId, request)
        );
    }

}
