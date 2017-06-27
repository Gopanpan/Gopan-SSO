package bing.Pan.sso.service;

import bing.Pan.sso.domain.entity.SysJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @crea : Created by intelliJ IDEA 16.1.3
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 2017/6/27 16:13
 * @desc :
 */

@Service
public class ScheduleService {

    @Autowired private SchedulerFactoryBean schedulerFactoryBean;




    public void addJob(SysJob sysJob) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(sysJob .getCronExpression()).withMisfireHandlingInstructionDoNothing();
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(sysJob.getName()).withSchedule(scheduleBuilder).build();
        Class clazz = Class.forName(sysJob.getClassName());
        JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(sysJob.getName()).build();
        scheduler.scheduleJob(jobDetail, trigger);

    }

    public void updateJob(SysJob sysJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(sysJob.getName());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(sysJob.getCronExpression()).withMisfireHandlingInstructionDoNothing();
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    public void pauseJob(SysJob sysJob) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(sysJob.getName());
        scheduler.pauseJob(jobKey);
    }

    public void resumeJob(SysJob sysJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(sysJob.getName());
        scheduler.resumeJob(jobKey);
    }

    public void deleteJob(SysJob sysJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(sysJob.getName());
        scheduler.deleteJob(jobKey);
    }

    public void runAJobNow(SysJob sysJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(sysJob.getName());
        scheduler.triggerJob(jobKey);
    }

    public void pauseJob(String jobName) throws Exception {
        SysJob job = new SysJob(jobName, false, null, null, null);
        this.pauseJob(job);
    }

    public Boolean isExistJob(String jobName) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        return !ObjectUtils.isEmpty(trigger);
    }

}
