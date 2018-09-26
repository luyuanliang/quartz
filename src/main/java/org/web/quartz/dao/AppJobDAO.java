package org.web.quartz.dao;

import java.util.List;

import org.web.quartz.domain.AppJobDO;
import org.web.quartz.query.QueryAppJobDO;



public interface AppJobDAO {

	public AppJobDO queryAppJobByAppJobId(Integer appJobId) ;

	public List< AppJobDO >queryAppJobList(QueryAppJobDO queryAppJobDO) ;

	public Integer countAppJobList(QueryAppJobDO queryAppJobDO) ;

	public int insertAppJob(AppJobDO appJobDO) ;

	public int updateAppJobByAppJobId(AppJobDO appJobDO) ;

}

