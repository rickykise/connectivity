package ai.fassto.connectivity.domain.scheduler.application;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.domain.scheduler.application.dto.GetSchedulerResponse;
import ai.fassto.connectivity.domain.scheduler.application.dto.SearchSchedulerResponse;
import ai.fassto.connectivity.domain.scheduler.application.mapper.SchedulerDataMapper;
import ai.fassto.connectivity.domain.scheduler.application.port.output.repository.SchedulerRepository;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerInfo;
import ai.fassto.connectivity.domain.scheduler.core.exception.SchedulerNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerGetRequestHandler {

    private final SchedulerDataMapper schedulerDataMapper;
    private final SchedulerRepository schedulerRepository;

    public GetSchedulerResponse getScheduler(String taskId) {
        SchedulerInfo schedulerInfo = schedulerRepository.findSchedulerInfoBy(taskId)
                        .orElseThrow(() -> new SchedulerNotFoundException(new ErrorDetail("TaskId", taskId))
        );

        return schedulerDataMapper.schedulerInfoToSchedulerResponse(schedulerInfo);
    }

    public SearchSchedulerResponse getSchedulerList(String type) {
        List<SchedulerInfo> schedulerInfoList = schedulerRepository.findSchedulerInfoListBy(type);

        return schedulerDataMapper.schedulerListInfoToSchedulerResponse(schedulerInfoList);
    }

}
