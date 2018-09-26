package org.web.quartz.core;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.web.quartz.utils.Utils;

public class QuartzDispatcherServlet extends DispatcherServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static WebApplicationContext context = null;

	public static final WebApplicationContext getSpringContext() {
		return context;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		context = getWebApplicationContext();
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			logger.error("init error : "+Utils.getExceptionInfo(e));
			throw new ServletException(e);
		}
	}
}
