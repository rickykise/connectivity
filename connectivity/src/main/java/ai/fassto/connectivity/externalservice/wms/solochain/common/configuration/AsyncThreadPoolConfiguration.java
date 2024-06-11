package ai.fassto.connectivity.externalservice.wms.solochain.common.configuration;

import ai.fassto.connectivity.externalservice.wms.solochain.common.exception.handler.AsyncServiceExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncThreadPoolConfiguration {
    private static final int C1_CORE_POOL_SIZE = 1;
    private static final int C1_MAX_POOL_SIZE = 100;
    public static final String ASYNC_JOB_EXECUTOR = "c1AsyncJobExecutor";

    private static final int C100_CORE_POOL_SIZE = 100;
    private static final int C100_MAX_POOL_SIZE = 50000;
    public static final String C100_FEIGN_EXECUTOR_1 = "c100FeignExecutor1";

    @Bean(name = ASYNC_JOB_EXECUTOR)
    public TaskExecutor c1TaskExecutor1() {

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(C1_CORE_POOL_SIZE);
        taskExecutor.setRejectedExecutionHandler(new AsyncServiceExceptionHandler());
        taskExecutor.setMaxPoolSize(C1_MAX_POOL_SIZE);
        taskExecutor.afterPropertiesSet();
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean(name = C100_FEIGN_EXECUTOR_1)
    public TaskExecutor c100TaskExecutor1() {

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(C100_CORE_POOL_SIZE);
        taskExecutor.setRejectedExecutionHandler(new AsyncServiceExceptionHandler());
        taskExecutor.setMaxPoolSize(C100_MAX_POOL_SIZE);
        taskExecutor.afterPropertiesSet();
        taskExecutor.initialize();
        return taskExecutor;
    }
}
