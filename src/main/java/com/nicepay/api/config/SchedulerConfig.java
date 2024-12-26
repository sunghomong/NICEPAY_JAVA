package com.nicepay.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Desc : 스케쥴러 동시 최대 10개 쓰레드 실행 가능
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-24
 */

@Configuration
public class SchedulerConfig {

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10); // 스레드 풀 크기
        scheduler.setThreadNamePrefix("CustomTaskScheduler-"); // 스레드 이름 프리픽스
        scheduler.setAwaitTerminationSeconds(60); // 애플리케이션 종료 시 최대 대기 시간
        scheduler.setWaitForTasksToCompleteOnShutdown(true); // 종료 시 작업 완료까지 대기
        scheduler.initialize();
        return scheduler;
    }

}
