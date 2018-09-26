package org.web.quartz.dao;

import java.util.List;

import org.web.quartz.domain.MemberDO;
import org.web.quartz.query.QueryMemberDO;



public interface MemberDAO {

	public MemberDO queryMemberByMemberId(Integer memberId) ;

	public List< MemberDO >queryMemberList(QueryMemberDO queryMemberDO) ;

	public Integer countMemberList(QueryMemberDO queryMemberDO) ;

	public int insertMember(MemberDO memberDO) ;

	public int updateMemberByMemberId(MemberDO memberDO) ;

}

