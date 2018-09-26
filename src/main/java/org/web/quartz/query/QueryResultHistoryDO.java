/**
* ResultHistoryDO entity encapsulation table result_history record. 
* @author Eclipse Tools Generate.
* @Time 2016-10-26 22:38:45
* Copyright by LuYuanliang.
*/

package org.web.quartz.query;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.web.quartz.query.QueryBase;

import java.math.BigDecimal;

public class QueryResultHistoryDO extends QueryBase {

	private Integer resultHistoryId = null;
	private List < Integer > resultHistoryIdList = null;
	private Integer jobId = null;
	private List < Integer > jobIdList = null;
	private String success = null;
	private String jobGroup = null;
	private String indistinctJobName = null;
	private String jobName = null;
	private Date lessThanInputDate;
	private Date equalAndLessThanInputDate;
	private Date moreThanInputDate;
	private Date equalAndMoreThanInputDate;
	private String isDelete = null;

	public void setResultHistoryId(Integer resultHistoryId) {
		this.resultHistoryId=resultHistoryId;
	}
	public Integer getResultHistoryId(){
		return resultHistoryId;
	}

	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getIndistinctJobName() {
		return indistinctJobName;
	}
	public void setIndistinctJobName(String indistinctJobName) {
		this.indistinctJobName = indistinctJobName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public void setResultHistoryIdList ( List < Integer > resultHistoryIdList ) {
		this.resultHistoryIdList = resultHistoryIdList;
	}
	public List < Integer >  getResultHistoryIdList(){
		return this.resultHistoryIdList ;
	}

	public void setJobId(Integer jobId) {
		this.jobId=jobId;
	}
	public Integer getJobId(){
		return jobId;
	}

	public void setJobIdList ( List < Integer > jobIdList ) {
		this.jobIdList = jobIdList;
	}
	public List < Integer >  getJobIdList(){
		return this.jobIdList ;
	}

	public void setSuccess(String success) {
		this.success=success;
	}
	public String getSuccess(){
		return success;
	}

	public void setLessThanInputDate(Date lessThanInputDate) {
		this.lessThanInputDate=lessThanInputDate;
	}
	public Date getLessThanInputDate(){
		return this.lessThanInputDate;
	}

	public void setEqualAndLessThanInputDate(Date equalAndLessThanInputDate) {
		this.equalAndLessThanInputDate=equalAndLessThanInputDate;
	}
	public Date getEqualAndLessThanInputDate(){
		return this.equalAndLessThanInputDate;
	}

	public void setMoreThanInputDate(Date moreThaninputDate) {
		this.moreThanInputDate=moreThaninputDate;
	}
	public Date getMoreThanInputDate(){
		return this.moreThanInputDate;
	}

	public void setEqualAndMoreThanInputDate(Date equalAndMoreThanInputDate) {
		this.equalAndMoreThanInputDate=equalAndMoreThanInputDate;
	}
	public Date getEqualAndMoreThanInputDate(){
		return this.equalAndMoreThanInputDate;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete=isDelete;
	}
	public String getIsDelete(){
		return isDelete;
	}


}


