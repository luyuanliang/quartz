/**
* MemberDO entity encapsulation table member record. 
* @author Eclipse Tools Generate.
* @Time 2016-10-06 22:54:19
* Copyright by LuYuanliang.
*/

package org.web.quartz.domain;

import java.util.Date;

public class MemberDO {

	// 主键
	private Integer memberId = null;
	// 会员名称
	private String memberName = null;
	// 角色
	private String role = null;
	// 密码
	private String passWord = null;
	// 处理原因
	private String reason = null;
	// 记录创建时间
	private Date inputDate = null;
	// 记录修改时间
	private Date updateDate = null;
	// 记录修改版本号
	private Integer updateVersion = null;
	// 是否逻辑删除
	private String isDelete = null;

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

	public void setRole(String role) {
		this.role=role;
	}
	public String getRole(){
		return role;
	}

	public void setPassWord(String passWord) {
		this.passWord=passWord;
	}
	public String getPassWord(){
		return passWord;
	}

	public void setReason(String reason) {
		this.reason=reason;
	}
	public String getReason(){
		return reason;
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


