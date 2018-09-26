/**
* MemberGroupDO entity encapsulation table MEMBER_GROUP record. 
* @author Eclipse Tools Generate.
* @Time 2016-10-05 20:54:26
* Copyright by LuYuanliang.
*/

package org.web.quartz.domain;

import java.util.Date;

public class MemberGroupDO {

	// 主键
	private Integer memberGroupId = null;
	// 分组名称
	private String jobGroup = null;
	// 会员ID
	private Integer memberId = null;
	// 会员名称
	private String memberName = null;
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

	public void setMemberGroupId(Integer memberGroupId) {
		this.memberGroupId=memberGroupId;
	}
	public Integer getMemberGroupId(){
		return memberGroupId;
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

	public void setMemberName(String memberName) {
		this.memberName=memberName;
	}
	public String getMemberName(){
		return memberName;
	}


	public void setReason(String reason) {
		this.reason=reason;
	}
	public String getReason(){
		return reason;
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

	public void setInputDate(Date inputDate) {
		this.inputDate=inputDate;
	}
	public Date getInputDate(){
		return inputDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate=updateDate;
	}
	public Date getUpdateDate(){
		return updateDate;
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


