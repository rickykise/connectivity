package ai.fassto.connectivity.domain.scheduler.application.port.output.repository;

import ai.fassto.connectivity.domain.scheduler.core.entity.Scheduler;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.Center;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerCount;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerInfo;

import java.util.List;
import java.util.Optional;

public interface SchedulerRepository {

    int insertScheduler(Scheduler scheduler);

    List<Scheduler> findSchedulerBy(String taskId);

    Optional<SchedulerInfo> findSchedulerInfoBy(String taskId);

    List<SchedulerInfo> findSchedulerInfoListBy(String type);

    SchedulerCount findSchedulerCountBy(String type);

    List<Center> findCenter();

    int deleteScheduler(Scheduler scheduler);

}
