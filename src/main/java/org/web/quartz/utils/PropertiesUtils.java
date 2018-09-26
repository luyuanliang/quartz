package org.web.quartz.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class PropertiesUtils {

	private static Logger logger = Logger.getLogger(PropertiesUtils.class);

	public static String getValue(String key, String defaultValue) {
		return getString(key, defaultValue);
	}

	public static String getValue(String bundleName, String key, String defaultValue) {
		String value = defaultValue;
		try {
			value = getString(bundleName, key);
			if (StringUtils.isEmpty(value)) {
				return value;
			}
		} catch (Exception e) {
			logger.error("ERROR key is " + key);
			logger.error(Utils.getExceptionInfo(e));
		}
		return value;
	}

	public static Boolean getValue(String bundleName, String key, boolean defaultValue) {
		Boolean value = defaultValue;
		try {
			value = Boolean.valueOf(getString(bundleName, key));
		} catch (Exception e) {
			logger.error("ERROR key is " + key);
			logger.error(Utils.getExceptionInfo(e));
		}
		return value;
	}

	public static Long getValue(String bundleName, String key, Long defaultValue) {
		Long value = defaultValue;
		try {
			value = Long.valueOf(getString(bundleName, key));
		} catch (Exception e) {
			logger.error("ERROR key is " + key);
			logger.error(Utils.getExceptionInfo(e));
		}
		return value;
	}

	/**
	 * 读取指定路径下的资源文件
	 * 
	 * @param bundleName
	 *            properties文件路径
	 * @param key
	 * @return value
	 */
	public static String getString(String bundleName, String key) throws MissingResourceException {
		final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(bundleName, Locale.getDefault());
		return RESOURCE_BUNDLE.getString(key);
	}
}
