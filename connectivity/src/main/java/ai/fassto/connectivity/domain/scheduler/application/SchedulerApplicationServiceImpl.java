package ai.fassto.connectivity.domain.scheduler.application;

import ai.fassto.connectivity.domain.scheduler.application.dto.*;
import ai.fassto.connectivity.domain.scheduler.application.port.input.service.SchedulerApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulerApplicationServiceImpl implements SchedulerApplicationService {

    private final SchedulerCreateRequestHandler schedulerCreateRequestHandler;
    private final SchedulerGetRequestHandler schedulerGetRequestHandler;
    private final SchedulerDeleteRequestHandler schedulerDeleteRequestHandler;

    @Override
    public CreateSchedulerResponse create(CreateSchedulerRequest request) {
        return schedulerCreateRequestHandler.createScheduler(request);
    }

    @Override
    public GetSchedulerResponse get(String taskId) {
        return schedulerGetRequestHandler.getScheduler(taskId);
    }

    @Override
    public SearchSchedulerResponse getList(String type) {
        return schedulerGetRequestHandler.getSchedulerList(type);
    }

    @Override
    public DeleteSchedulerResponse delete(String taskId, DeleteSchedulerRequest request) {
        return schedulerDeleteRequestHandler.deleteScheduler(taskId, request);
    }

}
