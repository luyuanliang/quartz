/**
* ResultHistoryDO entity encapsulation table result_history record. 
* @author Eclipse Tools Generate.
* @Time 2016-10-26 22:38:45
* Copyright by LuYuanliang.
*/

package org.web.quartz.domain;

import java.util.Date;

public class ResultHistoryDO {

	// 主键ID
	private Integer resultHistoryId = null;
	// 任务ID，见APP_JOB表主键
	private Integer jobId = null;
	// 报文
	private String body = null;
	// 成功失败标记
	private String success = null;
	// 记录创建时间
	private Date inputDate = null;
	// 是否逻辑删除
	private String isDelete = null;
	// 
	private String jobName = null;
	// 
	private String jobGroup = null;

	public void setResultHistoryId(Integer resultHistoryId) {
		this.resultHistoryId=resultHistoryId;
	}
	public Integer getResultHistoryId(){
		return resultHistoryId;
	}

	public void setJobId(Integer jobId) {
		this.jobId=jobId;
	}
	public Integer getJobId(){
		return jobId;
	}

	public void setBody(String body) {
		this.body=body;
	}
	public String getBody(){
		return body;
	}

	public void setSuccess(String success) {
		this.success=success;
	}
	public String getSuccess(){
		return success;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate=inputDate;
	}
	public Date getInputDate(){
		return inputDate;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete=isDelete;
	}
	public String getIsDelete(){
		return isDelete;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	
}


