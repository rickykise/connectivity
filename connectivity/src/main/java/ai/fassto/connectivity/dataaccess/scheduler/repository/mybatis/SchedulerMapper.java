package ai.fassto.connectivity.dataaccess.scheduler.repository.mybatis;

import ai.fassto.connectivity.dataaccess.scheduler.dto.SchedulerDeleteDTO;
import ai.fassto.connectivity.dataaccess.scheduler.dto.SchedulerInsertDTO;
import ai.fassto.connectivity.dataaccess.scheduler.entity.SchedulerEntity;
import ai.fassto.connectivity.dataaccess.scheduler.entity.SchedulerInfoEntity;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.Center;
import ai.fassto.connectivity.domain.scheduler.core.valueobject.SchedulerCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchedulerMapper {

    /************
     * 등록
     *************/
    int insertScheduler(SchedulerInsertDTO schedulerInsertDTO);

    /************
     * 조회
     *************/

    List<SchedulerEntity> getScheduler(String taskId);

    SchedulerInfoEntity getSchedulerInfo(String taskId);

    List<SchedulerInfoEntity> getSchedulerInfoList(String type);

    SchedulerCount getSchedulerCount(String type);

    List<Center> getCenterList();

    /************
     * 스케줄 취소
     *************/
    int deleteScheduler(SchedulerDeleteDTO schedulerDeleteDTO);

}
