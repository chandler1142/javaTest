package quartz.test01;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * Created by chandlerzhao on 2017/9/29.
 */
public class SimpleTriggerExample {

    public static void main(String[] args) throws SchedulerException {

        JobKey jobKey = new JobKey("dummyJobName", "group1");

        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("dummyJobName", "group1").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyJobName", "group1")
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(5)
                                .repeatForever()
                ).build();

//        Trigger trigger = TriggerBuilder
//                .newTrigger()
//                .withIdentity("dummyTriggerName", "group1")
//                .withSchedule(
//                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
//                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        //Listener attached to jobKey
        scheduler.getListenerManager().addJobListener(
                new HelloJobListener(), KeyMatcher.keyEquals(jobKey)
        );

        //Listener attached to group named "group 1" only.
        //scheduler.getListenerManager().addJobListener(
        //	new HelloJobListener(), GroupMatcher.jobGroupEquals("group1")
        //);

        scheduler.start();
        scheduler.scheduleJob(job, trigger);

    }
}
