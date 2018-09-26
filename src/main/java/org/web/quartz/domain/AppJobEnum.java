package org.web.quartz.domain;

public class AppJobEnum {

	public static enum TRIGGER {
		SIMPLE,CRON
	}
	
	public static enum STATUS {
		OPEN,PAUSE,COMPLETED
	}
	
	public static enum HTTP_WAY {
		GET,POST
	}
}
