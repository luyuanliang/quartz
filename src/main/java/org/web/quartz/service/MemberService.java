/**
 * @Description MemberServiceImpl is generate by Tools. 
 * @author luyl
 * @Time  2016-10-06 22:32:29
 */

package org.web.quartz.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.web.quartz.dao.MemberDAO;
import org.web.quartz.domain.BaseDO;
import org.web.quartz.domain.BaseDO.DELETE;
import org.web.quartz.domain.MemberDO;
import org.web.quartz.query.QueryBase;
import org.web.quartz.query.QueryMemberDO;
import org.web.quartz.query.ServiceException;

@Service("memberService")
public class MemberService {

	private static Logger logger = Logger.getLogger(MemberService.class);

	@Resource
	private MemberDAO memberDAO;

	public MemberDO queryMemberByMemberId(Integer memberId) throws ServiceException {
		if (memberId == null) {
			throw new ServiceException("PARAM_IS_EMPTY", "Query is null.");
		}
		return memberDAO.queryMemberByMemberId(memberId);
	}

	public List<MemberDO> queryMemberList(QueryMemberDO queryMemberDO) throws ServiceException {

		if (queryMemberDO == null) {
			// TODO
		}
		if (queryMemberDO.getPage() == null) {
			queryMemberDO.setPage(QueryBase.FIRST_PAGE);
		}
		if (queryMemberDO.getPageSize() == null) {
			queryMemberDO.setPageSize(QueryBase.DEFAULT_PAGE_SIZE);
		}
		if (queryMemberDO.getPageSize() > QueryBase.MAX_PAGE_SIZE) {
			queryMemberDO.setPageSize(QueryBase.MAX_PAGE_SIZE);
		}

		return memberDAO.queryMemberList(queryMemberDO);
	}

	public MemberDO queryOneMember(QueryMemberDO queryMemberDO) throws ServiceException {
		queryMemberDO.setFirstRecord();
		List<MemberDO> list = queryMemberList(queryMemberDO);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public MemberDO insertMember(MemberDO memberDO) throws ServiceException {

		// check params first.
		Date current = new Date();
		memberDO.setUpdateDate(current);
		memberDO.setInputDate(current);
		memberDO.setUpdateVersion(BaseDO.ZERO);
		memberDO.setIsDelete(DELETE.N.name());
		memberDO.setRole("MEMBER");
		checkInsert(memberDO);

		// TODO add default value.

		memberDAO.insertMember(memberDO);
		return memberDO;
	}

	public Integer updateMemberByMemberId(MemberDO memberDO) throws ServiceException {
		// check params first.
		checkUpdate(memberDO);

		return memberDAO.updateMemberByMemberId(memberDO);
	}

	public Integer countMemberList(QueryMemberDO queryMemberDO) throws ServiceException {
		return memberDAO.countMemberList(queryMemberDO);
	}

	/**
	 * According to DB info. check attribute allow empty or not, and check
	 * attribute's length is over upper limit of length or not. and this method
	 * is generate by tools.
	 */
	@SuppressWarnings({ "deprecation" })
	private void checkInsert(MemberDO memberDO) throws ServiceException {
		if (memberDO == null) {
		} else if (StringUtils.isEmpty(memberDO.getMemberName())
				|| (StringUtils.isNotEmpty(memberDO.getMemberName()) && memberDO.getMemberName().length() > 255)) {
			throw new ServiceException("PARAM_IS_INVALID", "memberName is null or out of range, Upper limit of length is 255");
		} else if (StringUtils.isEmpty(memberDO.getPassWord()) || (StringUtils.isNotEmpty(memberDO.getPassWord()) && memberDO.getPassWord().length() > 255)) {
			throw new ServiceException("PARAM_IS_INVALID", "passWord is null or out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(memberDO.getReason()) && memberDO.getReason().length() > 255) {
			throw new ServiceException("PARAM_IS_INVALID", "reason is out of range, Upper limit of length is 255");
		} else if (memberDO.getUpdateVersion() == null || (memberDO.getUpdateVersion() != null && String.valueOf(memberDO.getUpdateVersion()).length() > 11)) {
			throw new ServiceException("PARAM_IS_INVALID", "updateVersion is null or out of range, Upper limit of length is 11");
		} else if (StringUtils.isEmpty(memberDO.getIsDelete()) || (StringUtils.isNotEmpty(memberDO.getIsDelete()) && memberDO.getIsDelete().length() > 255)) {
			throw new ServiceException("PARAM_IS_INVALID", "isDelete is null or out of range, Upper limit of length is 255");
		}
	}

	@SuppressWarnings({ "deprecation" })
	private void checkUpdate(MemberDO memberDO) throws ServiceException {
		if (memberDO.getMemberId() == null) {
			throw new ServiceException("UPDATE_ERROR", "memberId can't be null.");
		} else if (memberDO.getMemberId() != null && String.valueOf(memberDO.getMemberId()).length() > 11) {
			throw new ServiceException("UPDATE_ERROR", "memberId is out of range, Upper limit of length is 11");
		} else if (StringUtils.isNotEmpty(memberDO.getMemberName()) && memberDO.getMemberName().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "memberName is out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(memberDO.getPassWord()) && memberDO.getPassWord().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "passWord is out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(memberDO.getReason()) && memberDO.getReason().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "reason is out of range, Upper limit of length is 255");
		} else if (memberDO.getUpdateVersion() != null && String.valueOf(memberDO.getUpdateVersion()).length() > 11) {
			throw new ServiceException("UPDATE_ERROR", "updateVersion is out of range, Upper limit of length is 11");
		} else if (StringUtils.isNotEmpty(memberDO.getIsDelete()) && memberDO.getIsDelete().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "isDelete is out of range, Upper limit of length is 255");
		}
	}
}
