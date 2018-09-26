/**
 * AppJobDO entity encapsulation table app_job record. 
 * @author Eclipse Tools Generate.
 * @Time 2016-10-05 19:42:09
 * Copyright by LuYuanliang.
 */

package org.web.quartz.domain;

import java.util.Date;

public class AppJobDO {

	// 表主键
	private Integer appJobId = null;
	// JOB名称
	private String jobName = null;
	// JOB所属组
	private String jobGroup = null;
	// 请求地址
	private String uri = null;
	// 描述
	private String description = null;
	// KEY-VALUE键值对
	private String mapData = null;
	// 触发器类型
	private String triggerType = null;
	// 请求方式
	private String requestWay = null;
	// 状态
	private String status = null;
	// cron表达式
	private String cron = null;
	// 执行记录
	private Integer repeatCount = null;
	// 间隔时间，单位毫秒
	private Integer repeatInterval = null;
	// 任务开始时间
	private Date startTime = null;
	// 任务结束时间
	private Date endTime = null;
	// 注释处理原因
	private String reason = null;
	// 创建者
	private String inputUser = null;
	// 修改者
	private String updateUser = null;
	// 记录创建时间
	private Date inputDate = null;
	// 记录修改时间
	private Date updateDate = null;
	// 记录修改版本号
	private Integer updateVersion = null;
	// 是否逻辑删除
	private String isDelete = null;
	// 下次执行时间
	private Date nextFireTime = null;
	// 上次执行时间
	private Date prevFireTime = null;
	// 是否需要记录请求结果
	private String needLog = null;
	// 当出现异常时，回调查的URI
	private String noticeErrorUri = null;

	public String getNeedLog() {
		return needLog;
	}

	public void setNeedLog(String needLog) {
		this.needLog = needLog;
	}

	public String getNoticeErrorUri() {
		return noticeErrorUri;
	}

	public void setNoticeErrorUri(String noticeErrorUri) {
		this.noticeErrorUri = noticeErrorUri;
	}

	public void setAppJobId(Integer appJobId) {
		this.appJobId = appJobId;
	}

	public Integer getAppJobId() {
		return appJobId;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setMapData(String mapData) {
		this.mapData = mapData;
	}

	public String getMapData() {
		return mapData;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	public String getTriggerType() {
		return triggerType;
	}

	public void setRequestWay(String requestWay) {
		this.requestWay = requestWay;
	}

	public String getRequestWay() {
		return requestWay;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getCron() {
		return cron;
	}

	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}

	public Integer getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatInterval(Integer repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	public Integer getRepeatInterval() {
		return repeatInterval;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setInputUser(String inputUser) {
		this.inputUser = inputUser;
	}

	public String getInputUser() {
		return inputUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateVersion(Integer updateVersion) {
		this.updateVersion = updateVersion;
	}

	public Integer getUpdateVersion() {
		return updateVersion;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public Date getPrevFireTime() {
		return prevFireTime;
	}

	public void setPrevFireTime(Date prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

}
