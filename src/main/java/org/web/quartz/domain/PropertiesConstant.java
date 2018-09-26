package org.web.quartz.domain;

import org.web.quartz.utils.PropertiesUtils;

/**
 * @author yuanlianggege
 * 
 * Detail see security.properites.
 *
 */
public class PropertiesConstant {

	// 必配配置
	public static String LAND_URI = PropertiesUtils.getValue("config/application", "LAND_URI", "");


}
