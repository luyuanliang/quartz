/**
 * @Description AppJobServiceImpl is generate by Tools. 
 * @author luyl
 * @Time  2016-10-01 14:41:42
 */

package org.web.quartz.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.AbstractTrigger;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.stereotype.Service;
import org.web.quartz.dao.AppJobDAO;
import org.web.quartz.demo.MyJob;
import org.web.quartz.domain.AppJobDO;
import org.web.quartz.domain.AppJobEnum;
import org.web.quartz.domain.AppJobEnum.HTTP_WAY;
import org.web.quartz.domain.AppJobEnum.STATUS;
import org.web.quartz.domain.AppJobEnum.TRIGGER;
import org.web.quartz.domain.BaseDO;
import org.web.quartz.domain.BaseDO.DELETE;
import org.web.quartz.domain.ResultMessageEnum;
import org.web.quartz.query.QueryAppJobDO;
import org.web.quartz.query.QueryBase;
import org.web.quartz.query.ServiceException;
import org.web.quartz.utils.EnumHelper;
import org.web.quartz.utils.Utils;

import com.google.gson.Gson;

@Service("appJobService")
public class AppJobService {

	private static Logger logger = Logger.getLogger(AppJobService.class);

	@Resource
	private AppJobDAO appJobDAO;

	public AppJobDO queryAppJobByAppJobId(Integer appJobId) throws ServiceException {
		if (appJobId == null) {

		}
		return appJobDAO.queryAppJobByAppJobId(appJobId);
	}

	public List<AppJobDO> queryAppJobList(QueryAppJobDO queryAppJobDO) throws ServiceException {

		if (queryAppJobDO == null) {
			throw new ServiceException("UPDATE_ERROR", "appJobId can't be null.");
		}
		if (queryAppJobDO.getPage() == null) {
			queryAppJobDO.setPage(QueryBase.FIRST_PAGE);
		}
		if (queryAppJobDO.getPageSize() == null) {
			queryAppJobDO.setPageSize(QueryBase.DEFAULT_PAGE_SIZE);
		}
		if (queryAppJobDO.getPageSize() > QueryBase.MAX_PAGE_SIZE) {
			queryAppJobDO.setPageSize(QueryBase.MAX_PAGE_SIZE);
		}

		return appJobDAO.queryAppJobList(queryAppJobDO);
	}

