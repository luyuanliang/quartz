package org.web.quartz.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CheckLandingInterceptor extends AbstractHandlerInterceptor {

	private List<String> ignoreChcekList = new ArrayList<String>();

	private List<String> ignorePatternList = new ArrayList<String>();

	public List<String> getIgnorePatternList() {
		return ignorePatternList;
	}

	public void setIgnorePatternList(List<String> ignorePatternList) {
		this.ignorePatternList = ignorePatternList;
	}

	public List<String> getIgnoreChcekList() {
		return ignoreChcekList;
	}

	public void setIgnoreChcekList(List<String> ignoreChcekList) {
		this.ignoreChcekList = ignoreChcekList;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String uri = request.getRequestURI();
		if (getIgnoreChcekList() != null && getIgnoreChcekList().contains(uri)) {
			return true;
		} else {
			if (getIgnorePatternList() != null) {
				for (String pattern : getIgnorePatternList()) {
					if (uri.contains(pattern)) {
						return true;
					}
				}
			}
			try {
				if (getMvcInterceptorInterface() != null) {
					return getMvcInterceptorInterface().execute(request, response);
				} else {
					return isLand(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

	}

	public abstract boolean isLand(HttpServletRequest request, HttpServletResponse response);
}
