/**
* MemberGroupDO entity encapsulation table MEMBER_GROUP record. 
* @author Eclipse Tools Generate.
* @Time 2016-10-05 20:54:26
* Copyright by LuYuanliang.
*/

package org.web.quartz.query;

import java.util.Date;
import java.util.List;

public class QueryMemberGroupDO extends QueryBase {

	private Integer memberGroupId = null;
	private List < Integer > memberGroupIdList = null;
	private String jobGroup = null;
	private Integer memberId = null;
	private List < Integer > memberIdList = null;
	private String memberName = null;
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

	public void setMemberGroupId(Integer memberGroupId) {
		this.memberGroupId=memberGroupId;
	}
	public Integer getMemberGroupId(){
		return memberGroupId;
	}

	public void setMemberGroupIdList ( List < Integer > memberGroupIdList ) {
		this.memberGroupIdList = memberGroupIdList;
	}
	public List < Integer >  getMemberGroupIdList(){
		return this.memberGroupIdList ;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup=jobGroup;
	}
	public String getJobGroup(){
		return jobGroup;
	}

	public void setMemberId(Integer memberId) {
		this.memberId=memberId;
	}
	public Integer getMemberId(){
		return memberId;
	}

	public void setMemberIdList ( List < Integer > memberIdList ) {
		this.memberIdList = memberIdList;
	}
	public List < Integer >  getMemberIdList(){
		return this.memberIdList ;
	}

	public void setMemberName(String memberName) {
		this.memberName=memberName;
	}
	public String getMemberName(){
		return memberName;
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


}


