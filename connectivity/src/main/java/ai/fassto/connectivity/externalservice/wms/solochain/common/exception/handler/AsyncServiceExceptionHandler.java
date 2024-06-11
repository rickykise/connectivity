package ai.fassto.connectivity.externalservice.wms.solochain.common.exception.handler;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import static ai.fassto.connectivity.common.utility.MapperUtil.toJson;


@Slf4j
public class AsyncServiceExceptionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {

        log.error("Async Exception is occurred ! ");
        log.error("runnable = {}", toJson(runnable));
        log.error("executor = {}", toJson(executor));
    }
}