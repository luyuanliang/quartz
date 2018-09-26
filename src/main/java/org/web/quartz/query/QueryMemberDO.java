/**
* MemberDO entity encapsulation table member record. 
* @author Eclipse Tools Generate.
* @Time 2016-10-06 22:54:19
* Copyright by LuYuanliang.
*/

package org.web.quartz.query;

import java.util.Date;
import java.util.List;

public class QueryMemberDO extends QueryBase {

	private Integer memberId = null;
	private List < Integer > memberIdList = null;
	private String memberName = null;
	private String indistinctMemberName;
	private String role = null;
	private List < String > roleList = null;
	private String passWord = null;
	private String reason = null;
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

	public void setIndistinctMemberName(String indistinctMemberName) {
		this.indistinctMemberName = indistinctMemberName;
	}
	public String getIndistinctMemberName(){
		return indistinctMemberName;
	}

	public void setRole(String role) {
		this.role=role;
	}
	public String getRole(){
		return role;
	}

	public void setRoleList ( List < String > roleList ) {
		this.roleList = roleList;
	}
	public List < String >  getRoleList(){
		return this.roleList ;
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


