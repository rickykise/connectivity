package ai.fassto.connectivity.domain.scheduler.application;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.domain.scheduler.application.dto.CreateSchedulerRequest;
import ai.fassto.connectivity.domain.scheduler.application.dto.CreateSchedulerResponse;
import ai.fassto.connectivity.domain.scheduler.core.entity.Scheduler;
import ai.fassto.connectivity.domain.scheduler.core.exception.SchedulerUnableRegisterException;
import ai.fassto.connectivity.domain.scheduler.application.mapper.SchedulerDataMapper;
import ai.fassto.connectivity.domain.scheduler.application.port.output.repository.SchedulerRepository;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.Center;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerCount;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.enums.SchedulerStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerCreateRequestHandler {
    private final SchedulerDataMapper schedulerDataMapper;
    private final SchedulerRepository schedulerRepository;

    public CreateSchedulerResponse createScheduler(CreateSchedulerRequest request) {
        Scheduler scheduler = schedulerDataMapper.toScheduler(request);
        check(scheduler);

        UUID taskId = UUID.randomUUID();
        List<Center> centerList = schedulerRepository.findCenter();

        for (Center center : centerList) {
            scheduler = schedulerDataMapper.schedulerToInsertScheduler(
                    request, SchedulerStatus.REGISTERED, center, taskId
            );

            schedulerRepository.insertScheduler(scheduler);
        }

        return schedulerDataMapper.schedulerToCreateSchedulerResponse(scheduler);
    }

    private void check(Scheduler scheduler) {
        SchedulerCount count = schedulerRepository.findSchedulerCountBy(String.valueOf(scheduler.getType()));

        if (count.getCount() > 0) {
            throw new SchedulerUnableRegisterException(
                    new ErrorDetail("Unfinished Scheduler Quantity", String.valueOf(count.getCount()))
            );
        }
    }

}
