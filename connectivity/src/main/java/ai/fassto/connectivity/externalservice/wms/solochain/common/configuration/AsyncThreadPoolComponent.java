package ai.fassto.connectivity.externalservice.wms.solochain.common.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.AsyncThreadPoolConfiguration.C100_FEIGN_EXECUTOR_1;

@Component
@Slf4j
public class AsyncThreadPoolComponent {
    private final TaskExecutor bulkTaskExecutors;
    public AsyncThreadPoolComponent(@Qualifier(C100_FEIGN_EXECUTOR_1) TaskExecutor bulkTaskExecutors) {
        this.bulkTaskExecutors = bulkTaskExecutors;
    }

    public TaskExecutor getTaskExecutor() {
        log.info("TaskExecutor Selected: {}", "bulkTaskExecutors");
        return bulkTaskExecutors;
    }
}
