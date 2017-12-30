package org.daijie.quartz.cloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * 多线程执行定时任务
 * 所有的定时任务都放在一个线程池中，定时任务启动时使用不同都线程。
 * @author daijie
 * @date 2017年8月12日
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
	
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //设定一个长度10的定时任务线程池
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
    }
}
