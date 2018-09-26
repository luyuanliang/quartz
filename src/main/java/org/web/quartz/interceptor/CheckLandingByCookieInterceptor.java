package org.web.quartz.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web.quartz.domain.MemberDO;
import org.web.quartz.domain.PropertiesConstant;
import org.web.quartz.utils.Utils;
import org.web.quartz.view.Land;

public class CheckLandingByCookieInterceptor extends CheckLandingInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(Land.class);

	public static final List<String> IgnoreList = new ArrayList<String>();
	public static final String SEPERAT = "&@SEPERAT@&";

	static {

	}

	public static final String COOKIE_KEY = "land";

	@Override
	public boolean isLand(HttpServletRequest request, HttpServletResponse response) {
		boolean flag = false;
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (COOKIE_KEY.equals(cookie.getName())) {
					flag = true;
					break;
				}
			}
		}
		if (!flag) {
			try {
				response.sendRedirect(PropertiesConstant.LAND_URI);
			} catch (IOException e) {
				logger.error(Utils.getExceptionInfo(e));
			}
		}
		return flag;
	}

	public static MemberDO getMemberDOFromCookie(HttpServletRequest request) {
		MemberDO memberDO = null;
		for (Cookie cookie : request.getCookies()) {
			if (COOKIE_KEY.equals(cookie.getName())) {
				memberDO = new MemberDO();
				String[] array = cookie.getValue().split(SEPERAT);
				memberDO.setMemberId(Integer.valueOf(array[0]));
				memberDO.setMemberName(array[1]);
			}
			logger.info(cookie.getName() + " = " + cookie.getValue());
		}
		return memberDO;
	}
}
