package ai.fassto.connectivity.domain.scheduler.application.mapper;

import ai.fassto.connectivity.domain.scheduler.application.dto.*;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.Center;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerId;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.enums.SchedulerStatus;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.enums.SchedulerType;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerInfo;
import ai.fassto.connectivity.domain.scheduler.core.entity.Scheduler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SchedulerDataMapper {

    public Scheduler toScheduler (CreateSchedulerRequest request) {
        return Scheduler.Builder.builder()
                .type(SchedulerType.findBy(request.getSchedulerType()))
                .reservedAt(request.getReservedAt())
                .build();
    }

    public CreateSchedulerResponse schedulerToCreateSchedulerResponse(Scheduler scheduler) {
        return new CreateSchedulerResponse(
                scheduler.getType(),
                scheduler.getId()
        );
    }

    public GetSchedulerResponse schedulerInfoToSchedulerResponse(SchedulerInfo schedulerInfo) {
        return new GetSchedulerResponse(
                schedulerInfo.getType(),
                schedulerInfo.getTaskId(),
                schedulerInfo.getCreatedAt(),
                schedulerInfo.getTotalCount(),
                schedulerInfo.getRegisteredCount(),
                schedulerInfo.getInProgressCount()
        );
    }

    public SearchSchedulerResponse schedulerListInfoToSchedulerResponse(List<SchedulerInfo> schedulerInfo) {
        return new SearchSchedulerResponse(schedulerInfo);
    }

    public DeleteSchedulerResponse schedulerToSchedulerDeleteResponse(Scheduler scheduler) {
        return new DeleteSchedulerResponse(
                scheduler.getType(),
                scheduler.getId()
        );
    }

    public Scheduler schedulerToInsertScheduler(CreateSchedulerRequest request, SchedulerStatus status, Center center, UUID taskId) {
        return Scheduler.Builder.builder()
                .taskId(new SchedulerId(taskId))
                .type(SchedulerType.findBy(request.getSchedulerType()))
                .status(status)
                .meta(center.getWhCd())
                .email(request.getEmail())
                .reservedAt(request.getReservedAt())
                .build();
    }

    public Scheduler schedulerToUpdateScheduler(Scheduler scheduler, String description, SchedulerStatus status) {
        return Scheduler.Builder.builder()
                .taskId(scheduler.getId())
                .description(description)
                .status(status)
                .build();
    }

}
