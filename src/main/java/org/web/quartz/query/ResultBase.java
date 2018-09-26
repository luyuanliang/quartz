package org.web.quartz.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * @author luyuanliang
 * */
public class ResultBase<T> extends BaseDO {

	public ResultBase() {
		super();
	}

	public ResultBase(Boolean bool) {
		success = bool;
	}

	public ResultBase(T module) {
		super();
		this.module = module;
	}

	/**
     *
     */
	private static final long serialVersionUID = -285470070917854580L;

	private boolean success = true; // 执行是否成功
	private int totalCount = 0;
	private T module; // 实际的返回结果
	private String message;
	// 某些情况下，特殊 格外的参数返回
	private Map<String, Object> models = new HashMap<String, Object>(4);

	private Map<String, Object> context; // 上下文参数,主要给error code使用
	private Set<String> errorCodes; // 错误消息代码
	/**
	 * 在Service请不要设置， 该对象用于在客户端加载错误信息
	 */
	private Map<String, String> errorMessages;

	public T getModule() {
		return module;
	}

	public void setModel(String key, Object model) {
		getModels().put(key, model);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	/**
	 * erorrCodes 参考 ErrorConstants
	 * 
	 * @return
	 */
	public Set<String> getErrorCodes() {
		if (null == errorCodes) {
			errorCodes = new HashSet<String>();
		}
		return errorCodes;
	}

	public void addError(String errorCode) {
		if (StringUtils.isBlank(errorCode)) {
			return;
		}
		if (!getErrorCodes().contains(errorCode)) {
			getErrorCodes().add(errorCode);
		}
		success = false; // 指示验证失败
	}

	public void setErrorMessage(String errorMessage) {
		success = false; // 指示验证失败
		setMessage(errorMessage);
	}

	public void addErrors(Collection<String> errors) {
		getErrorCodes().addAll(errors);
		success = false;
	}

	public Map<String, Object> getContext() {
		if (null == context) {
			context = new HashMap<String, Object>();
		}
		return context;
	}

	public void setContext(Map<String, Object> models) {
		this.context = models;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Map<String, String> getErrorMessages() {
		if (errorMessages == null) {
			errorMessages = new HashMap<String, String>();
		}
		return errorMessages;
	}

	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public List<String> getErrorMessagesList() {
		List<String> lis = new ArrayList<String>();
		lis.addAll(getErrorMessages().values());
		return lis;
	}

	public String getErrorMesssageString() {
		String s = getErrorMessagesList().toString();
		return s.substring(1, s.length() - 1);
	}

	public boolean getBooleanValue(String key) {
		Boolean flag = (Boolean) getModels().get(key);
		if (flag != null && flag) {
			return true;
		}
		return false;
	}
	
	public boolean isEmpty() {
		return !isNotEmpty();
	}

	public boolean isNotEmpty() {
		return isSuccess() && getModule() != null;
	}
}
