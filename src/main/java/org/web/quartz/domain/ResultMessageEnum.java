package org.web.quartz.domain;

import org.web.quartz.query.ServiceException;


/**
 * @author yufan
 * @see ServiceException
 */
public enum ResultMessageEnum {

	SUCCESS("SUCCESS","成功"),
	ERROR_RECORD_EXIST("ERRO_RECORD_EXIST", "记录已存在"),
	ERROR_COMPARE_TIME("ERROR_COMPARE_TIME", "时间日期不合法,结束时间不能早于开始时间"),
	ERROR_MORE_THAN_CURRENT("ERROR_MORE_THAN_CURRENT", "时间日期不合法,结束时间不能早于当前时间"),
	ERROR_SYSTEM_EXCEPTION("ERROR_SYSTEM_EXCEPTION", "系统异常"),
	ERROR_PARAM_INVALID("ERROR_PARAM_INVALID", "参数不合法"),
	ERROR_PATTERN_INVALID("ERROR_PARAM_INVALID", "格式不合法"),
	ERROR_PARAM_EMPTY("ERROR_PARAM_EMPTY", "参数为空"),
	ERROR_ACCESS_PRIVILEGE("ERROR_ACCESS_PRIVILEGE", "没有访问权限"),
	ERROR_RECORD_NOT_EXIST("ERROR_RECORD_NOT_EXIST", "记录不存在"),
	ERROR_RECORD_NOT_UPDATE("ERROR_RECORD_NOT_UPDATE", "记录不允许修改");
	
	
	private String code;
	private String message;

	private ResultMessageEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public static String getMessageByCode(String code) {
		for (ResultMessageEnum c : ResultMessageEnum.values()) {
			if (c.getCode().equals(code)) {
				return c.getMessage();
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
