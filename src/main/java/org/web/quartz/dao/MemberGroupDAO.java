package org.web.quartz.dao;

import java.util.List;

import org.web.quartz.domain.MemberGroupDO;
import org.web.quartz.query.QueryMemberGroupDO;



public interface MemberGroupDAO {

	public MemberGroupDO queryMemberGroupByMemberGroupId(Long memberGroupId) ;

	public List< MemberGroupDO >queryMemberGroupList(QueryMemberGroupDO queryMemberGroupDO) ;

	public Integer countMemberGroupList(QueryMemberGroupDO queryMemberGroupDO) ;

	public int insertMemberGroup(MemberGroupDO memberGroupDO) ;

	public int updateMemberGroupByMemberGroupId(MemberGroupDO memberGroupDO) ;

}

