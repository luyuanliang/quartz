package org.web.quartz.demo;

import org.quartz.CronScheduleBuilder;

public class Demo {

	public static void main(String[] args) {

		CronScheduleBuilder.cronSchedule("fff").build();
		
//		SchedulerFactory sf = new StdSchedulerFactory();
//		Scheduler sched = sf.getScheduler();
//	
//		SimpleTriggerImpl trigger = (SimpleTriggerImpl)TriggerBuilder.newTrigger().withDescription("first test.").withIdentity("tf345", "party").build();
//		trigger.setRepeatCount(30);
//		trigger.setRepeatInterval(2000L);
//		JobDetail jobDetail = JobBuilder.newJob().withDescription("first job").withIdentity("fffff", "bbb").ofType(MyJob.class).storeDurably().build();
//		jobDetail.isDurable();
//		jobDetail.getJobDataMap().put("av", "hello");
//		Date date = new Date();
//		//trigger.setStartTime(date);
//		trigger.setStartTime(new Date(date.getTime()+10000));
//		sched.scheduleJob(jobDetail, trigger);
//		sched.start();
//		JobDetail jobDetail2 = sched.getJobDetail(JobKey.jobKey("aaa", "bbb"));
//		JobDetail jobDetail3 = sched.getJobDetail(JobKey.jobKey("aaa", "bbb"));
//		//sched.deleteJob(JobKey.jobKey("aaa", "bbb"));
//		String schedId = sched.getSchedulerInstanceId();
//		System.out.println(schedId);
//		System.out.println(System.currentTimeMillis());
//	
	}

}
