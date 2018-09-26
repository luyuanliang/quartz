/**
 * @Description MemberGroupServiceImpl is generate by Tools. 
 * @author luyl
 * @Time  2016-10-05 20:41:25
 */

package org.web.quartz.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.web.quartz.dao.MemberGroupDAO;
import org.web.quartz.domain.BaseDO;
import org.web.quartz.domain.BaseDO.DELETE;
import org.web.quartz.domain.MemberGroupDO;
import org.web.quartz.domain.ResultMessageEnum;
import org.web.quartz.query.QueryBase;
import org.web.quartz.query.QueryMemberGroupDO;
import org.web.quartz.query.ServiceException;

@Service("memberGroupService")
public class MemberGroupService {

	private static Logger logger = Logger.getLogger(MemberGroupService.class);

	@Resource
	MemberGroupDAO memberGroupDAO;

	public MemberGroupDO queryMemberGroupByMemberGroupId(Long memberGroupId) throws ServiceException {
		if (memberGroupId == null) {
			throw new ServiceException("PARAM_IS_EMPTY", "Query is null.");
		}
		return memberGroupDAO.queryMemberGroupByMemberGroupId(memberGroupId);
	}

	public List<MemberGroupDO> queryMemberGroupList(QueryMemberGroupDO queryMemberGroupDO) throws ServiceException {

		if (queryMemberGroupDO == null) {
			// TODO
		}
		if (queryMemberGroupDO.getPage() == null) {
			queryMemberGroupDO.setPage(QueryBase.FIRST_PAGE);
		}
		if (queryMemberGroupDO.getPageSize() == null) {
			queryMemberGroupDO.setPageSize(QueryBase.DEFAULT_PAGE_SIZE);
		}
		if (queryMemberGroupDO.getPageSize() > QueryBase.MAX_PAGE_SIZE) {
			queryMemberGroupDO.setPageSize(QueryBase.MAX_PAGE_SIZE);
		}
		if(queryMemberGroupDO.getIsDelete()==null) {
			queryMemberGroupDO.setIsDelete(DELETE.N.name());
		}
		return memberGroupDAO.queryMemberGroupList(queryMemberGroupDO);
	}

