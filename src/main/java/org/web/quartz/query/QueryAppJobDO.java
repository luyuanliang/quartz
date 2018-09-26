/**
* AppJobDO entity encapsulation table app_job record. 
* @author Eclipse Tools Generate.
* @Time 2016-10-05 19:42:09
* Copyright by LuYuanliang.
*/

package org.web.quartz.query;

import java.util.Date;
import java.util.List;

public class QueryAppJobDO extends QueryBase {

	private Integer appJobId = null;
	private List < Integer > appJobIdList = null;
	private String jobName = null;
	private List < String > jobNameList = null;
	private String indistinctJobName;
	private String jobGroup = null;
	private List < String > jobGroupList = null;
	private String uri = null;
	private String indistinctUri;
	private String indistinctDescription;
	private String triggerType = null;
	private List < String > triggerTypeList = null;
	private String requestWay = null;
	private List < String > requestWayList = null;
	private String status = null;
	private List < String > statusList = null;
	private Date lessThanStartTime;
	private Date equalAndLessThanStartTime;
	private Date moreThanStartTime;
	private Date equalAndMoreThanStartTime;
	private Date lessThanEndTime;
	private Date equalAndLessThanEndTime;
	private Date moreThanEndTime;
	private Date equalAndMoreThanEndTime;
	private String inputUser = null;
	private String updateUser = null;
	private Date lessThanInputDate;
	private Date equalAndLessThanInputDate;
	private Date moreThanInputDate;
	private Date equalAndMoreThanInputDate;
	private Date lessThanUpdateDate;
	private Date equalAndLessThanUpdateDate;
	private Date moreThanUpdateDate;
	private Date equalAndMoreThanUpdateDate;
	private Integer updateVersion = null;
	private String isDelete = null;

	public void setAppJobId(Integer appJobId) {
		this.appJobId=appJobId;
	}
	public Integer getAppJobId(){
		return appJobId;
	}

	public void setAppJobIdList ( List < Integer > appJobIdList ) {
		this.appJobIdList = appJobIdList;
	}
	public List < Integer >  getAppJobIdList(){
		return this.appJobIdList ;
	}

	public void setJobName(String jobName) {
		this.jobName=jobName;
	}
	public String getJobName(){
		return jobName;
	}

	public void setIndistinctJobName(String indistinctJobName) {
		this.indistinctJobName = indistinctJobName;
	}
	public String getIndistinctJobName(){
		return indistinctJobName;
	}

	public void setJobNameList ( List < String > jobNameList ) {
		this.jobNameList = jobNameList;
	}
	public List < String >  getJobNameList(){
		return this.jobNameList ;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup=jobGroup;
	}
	public String getJobGroup(){
		return jobGroup;
	}

	public void setUri(String uri) {
		this.uri=uri;
	}
	public String getUri(){
		return uri;
	}

	public void setIndistinctUri(String indistinctUri) {
		this.indistinctUri = indistinctUri;
	}
	public String getIndistinctUri(){
		return indistinctUri;
	}

	public String getIndistinctDescription() {
		return indistinctDescription;
	}
	public void setIndistinctDescription(String indistinctDescription) {
		this.indistinctDescription = indistinctDescription;
	}
	public void setTriggerType(String triggerType) {
		this.triggerType=triggerType;
	}
	public String getTriggerType(){
		return triggerType;
	}

	public void setTriggerTypeList ( List < String > triggerTypeList ) {
		this.triggerTypeList = triggerTypeList;
	}
	public List < String >  getTriggerTypeList(){
		return this.triggerTypeList ;
	}

	public void setRequestWay(String requestWay) {
		this.requestWay=requestWay;
	}
	public String getRequestWay(){
		return requestWay;
	}

	public void setRequestWayList ( List < String > requestWayList ) {
		this.requestWayList = requestWayList;
	}
	public List < String >  getRequestWayList(){
		return this.requestWayList ;
	}

	public void setStatus(String status) {
		this.status=status;
	}
	public String getStatus(){
		return status;
	}

