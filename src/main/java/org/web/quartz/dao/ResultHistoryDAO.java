package org.web.quartz.dao;

import java.util.List;
import org.web.quartz.query.QueryResultHistoryDO;
import org.web.quartz.domain.ResultHistoryDO;



public interface ResultHistoryDAO {

	public ResultHistoryDO queryResultHistoryByResultHistoryId(Integer resultHistoryId) ;

	public List< ResultHistoryDO >queryResultHistoryList(QueryResultHistoryDO queryResultHistoryDO) ;

	public Integer countResultHistoryList(QueryResultHistoryDO queryResultHistoryDO) ;

	public int insertResultHistory(ResultHistoryDO resultHistoryDO) ;

	public int updateResultHistoryByResultHistoryId(ResultHistoryDO resultHistoryDO) ;

}

