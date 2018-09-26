/**
 * @Description ResultHistoryServiceImpl is generate by Tools. 
 * @author luyl
 * @Time  2016-10-26 22:38:45
 */

package org.web.quartz.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.web.quartz.dao.ResultHistoryDAO;
import org.web.quartz.domain.BaseDO.DELETE;
import org.web.quartz.domain.ResultHistoryDO;
import org.web.quartz.query.QueryBase;
import org.web.quartz.query.QueryResultHistoryDO;
import org.web.quartz.query.ServiceException;

@Service("resultHistoryService")
public class ResultHistoryService {

	private static Logger logger = Logger.getLogger(ResultHistoryService.class);

	@Resource
	private ResultHistoryDAO resultHistoryDAO;

	public ResultHistoryDO queryResultHistoryByResultHistoryId(Integer resultHistoryId) throws ServiceException {
		if (resultHistoryId == null) {
			throw new ServiceException("PARAM_IS_EMPTY", "Query is null.");
		}
		return resultHistoryDAO.queryResultHistoryByResultHistoryId(resultHistoryId);
	}

	public List<ResultHistoryDO> queryResultHistoryList(QueryResultHistoryDO queryResultHistoryDO) throws ServiceException {

		if (queryResultHistoryDO == null) {
			// TODO
		}
		if (queryResultHistoryDO.getPage() == null) {
			queryResultHistoryDO.setPage(QueryBase.FIRST_PAGE);
		}
		if (queryResultHistoryDO.getPageSize() == null) {
			queryResultHistoryDO.setPageSize(QueryBase.DEFAULT_PAGE_SIZE);
		}
		if (queryResultHistoryDO.getPageSize() > QueryBase.MAX_PAGE_SIZE) {
			queryResultHistoryDO.setPageSize(QueryBase.MAX_PAGE_SIZE);
		}

		return resultHistoryDAO.queryResultHistoryList(queryResultHistoryDO);
	}

	public ResultHistoryDO queryOneResultHistory(QueryResultHistoryDO queryResultHistoryDO) throws ServiceException {
		queryResultHistoryDO.setFirstRecord();
		List<ResultHistoryDO> list = queryResultHistoryList(queryResultHistoryDO);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public ResultHistoryDO insertResultHistory(ResultHistoryDO resultHistoryDO) throws ServiceException {

		resultHistoryDO.setIsDelete(DELETE.N.name());
		resultHistoryDO.setInputDate(new Date());
		// check params first.
		checkInsert(resultHistoryDO);

		// TODO add default value.

		resultHistoryDAO.insertResultHistory(resultHistoryDO);
		return resultHistoryDO;
	}

	public Integer updateResultHistoryByResultHistoryId(ResultHistoryDO resultHistoryDO) throws ServiceException {
		// check params first.
		checkUpdate(resultHistoryDO);

		return resultHistoryDAO.updateResultHistoryByResultHistoryId(resultHistoryDO);
	}

	public Integer countResultHistoryList(QueryResultHistoryDO queryResultHistoryDO) throws ServiceException {
		return resultHistoryDAO.countResultHistoryList(queryResultHistoryDO);
	}

	/**
	 * According to DB info. check attribute allow empty or not, and check
	 * attribute's length is over upper limit of length or not. and this method
	 * is generate by tools.
	 */
	@SuppressWarnings({ "deprecation" })
	private void checkInsert(ResultHistoryDO resultHistoryDO) throws ServiceException {
		if (resultHistoryDO == null) {
		} else if (resultHistoryDO.getJobId() == null || (resultHistoryDO.getJobId() != null && String.valueOf(resultHistoryDO.getJobId()).length() > 11)) {
			throw new ServiceException("PARAM_IS_INVALID", "jobId is null or out of range, Upper limit of length is 11");
		} else if (StringUtils.isNotEmpty(resultHistoryDO.getBody()) && resultHistoryDO.getBody().length() > 5000) {
			throw new ServiceException("PARAM_IS_INVALID", "body is null or out of range, Upper limit of length is 5000");
		} else if (StringUtils.isEmpty(resultHistoryDO.getSuccess())
				|| (StringUtils.isNotEmpty(resultHistoryDO.getSuccess()) && resultHistoryDO.getSuccess().length() > 15)) {
			throw new ServiceException("PARAM_IS_INVALID", "success is null or out of range, Upper limit of length is 1");
		} else if (StringUtils.isEmpty(resultHistoryDO.getIsDelete())
				|| (StringUtils.isNotEmpty(resultHistoryDO.getIsDelete()) && resultHistoryDO.getIsDelete().length() > 255)) {
			throw new ServiceException("PARAM_IS_INVALID", "isDelete is null or out of range, Upper limit of length is 255");
		}
	}

	@SuppressWarnings({ "deprecation" })
	private void checkUpdate(ResultHistoryDO resultHistoryDO) throws ServiceException {
		if (resultHistoryDO.getResultHistoryId() == null) {
			throw new ServiceException("UPDATE_ERROR", "resultHistoryId can't be null.");
		} else if (resultHistoryDO.getResultHistoryId() != null && String.valueOf(resultHistoryDO.getResultHistoryId()).length() > 11) {
			throw new ServiceException("UPDATE_ERROR", "resultHistoryId is out of range, Upper limit of length is 11");
		} else if (resultHistoryDO.getJobId() != null && String.valueOf(resultHistoryDO.getJobId()).length() > 11) {
			throw new ServiceException("UPDATE_ERROR", "jobId is out of range, Upper limit of length is 11");
		} else if (StringUtils.isNotEmpty(resultHistoryDO.getBody()) && resultHistoryDO.getBody().length() > 5000) {
			throw new ServiceException("UPDATE_ERROR", "body is out of range, Upper limit of length is 5000");
		} else if (StringUtils.isNotEmpty(resultHistoryDO.getSuccess()) && resultHistoryDO.getSuccess().length() > 1) {
			throw new ServiceException("UPDATE_ERROR", "success is out of range, Upper limit of length is 1");
		} else if (StringUtils.isNotEmpty(resultHistoryDO.getIsDelete()) && resultHistoryDO.getIsDelete().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "isDelete is out of range, Upper limit of length is 255");
		}
	}
}
