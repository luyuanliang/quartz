package org.web.quartz.demo;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

public class Demo2 {

	public static void main(String[] args) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		CronTriggerImpl trigger = (CronTriggerImpl)CronScheduleBuilder.cronSchedule("*/5 * * * * ?").build();
		trigger.setCronExpression("*/5 * * * * ?");
		trigger.setName("aaa");
		trigger.setGroup("bbbb");
		JobDetail jobDetail = JobBuilder.newJob().withDescription("first job").withIdentity("cron33", "bbb").ofType(MyJob.class).build();
		jobDetail.getJobDataMap().put("av", "hello");
		//sched.scheduleJob(jobDetail, trigger);
		sched.start();
		//sched.deleteJob(JobKey.jobKey("aaa", "bbb"));
		String schedId = sched.getSchedulerInstanceId();
		System.out.println(schedId);
		System.out.println(System.currentTimeMillis());
	}

}
