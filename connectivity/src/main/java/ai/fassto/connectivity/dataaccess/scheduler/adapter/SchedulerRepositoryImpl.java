package ai.fassto.connectivity.dataaccess.scheduler.adapter;

import ai.fassto.connectivity.dataaccess.scheduler.mapper.SchedulerDataAccessMapper;
import ai.fassto.connectivity.dataaccess.scheduler.repository.mybatis.SchedulerMapper;
import ai.fassto.connectivity.domain.scheduler.application.port.output.repository.SchedulerRepository;
import ai.fassto.connectivity.domain.scheduler.core.entity.Scheduler;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.Center;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerCount;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerRepositoryImpl implements SchedulerRepository {

    private final SchedulerMapper schedulerMapper;
    private final SchedulerDataAccessMapper schedulerDataAccessMapper;

    @Override
    public int insertScheduler(Scheduler scheduler) {
        return this.schedulerMapper.insertScheduler(
                schedulerDataAccessMapper.schedulerToSchedulerInsertDTO(scheduler)
        );
    }

    @Override
    public List<Scheduler> findSchedulerBy(String taskId) {
        return this.schedulerDataAccessMapper.schedulerEntityToScheduler(
                this.schedulerMapper.getScheduler(taskId)
        );
    }

    @Override
    public Optional<SchedulerInfo> findSchedulerInfoBy(String taskId) {
        return this.schedulerDataAccessMapper.schedulerInfoEntityToSchedulerInfo(
                this.schedulerMapper.getSchedulerInfo(taskId)
        );
    }

    @Override
    public List<SchedulerInfo> findSchedulerInfoListBy(String type) {
        return this.schedulerDataAccessMapper.schedulerInfoEntityListToSchedulerInfoList(
                this.schedulerMapper.getSchedulerInfoList(type)
        );
    }

    @Override
    public SchedulerCount findSchedulerCountBy(String type) {
        return this.schedulerMapper.getSchedulerCount(type);
    }

    @Override
    public List<Center> findCenter() {
        return this.schedulerMapper.getCenterList();
    }

    @Override
    public int deleteScheduler(Scheduler scheduler) {
        return this.schedulerMapper.deleteScheduler(
                this.schedulerDataAccessMapper.schedulerToSchedulerDeleteDTO(scheduler)
        );
    }

}