	public void setStatusList ( List < String > statusList ) {
		this.statusList = statusList;
	}
	public List < String >  getStatusList(){
		return this.statusList ;
	}

	public void setLessThanStartTime(Date lessThanStartTime) {
		this.lessThanStartTime=lessThanStartTime;
	}
	public Date getLessThanStartTime(){
		return this.lessThanStartTime;
	}

	public void setEqualAndLessThanStartTime(Date equalAndLessThanStartTime) {
		this.equalAndLessThanStartTime=equalAndLessThanStartTime;
	}
	public Date getEqualAndLessThanStartTime(){
		return this.equalAndLessThanStartTime;
	}

	public void setMoreThanStartTime(Date moreThanstartTime) {
		this.moreThanStartTime=moreThanstartTime;
	}
	public Date getMoreThanStartTime(){
		return this.moreThanStartTime;
	}

	public void setEqualAndMoreThanStartTime(Date equalAndMoreThanStartTime) {
		this.equalAndMoreThanStartTime=equalAndMoreThanStartTime;
	}
	public Date getEqualAndMoreThanStartTime(){
		return this.equalAndMoreThanStartTime;
	}

	public void setLessThanEndTime(Date lessThanEndTime) {
		this.lessThanEndTime=lessThanEndTime;
	}
	public Date getLessThanEndTime(){
		return this.lessThanEndTime;
	}

	public void setEqualAndLessThanEndTime(Date equalAndLessThanEndTime) {
		this.equalAndLessThanEndTime=equalAndLessThanEndTime;
	}
	public Date getEqualAndLessThanEndTime(){
		return this.equalAndLessThanEndTime;
	}

	public void setMoreThanEndTime(Date moreThanendTime) {
		this.moreThanEndTime=moreThanendTime;
	}
	public Date getMoreThanEndTime(){
		return this.moreThanEndTime;
	}

	public void setEqualAndMoreThanEndTime(Date equalAndMoreThanEndTime) {
		this.equalAndMoreThanEndTime=equalAndMoreThanEndTime;
	}
	public Date getEqualAndMoreThanEndTime(){
		return this.equalAndMoreThanEndTime;
	}

	public void setInputUser(String inputUser) {
		this.inputUser=inputUser;
	}
	public String getInputUser(){
		return inputUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser=updateUser;
	}
	public String getUpdateUser(){
		return updateUser;
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

	public void setLessThanUpdateDate(Date lessThanUpdateDate) {
		this.lessThanUpdateDate=lessThanUpdateDate;
	}
	public Date getLessThanUpdateDate(){
		return this.lessThanUpdateDate;
	}

	public void setEqualAndLessThanUpdateDate(Date equalAndLessThanUpdateDate) {
		this.equalAndLessThanUpdateDate=equalAndLessThanUpdateDate;
	}
	public Date getEqualAndLessThanUpdateDate(){
		return this.equalAndLessThanUpdateDate;
	}

	public void setMoreThanUpdateDate(Date moreThanupdateDate) {
		this.moreThanUpdateDate=moreThanupdateDate;
	}
	public Date getMoreThanUpdateDate(){
		return this.moreThanUpdateDate;
	}

	public void setEqualAndMoreThanUpdateDate(Date equalAndMoreThanUpdateDate) {
		this.equalAndMoreThanUpdateDate=equalAndMoreThanUpdateDate;
	}
	public Date getEqualAndMoreThanUpdateDate(){
		return this.equalAndMoreThanUpdateDate;
	}

	public void setUpdateVersion(Integer updateVersion) {
		this.updateVersion=updateVersion;
	}
	public Integer getUpdateVersion(){
		return updateVersion;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete=isDelete;
	}
	public String getIsDelete(){
		return isDelete;
	}
	public List<String> getJobGroupList() {
		return jobGroupList;
	}
	public void setJobGroupList(List<String> jobGroupList) {
		this.jobGroupList = jobGroupList;
	}



}


