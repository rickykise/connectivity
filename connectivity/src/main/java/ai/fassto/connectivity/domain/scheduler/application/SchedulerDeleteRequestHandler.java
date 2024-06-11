package ai.fassto.connectivity.domain.scheduler.application;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.domain.scheduler.application.dto.DeleteSchedulerRequest;
import ai.fassto.connectivity.domain.scheduler.application.dto.DeleteSchedulerResponse;
import ai.fassto.connectivity.domain.scheduler.core.entity.Scheduler;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.enums.SchedulerStatus;
import ai.fassto.connectivity.domain.scheduler.application.mapper.SchedulerDataMapper;
import ai.fassto.connectivity.domain.scheduler.application.port.output.repository.SchedulerRepository;
import ai.fassto.connectivity.domain.scheduler.core.exception.SchedulerNotFoundException;
import ai.fassto.connectivity.domain.scheduler.core.exception.SchedulerStatusNotRegisterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerDeleteRequestHandler {

    private final SchedulerDataMapper schedulerDataMapper;
    private final SchedulerRepository schedulerRepository;

    public DeleteSchedulerResponse deleteScheduler(String taskId, DeleteSchedulerRequest request) {
        List<Scheduler> schedulerList = schedulerRepository.findSchedulerBy(taskId);
        check(taskId, schedulerList);

        for (Scheduler scheduler : schedulerList) {
            scheduler = schedulerDataMapper.schedulerToUpdateScheduler(scheduler, request.getDescription(), SchedulerStatus.CANCELLED);
            schedulerRepository.deleteScheduler(scheduler);
        }

        return schedulerDataMapper.schedulerToSchedulerDeleteResponse(schedulerList.get(0));
    }

    private void check(String taskId, List<Scheduler> schedulerList) {
        if (schedulerList.isEmpty()) {
            throw new SchedulerNotFoundException(new ErrorDetail("TaskId", taskId));
        }

        for (Scheduler scheduler : schedulerList) {
            if (!SchedulerStatus.REGISTERED.equals(scheduler.getStatus())) {
                throw new SchedulerStatusNotRegisterException(
                        new ErrorDetail("SchedulerStatus", String.valueOf(scheduler.getStatus()))
                );
            }
        }
    }

}
