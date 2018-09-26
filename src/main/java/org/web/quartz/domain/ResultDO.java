package org.web.quartz.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiajun.wei
 */
public class ResultDO<T> {

	protected String message;
	protected String code;

	private boolean success = true; // 执行是否成功
	private long totalCount = 0;
	private T module; // 实际的返回结果
	// 某些情况下，特殊 格外的参数返回
	private Map<String, Object> models = new HashMap<String, Object>(4);

	public ResultDO() {
	}


	public T getModule() {
		return module;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setModel(String key, Object model) {
		getModels().put(key, model);
	}

	public Map<String, Object> getModels() {
		return models;
	}

	public void setModule(T module) {
		this.module = module;
	}

	public boolean isSuccess() {
		return success;
	}

	public boolean isFailure() {
		return !success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isEmpty() {
		return !isNotEmpty();
	}

	public boolean isNotEmpty() {
		return isSuccess() && getModule() != null;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setModels(Map<String, Object> models) {
		this.models = models;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}


	public void setResultMessage(boolean bool, String code, String msg) {
		this.success = bool;
		this.setMessage(msg);
		this.setCode(code);
	}

}
