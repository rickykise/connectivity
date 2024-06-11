package ai.fassto.connectivity.dataaccess.scheduler.mapper;

import ai.fassto.connectivity.dataaccess.scheduler.dto.SchedulerDeleteDTO;
import ai.fassto.connectivity.dataaccess.scheduler.dto.SchedulerInsertDTO;
import ai.fassto.connectivity.dataaccess.scheduler.entity.SchedulerEntity;
import ai.fassto.connectivity.dataaccess.scheduler.entity.SchedulerInfoEntity;
import ai.fassto.connectivity.domain.scheduler.core.entity.Scheduler;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerId;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerInfo;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.enums.SchedulerStatus;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.enums.SchedulerType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SchedulerDataAccessMapper {

    public SchedulerInsertDTO schedulerToSchedulerInsertDTO(Scheduler scheduler) {
        return SchedulerInsertDTO.builder()
                .type(String.valueOf(scheduler.getType()))
                .status(String.valueOf(scheduler.getStatus()))
                .taskId(String.valueOf(scheduler.getId().getValue()))
                .meta(scheduler.getMeta())
                .email(scheduler.getEmail())
                .reservedAt(scheduler.getReservedAt())
                .build();
    }

    public SchedulerDeleteDTO schedulerToSchedulerDeleteDTO(Scheduler scheduler) {
        return SchedulerDeleteDTO.builder()
                .status(String.valueOf(scheduler.getStatus()))
                .taskId(String.valueOf(scheduler.getId().getValue()))
                .description(scheduler.getDescription())
                .build();
    }

    public List<Scheduler> schedulerEntityToScheduler(List<SchedulerEntity> entityList) {

        return entityList.stream().map(scheduler ->
                Scheduler.Builder.builder()
                        .taskId(new SchedulerId(UUID.fromString(scheduler.getTaskId())))
                        .type(SchedulerType.findBy(scheduler.getType()))
                        .status(SchedulerStatus.findBy(scheduler.getStatus()))
                        .build()
                ).toList();
    }

    public Optional<SchedulerInfo> schedulerInfoEntityToSchedulerInfo(SchedulerInfoEntity schedulerInfoEntityOrNull) {
        if (schedulerInfoEntityOrNull == null) {
            return Optional.empty();
        }

        return Optional.of(SchedulerInfo.builder()
                        .taskId(new SchedulerId(UUID.fromString(schedulerInfoEntityOrNull.getTaskId())))
                        .type(SchedulerType.findBy(schedulerInfoEntityOrNull.getType()))
                        .createdAt(schedulerInfoEntityOrNull.getCreatedAt())
                        .totalCount(schedulerInfoEntityOrNull.getTotalCount())
                        .registeredCount(schedulerInfoEntityOrNull.getRegisteredCount())
                        .inProgressCount(schedulerInfoEntityOrNull.getInProgressCount())
                        .build()
        );
    }

    public List<SchedulerInfo> schedulerInfoEntityListToSchedulerInfoList(List<SchedulerInfoEntity> entityList) {
        return entityList.stream().map(schedulerInfo ->
                SchedulerInfo.builder()
                        .taskId(new SchedulerId(UUID.fromString(schedulerInfo.getTaskId())))
                        .type(SchedulerType.findBy(schedulerInfo.getType()))
                        .createdAt(schedulerInfo.getCreatedAt())
                        .totalCount(schedulerInfo.getTotalCount())
                        .registeredCount(schedulerInfo.getRegisteredCount())
                        .inProgressCount(schedulerInfo.getInProgressCount())
                        .build()
        ).toList();
    }

}
