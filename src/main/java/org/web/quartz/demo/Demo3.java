package org.web.quartz.demo;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

public class Demo3 {

	public static void main(String[] args) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
	
		SimpleTriggerImpl trigger = (SimpleTriggerImpl)TriggerBuilder.newTrigger().withDescription("first test.").withIdentity("tf345", "party").build();
		trigger.setRepeatCount(50);
		trigger.setRepeatInterval(2000L);
		JobDetail jobDetail = JobBuilder.newJob().withDescription("first job").withIdentity("fffff", "bbb").ofType(MyJob.class).build();
	
		jobDetail.getJobDataMap().put("av", "hello");
		Date date = new Date();
		//trigger.setStartTime(date);
		trigger.setEndTime(date);
		sched.scheduleJob(jobDetail, trigger);
		sched.start();
		JobDetail jobDetail2 = sched.getJobDetail(JobKey.jobKey("aaa", "bbb"));
		JobDetail jobDetail3 = sched.getJobDetail(JobKey.jobKey("aaa", "bbb"));
		//sched.deleteJob(JobKey.jobKey("aaa", "bbb"));
		String schedId = sched.getSchedulerInstanceId();
		System.out.println(schedId);
		System.out.println(System.currentTimeMillis());
	}

}
