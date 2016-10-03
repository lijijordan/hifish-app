package com.hifish.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 16/7/17
 * Time: 上午12:14
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableAsync
public class SpringConfig {

    private final int corePoolSize = 10;
    private final int maxPoolSize = 200;
    private final int queueCapacity = 10;



    private Executor initExecutor(String namePrefix){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix(namePrefix);
        executor.initialize();
        return executor;
    }

    @Bean
    public Executor logExecutor(){
        return initExecutor("log-executor-");
    }

    @Bean
    public Executor messageExecutor(){
        return initExecutor("message-executor-");
    }


}
