package org.moy.spring.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleTaskJob implements Job {

    private final Logger LOGGER = LoggerFactory.getLogger(ScheduleTaskJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String key = jobExecutionContext.getMergedJobDataMap().getString("key");
        String key1 = jobExecutionContext.getTrigger().getDescription();
        LOGGER.info("getMergedJobDataMap:{} getJobDataMap:{}任务执行了......" , key , key1);
    }
}
