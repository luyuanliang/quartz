package org.web.quartz.query;

import org.apache.commons.lang.StringUtils;
import org.web.quartz.domain.ResultMessageEnum;

/**
 * 封装业务逻辑异常
 * 
 * @author yufan
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// 错误码
	private String errorCode;
	// 结果描述，用于排查问题
	private String description;
	// 参数
	private String parameter;
	// 给终端用户的信息，不推荐使用
	private String message;

	@Deprecated
	public ServiceException(String errorCode, String description) {
		this.errorCode = errorCode;
		this.description = description;
	}

	public ServiceException(String errorCode, String description, String parameter) {
		this.errorCode = errorCode;
		this.description = description;
		this.parameter = parameter;
	}

	public ServiceException(ResultMessageEnum resultInfoEnum, String parameter) {
		this.errorCode = resultInfoEnum.getCode();
		this.description = resultInfoEnum.getMessage();
		this.parameter = parameter;
	}

	public ServiceException(ResultMessageEnum resultInfoEnum, String parameter, String message) {
		this.errorCode = resultInfoEnum.getCode();
		this.description = resultInfoEnum.getMessage();
		this.parameter = parameter;
		this.message = message;
	}

	public ServiceException(ResultMessageEnum resultInfoEnum, String description, String parameter, String message) {
		this.errorCode = resultInfoEnum.getCode();
		this.description = description;
		this.parameter = parameter;
		this.message = message;
	}

	public ServiceException(ResultMessageEnum resultInfoEnum) {
		this.errorCode = resultInfoEnum.getCode();
		this.description = resultInfoEnum.getMessage();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String buildMessage() {
		String message = "descripion is " + this.getDescription();
		if (StringUtils.isNotBlank(this.parameter)) {
			message = message + " and parameter is " + this.getParameter();
		}
		return message;
	}

}
