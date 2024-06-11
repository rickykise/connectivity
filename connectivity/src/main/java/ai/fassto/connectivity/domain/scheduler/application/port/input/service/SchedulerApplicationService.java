package ai.fassto.connectivity.domain.scheduler.application.port.input.service;

import ai.fassto.connectivity.domain.scheduler.application.dto.*;

public interface SchedulerApplicationService {

    CreateSchedulerResponse create(CreateSchedulerRequest request);

    GetSchedulerResponse get(String taskId);

    SearchSchedulerResponse getList(String type);

    DeleteSchedulerResponse delete(String taskId, DeleteSchedulerRequest request);

}
