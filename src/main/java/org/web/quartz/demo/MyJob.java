package org.web.quartz.demo;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.web.quartz.biz.JobBiz;
import org.web.quartz.core.QuartzDispatcherServlet;

public class MyJob implements Job {

	public static int num = 0;

	private static Logger logger = Logger.getLogger(JobBiz.class);

	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("trigger begin. " + context.getTrigger().getJobKey().getName() + context.getTrigger().getJobKey().getGroup());
		JobBiz jobBiz = QuartzDispatcherServlet.getSpringContext().getBean("jobBiz", JobBiz.class);
		jobBiz.triggerJob(context);
		logger.info("trigger end. " + context.getTrigger().getJobKey().getName() + context.getTrigger().getJobKey().getGroup());
	}

}
