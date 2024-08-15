package com.baimi.init.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@EnableScheduling
@Slf4j
@Data
@ConfigurationProperties(prefix = "thread.config")
public class ThreadPoolConfig implements AsyncConfigurer, SchedulingConfigurer {

    private int coreSize;

    private int maxPendingTasks;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private String poolName;

    // 暴露底层线程池, 监控状态
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public Executor getAsyncExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setThreadNamePrefix(poolName);
        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(maxPendingTasks);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //队列满了,交给原线程执行

        executor.initialize(); // 初始化
        threadPoolExecutor = executor.getThreadPoolExecutor();
        log.error("自定义线程池生效");
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> log.error("异常： {}, 异常信息： {}", throwable.getStackTrace(), throwable.getMessage());
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    }


    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(8);
        scheduler.setThreadNamePrefix("drwash_scheduled");
        scheduler.setErrorHandler(Throwable::printStackTrace);
        scheduler.initialize();

        return scheduler.getScheduledExecutor();
    }
}
