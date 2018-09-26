package org.web.quartz.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MvcInterceptorInterface {

	public boolean execute(HttpServletRequest request, HttpServletResponse response);
	
}