	public MemberGroupDO queryOneMemberGroup(QueryMemberGroupDO queryMemberGroupDO) throws ServiceException {
		queryMemberGroupDO.setFirstRecord();
		List<MemberGroupDO> list = queryMemberGroupList(queryMemberGroupDO);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public MemberGroupDO insertMemberGroup(MemberGroupDO memberGroupDO) throws ServiceException {

		// add default value.
		Date current = new Date();
		memberGroupDO.setUpdateDate(current);
		memberGroupDO.setInputDate(current);
		memberGroupDO.setUpdateVersion(BaseDO.ZERO);
		memberGroupDO.setIsDelete(DELETE.N.name());
		
		// check params first.
		checkInsert(memberGroupDO);

		QueryMemberGroupDO query = new QueryMemberGroupDO();
		query.setMemberId(memberGroupDO.getMemberId());
		query.setJobGroup(memberGroupDO.getJobGroup());
		query.setIsDelete(DELETE.N.name());
		int num = memberGroupDAO.countMemberGroupList(query);
		if (num > 0) {
			throw new ServiceException(ResultMessageEnum.ERROR_RECORD_EXIST, null, memberGroupDO.getJobGroup() + "已经拥有");
		}
		query = new QueryMemberGroupDO();
		query.setJobGroup(memberGroupDO.getJobGroup());
		query.setIsDelete(DELETE.N.name());
		num = memberGroupDAO.countMemberGroupList(query);
		if (num > 0) {
			throw new ServiceException(ResultMessageEnum.ERROR_RECORD_EXIST, null, memberGroupDO.getJobGroup() + "已经被他人创建");
		}
		memberGroupDAO.insertMemberGroup(memberGroupDO);
		return memberGroupDO;
	}

	public Integer updateMemberGroupByMemberGroupId(MemberGroupDO memberGroupDO) throws ServiceException {
		// check params first.
		checkUpdate(memberGroupDO);

		return memberGroupDAO.updateMemberGroupByMemberGroupId(memberGroupDO);
	}

	public Integer countMemberGroupList(QueryMemberGroupDO queryMemberGroupDO) throws ServiceException {
		if(queryMemberGroupDO.getIsDelete()==null) {
			queryMemberGroupDO.setIsDelete(DELETE.N.name());
		}
		return memberGroupDAO.countMemberGroupList(queryMemberGroupDO);
	}

	/**
	 * According to DB info. check attribute allow empty or not, and check
	 * attribute's length is over upper limit of length or not. and this method
	 * is generate by tools.
	 */
	@SuppressWarnings({ "deprecation" })
	private void checkInsert(MemberGroupDO memberGroupDO) throws ServiceException {
		if (memberGroupDO == null) {
		} else if (StringUtils.isNotEmpty(memberGroupDO.getJobGroup()) && memberGroupDO.getJobGroup().length() > 255) {
			throw new ServiceException("PARAM_IS_INVALID", "jobGroup is out of range, Upper limit of length is 255");
		} else if (memberGroupDO.getMemberId() != null && String.valueOf(memberGroupDO.getMemberId()).length() > 11) {
			throw new ServiceException("PARAM_IS_INVALID", "memberId is out of range, Upper limit of length is 11");
		} else if (StringUtils.isEmpty(memberGroupDO.getMemberName())
				|| (StringUtils.isNotEmpty(memberGroupDO.getMemberName()) && memberGroupDO.getMemberName().length() > 255)) {
			throw new ServiceException("PARAM_IS_INVALID", "memberName is null or out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(memberGroupDO.getReason()) && memberGroupDO.getReason().length() > 255) {
			throw new ServiceException("PARAM_IS_INVALID", "reason is out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(memberGroupDO.getInputUser()) && memberGroupDO.getInputUser().length() > 255) {
			throw new ServiceException("PARAM_IS_INVALID", "inputUser is out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(memberGroupDO.getUpdateUser()) && memberGroupDO.getUpdateUser().length() > 255) {
			throw new ServiceException("PARAM_IS_INVALID", "updateUser is out of range, Upper limit of length is 255");
		} else if (memberGroupDO.getUpdateVersion() == null
				|| (memberGroupDO.getUpdateVersion() != null && String.valueOf(memberGroupDO.getUpdateVersion()).length() > 11)) {
			throw new ServiceException("PARAM_IS_INVALID", "updateVersion is null or out of range, Upper limit of length is 11");
		} else if (StringUtils.isEmpty(memberGroupDO.getIsDelete())
				|| (StringUtils.isNotEmpty(memberGroupDO.getIsDelete()) && memberGroupDO.getIsDelete().length() > 255)) {
			throw new ServiceException("PARAM_IS_INVALID", "isDelete is null or out of range, Upper limit of length is 255");
		}
	}

	@SuppressWarnings({ "deprecation" })
	private void checkUpdate(MemberGroupDO memberGroupDO) throws ServiceException {
		if (memberGroupDO.getMemberGroupId() == null) {
			throw new ServiceException("UPDATE_ERROR", "memberGroupId can't be null.");
		} else if (memberGroupDO.getMemberGroupId() != null && String.valueOf(memberGroupDO.getMemberGroupId()).length() > 11) {
			throw new ServiceException("UPDATE_ERROR", "memberGroupId is out of range, Upper limit of length is 11");
		} else if (StringUtils.isNotEmpty(memberGroupDO.getJobGroup()) && memberGroupDO.getJobGroup().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "jobGroup is out of range, Upper limit of length is 255");
		} else if (memberGroupDO.getMemberId() != null && String.valueOf(memberGroupDO.getMemberId()).length() > 11) {
			throw new ServiceException("UPDATE_ERROR", "memberId is out of range, Upper limit of length is 11");
		} else if (StringUtils.isNotEmpty(memberGroupDO.getMemberName()) && memberGroupDO.getMemberName().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "memberName is out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(memberGroupDO.getReason()) && memberGroupDO.getReason().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "reason is out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(memberGroupDO.getInputUser()) && memberGroupDO.getInputUser().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "inputUser is out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(memberGroupDO.getUpdateUser()) && memberGroupDO.getUpdateUser().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "updateUser is out of range, Upper limit of length is 255");
		} else if (memberGroupDO.getUpdateVersion() != null && String.valueOf(memberGroupDO.getUpdateVersion()).length() > 11) {
			throw new ServiceException("UPDATE_ERROR", "updateVersion is out of range, Upper limit of length is 11");
		} else if (StringUtils.isNotEmpty(memberGroupDO.getIsDelete()) && memberGroupDO.getIsDelete().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "isDelete is out of range, Upper limit of length is 255");
		}
	}
}
