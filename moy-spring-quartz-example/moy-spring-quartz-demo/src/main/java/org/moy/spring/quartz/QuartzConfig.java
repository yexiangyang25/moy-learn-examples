package org.moy.spring.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail demoJob() {
        String name = ScheduleTaskJob.class.getName();
        JobDetail demoJob = JobBuilder.newJob(ScheduleTaskJob.class)
                .withIdentity(name, name)
                .build();
        return demoJob;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //覆盖已存在的任务
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        //延时5秒启动定时任务，避免系统未完全启动却开始执行定时任务的情况
        schedulerFactoryBean.setStartupDelay(5);
        return schedulerFactoryBean;
    }
}