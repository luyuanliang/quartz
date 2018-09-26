package org.web.quartz.utils;

import org.apache.commons.lang.StringUtils;
import org.web.quartz.domain.ViewResult;
import org.web.quartz.domain.ViewTypeEnum;
import org.web.quartz.query.ServiceException;

public class ViewHelper {

	public static ViewResult buildViewByServiceException(ServiceException e) {
		ViewResult result = new ViewResult();
		if (StringUtils.isNotBlank(e.getMessage())) {
			result.setMsg(e.getMessage());
		} else {
			result.setMsg(e.getDescription());
		}
		result.setResult(false);
		result.setType(ViewTypeEnum.error.name());
		return result;
	}
}
