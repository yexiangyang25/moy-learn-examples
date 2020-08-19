package org.moy.spring.quartz;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class ScheduleJobController {


    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @PostMapping(value = "/job/new")
    public Date executeTask(JobVO vo) throws SchedulerException {
        // job类
        JobDetail jobDetail = JobBuilder.newJob(ScheduleTaskJob.class)
                .withIdentity(vo.getJobName())
                .build();

        // 触发器类
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(vo.getTriggerName())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(vo.getCronExpression()))
                .build();

        // 执行任务
        return schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
    }

    @GetMapping(value = "/job/get")
    public String getJobDetail(String jobName) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, jobName);
        JobDetail jobDetail = schedulerFactoryBean.getScheduler().getJobDetail(jobKey);
        return jobDetail.getJobClass().getName();
    }

    @GetMapping(value = "/job/list")
    public List<JobVO> getAllJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<JobVO> jobList = new ArrayList<>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                JobVO job = new JobVO();
                job.setJobName(jobKey.getName());
                job.setTriggerName("触发器Key:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setTriggerState(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

    @Resource
    JobDetail demoJob;

    @PostMapping(value = "/job/start")
    public Date start(JobVO vo) throws SchedulerException {

        List<? extends Trigger> triggersOfJob = schedulerFactoryBean.getScheduler().getTriggersOfJob(new JobKey(vo.getJobName()));
        if (null == triggersOfJob
                || triggersOfJob.isEmpty()) {

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(vo.getTriggerName())
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(vo.getCronExpression()))
                    .withDescription(vo.getTriggerName())
                    .build();

            return schedulerFactoryBean.getScheduler().scheduleJob(demoJob, trigger);
        }
        return new Date();
    }
}