	public AppJobDO queryOneAppJob(QueryAppJobDO queryAppJobDO) throws ServiceException {
		queryAppJobDO.setFirstRecord();
		List<AppJobDO> list = queryAppJobList(queryAppJobDO);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public AppJobDO insertAppJob(AppJobDO appJobDO) throws ServiceException {

		// check params first.
		checkInsert(appJobDO);

		// 校验name和组是否存在.
		QueryAppJobDO query = new QueryAppJobDO();
		query.setJobGroup(appJobDO.getJobGroup());
		query.setJobName(appJobDO.getJobName());
		query.setIsDelete(DELETE.N.name());
		AppJobDO queryResult = queryOneAppJob(query);
		if (queryResult != null) {
			try {
				Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
				if (!sched.checkExists(JobKey.jobKey(appJobDO.getJobName(), appJobDO.getJobGroup()))
						&& !STATUS.COMPLETED.name().equalsIgnoreCase(appJobDO.getStatus())) {
					// 逻辑删除
					AppJobDO updateAppJobDO = new AppJobDO();
					updateAppJobDO.setAppJobId(queryResult.getAppJobId());
					updateAppJobDO.setStatus(STATUS.COMPLETED.name());
					// updateAppJobDO.setIsDelete(DELETE.Y.name());
					updateAppJobByAppJobId(updateAppJobDO);
				}
			} catch (Exception e) {
				logger.error("AABBCC " + Utils.getExceptionInfo(e));
			}
			throw new ServiceException(ResultMessageEnum.ERROR_RECORD_EXIST, appJobDO.getJobGroup() + appJobDO.getJobName(), "JobName和JobGroup已存在.");
		}

		// 校验Schedule
		Scheduler sched = null;
		try {
			sched = StdSchedulerFactory.getDefaultScheduler();
			boolean exist = sched.checkExists(JobKey.jobKey(appJobDO.getJobName(), appJobDO.getJobGroup()));
			if (exist) {
				sched.deleteJob(JobKey.jobKey(appJobDO.getJobName(), appJobDO.getJobGroup()));
			}
		} catch (SchedulerException e) {
			throw new ServiceException(ResultMessageEnum.ERROR_SYSTEM_EXCEPTION);
		}

		// set default value;
		Date current = new Date();
		appJobDO.setUpdateDate(current);
		appJobDO.setInputDate(current);
		appJobDO.setStatus(STATUS.OPEN.name());
		appJobDO.setUpdateVersion(BaseDO.ZERO);
		appJobDO.setIsDelete(DELETE.N.name());

		appJobDAO.insertAppJob(appJobDO);

		boolean needRollback = true;
		try {
			Trigger trigger = buildTriggerByAppJob(appJobDO);
			JobDetail jobDetail = buildJobDetailByAppJob(sched, appJobDO);
			sched.scheduleJob(jobDetail, trigger);
			needRollback = false;
		} catch (ServiceException e) {
			logger.info(Utils.getExceptionInfo(e));
			throw e;
		} catch (Exception e) {
			logger.info(Utils.getExceptionInfo(e));
			throw new ServiceException(ResultMessageEnum.ERROR_SYSTEM_EXCEPTION);
		} finally {
			if (needRollback) {
				AppJobDO updateAppJobDO = new AppJobDO();
				updateAppJobDO.setAppJobId(appJobDO.getAppJobId());
				updateAppJobDO.setIsDelete(DELETE.Y.name());
				updateAppJobDO.setReason("注册schedule异常");
				updateAppJobByAppJobId(updateAppJobDO);
			}
		}
		return appJobDO;
	}

	private JobDetail buildJobDetailByAppJob(Scheduler sched, AppJobDO appJobDO) throws ServiceException {
		try {
			sched = StdSchedulerFactory.getDefaultScheduler();
			boolean exist = sched.checkExists(JobKey.jobKey(appJobDO.getJobName(), appJobDO.getJobGroup()));
			if (exist) {
				throw new ServiceException(ResultMessageEnum.ERROR_RECORD_EXIST, appJobDO.getJobGroup() + appJobDO.getJobName(), "JobName和JobGroup已存在.");
			}
		} catch (SchedulerException e) {
			throw new ServiceException(ResultMessageEnum.ERROR_SYSTEM_EXCEPTION);
		}
		JobDetail jobDetail = JobBuilder.newJob().withIdentity(appJobDO.getJobName(), appJobDO.getJobGroup())
				.ofType(MyJob.class).build();
		return jobDetail;
	}

	@SuppressWarnings("rawtypes")
	private Trigger buildTriggerByAppJob(AppJobDO appJobDO) throws ServiceException {

		// check time
		if (appJobDO.getStartTime() != null && appJobDO.getEndTime() != null && appJobDO.getEndTime().before(appJobDO.getStartTime())) {
			throw new ServiceException(ResultMessageEnum.ERROR_COMPARE_TIME);
		} else if (appJobDO.getEndTime() != null && appJobDO.getEndTime().before(new Date())) {
			throw new ServiceException(ResultMessageEnum.ERROR_MORE_THAN_CURRENT);
		}

		AbstractTrigger trigger = getTriggerByAppJobDO(appJobDO);
		
		trigger.setStartTime(appJobDO.getStartTime());
		trigger.setEndTime(appJobDO.getEndTime());
		return trigger;
	}

	@SuppressWarnings("rawtypes")
	public static AbstractTrigger getTriggerByAppJobDO(AppJobDO appJobDO) throws ServiceException {
		AbstractTrigger trigger = null;
		if (TRIGGER.SIMPLE.name().equalsIgnoreCase(appJobDO.getTriggerType())) {
			SimpleTriggerImpl simpleTrigger = (SimpleTriggerImpl) TriggerBuilder.newTrigger().withIdentity(String.valueOf(appJobDO.getAppJobId()), appJobDO.getJobGroup()).build();
			simpleTrigger.setRepeatCount(appJobDO.getRepeatCount());
			simpleTrigger.setRepeatInterval(appJobDO.getRepeatInterval());
			trigger = simpleTrigger;
		} else {
			try {
				CronTriggerImpl cronTrigger = (CronTriggerImpl) CronScheduleBuilder.cronSchedule(appJobDO.getCron()).build();
				trigger = cronTrigger;
			} catch (RuntimeException e) {
				throw new ServiceException(ResultMessageEnum.ERROR_PATTERN_INVALID, appJobDO.getCron(), "CRON 格式不合法.");
			}
		}
		return trigger;
	}
	
	public Integer updateAppJobByAppJobId(AppJobDO appJobDO) throws ServiceException {
		// check params first.
		checkUpdate(appJobDO);
		if (StringUtils.isNotBlank(appJobDO.getStatus()) && !EnumHelper.checkExist(STATUS.class, appJobDO.getStatus())) {
			throw new ServiceException(ResultMessageEnum.ERROR_PATTERN_INVALID, appJobDO.getStatus(), "STATUS 格式不合法.");
		} else if (StringUtils.isNotBlank(appJobDO.getRequestWay()) && !EnumHelper.checkExist(HTTP_WAY.class, appJobDO.getRequestWay())) {
			throw new ServiceException(ResultMessageEnum.ERROR_PATTERN_INVALID, appJobDO.getRequestWay(), "RequestWay 格式不合法.");
		}
		if (StringUtils.isEmpty(appJobDO.getUpdateUser())){
			appJobDO.setUpdateUser("SYSTEM");
		}
		logger.info("update app job : "+new Gson().toJson(appJobDO));
		appJobDO.setUpdateDate(new Date());
		return appJobDAO.updateAppJobByAppJobId(appJobDO);
	}

	public Integer countAppJobList(QueryAppJobDO queryAppJobDO) throws ServiceException {
		return appJobDAO.countAppJobList(queryAppJobDO);
	}

	/**
	 * According to DB info. check attribute allow empty or not, and check
	 * attribute's length is over upper limit of length or not. and this method
	 * is generate by tools.
	 */
	@SuppressWarnings({ "deprecation" })
	private void checkInsert(AppJobDO appJobDO) throws ServiceException {
		if (appJobDO == null) {
		} else if (StringUtils.isEmpty(appJobDO.getJobName()) || (StringUtils.isNotEmpty(appJobDO.getJobName()) && appJobDO.getJobName().length() > 255)) {
			throw new ServiceException("PARAM_IS_INVALID", "jobName is null or out of range, Upper limit of length is 255");
		} else if (StringUtils.isEmpty(appJobDO.getJobGroup()) || (StringUtils.isNotEmpty(appJobDO.getJobGroup()) && appJobDO.getJobGroup().length() > 255)) {
			throw new ServiceException("PARAM_IS_INVALID", "jobGroup is null or out of range, Upper limit of length is 255");
		} else if (StringUtils.isEmpty(appJobDO.getInputUser()) || (StringUtils.isNotEmpty(appJobDO.getInputUser()) && appJobDO.getInputUser().length() > 255)) {
			throw new ServiceException("PARAM_IS_INVALID", "InputUser is null or out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(appJobDO.getUpdateUser()) && appJobDO.getUpdateUser().length() > 255) {
			throw new ServiceException("PARAM_IS_INVALID", "UpdateUser is out of range, Upper limit of length is 255");
		} else if (StringUtils.isEmpty(appJobDO.getUri()) || (StringUtils.isNotEmpty(appJobDO.getUri()) && appJobDO.getUri().length() > 255)) {
			throw new ServiceException("PARAM_IS_INVALID", "uri is null or out of range, Upper limit of length is 255");
		} else if (StringUtils.isEmpty(appJobDO.getTriggerType())) {
			throw new ServiceException("PARAM_IS_INVALID", "triggerType is null or out of range, Upper limit of length is 6");
		} else if (StringUtils.isEmpty(appJobDO.getRequestWay()) || (StringUtils.isNotEmpty(appJobDO.getRequestWay()) && appJobDO.getRequestWay().length() > 4)) {
			throw new ServiceException("PARAM_IS_INVALID", "requestWay is null or out of range, Upper limit of length is 4");
		}
		
		checkTrigger(appJobDO);
	}

	
	public static void checkTrigger(AppJobDO appJobDO)throws ServiceException{
		if (AppJobEnum.TRIGGER.CRON.name().equalsIgnoreCase(appJobDO.getTriggerType()) && StringUtils.isBlank(appJobDO.getCron())) {
			throw new ServiceException("CRON_ERROR", "CRON表达式不能为空.");
		} else if (appJobDO.getRepeatInterval() == null || appJobDO.getRepeatCount() == null) {
			throw new ServiceException("SIMPLE_CONTAIN_ERROR", "总次数和时间间隔不能为.");
		} else if (AppJobEnum.TRIGGER.SIMPLE.name().equalsIgnoreCase(appJobDO.getTriggerType()) && appJobDO.getRepeatInterval() < 10) {
			throw new ServiceException("SIMPLE_CONTAIN_ERROR", "时间间隔不能小于10毫秒。");
		}
	}
	
	@SuppressWarnings({ "deprecation" })
	private void checkUpdate(AppJobDO appJobDO) throws ServiceException {
		if (appJobDO.getAppJobId() == null) {
			throw new ServiceException("UPDATE_ERROR", "appJobId can't be null.");
		} else if (appJobDO.getAppJobId() != null && String.valueOf(appJobDO.getAppJobId()).length() > 11) {
			throw new ServiceException("UPDATE_ERROR", "appJobId is out of range, Upper limit of length is 11");
		} else if (StringUtils.isNotEmpty(appJobDO.getJobName()) && appJobDO.getJobName().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "jobName is out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(appJobDO.getJobGroup()) && appJobDO.getJobGroup().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "jobGroup is out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(appJobDO.getUri()) && appJobDO.getUri().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "uri is out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(appJobDO.getDescription()) && appJobDO.getDescription().length() > 5000) {
			throw new ServiceException("UPDATE_ERROR", "description is out of range, Upper limit of length is 5000");
		} else if (StringUtils.isNotEmpty(appJobDO.getMapData()) && appJobDO.getMapData().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "mapData is out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(appJobDO.getTriggerType()) && appJobDO.getTriggerType().length() > 6) {
			throw new ServiceException("UPDATE_ERROR", "triggerType is out of range, Upper limit of length is 6");
		} else if (StringUtils.isNotEmpty(appJobDO.getRequestWay()) && appJobDO.getRequestWay().length() > 4) {
			throw new ServiceException("UPDATE_ERROR", "requestWay is out of range, Upper limit of length is 4");
		} else if (StringUtils.isNotEmpty(appJobDO.getStatus()) && appJobDO.getStatus().length() > 9) {
			throw new ServiceException("UPDATE_ERROR", "status is out of range, Upper limit of length is 9");
		} else if (StringUtils.isNotEmpty(appJobDO.getCron()) && appJobDO.getCron().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "cron is out of range, Upper limit of length is 255");
		} else if (appJobDO.getRepeatCount() != null && String.valueOf(appJobDO.getRepeatCount()).length() > 11) {
			throw new ServiceException("UPDATE_ERROR", "repeatCount is out of range, Upper limit of length is 11");
		} else if (appJobDO.getRepeatInterval() != null && String.valueOf(appJobDO.getRepeatInterval()).length() > 11) {
			throw new ServiceException("UPDATE_ERROR", "repeatInterval is out of range, Upper limit of length is 11");
		} else if (StringUtils.isNotEmpty(appJobDO.getReason()) && appJobDO.getReason().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "reason is out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(appJobDO.getInputUser()) && appJobDO.getInputUser().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "inputUser is out of range, Upper limit of length is 255");
		} else if (StringUtils.isNotEmpty(appJobDO.getUpdateUser()) && appJobDO.getUpdateUser().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "updateUser is out of range, Upper limit of length is 255");
		} else if (appJobDO.getUpdateVersion() != null && String.valueOf(appJobDO.getUpdateVersion()).length() > 11) {
			throw new ServiceException("UPDATE_ERROR", "updateVersion is out of range, Upper limit of length is 11");
		} else if (StringUtils.isNotEmpty(appJobDO.getIsDelete()) && appJobDO.getIsDelete().length() > 255) {
			throw new ServiceException("UPDATE_ERROR", "isDelete is out of range, Upper limit of length is 255");
		}

	}
}
