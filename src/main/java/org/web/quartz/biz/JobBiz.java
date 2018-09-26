package org.web.quartz.biz;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;
import org.web.exception.ServiceException;
import org.web.helper.ExceptionHelper;
import org.web.helper.HttpHelper;
import org.web.quartz.domain.AppJobDO;
import org.web.quartz.domain.AppJobEnum.HTTP_WAY;
import org.web.quartz.domain.BaseDO.DELETE;
import org.web.quartz.domain.HistoryJobEnum.NeedLog;
import org.web.quartz.domain.ResultHistoryDO;
import org.web.quartz.service.AppJobService;
import org.web.quartz.service.ResultHistoryService;

import com.google.gson.Gson;

@Service("jobBiz")
public class JobBiz {

	private static Logger logger = Logger.getLogger(JobBiz.class);

	@Resource
	private AppJobService appJobService;


	@Resource
	private ResultHistoryService resultHistoryService;

	public void triggerJob(JobExecutionContext context) {
		AppJobDO appJobDO = null;
		int step = 0;
		String result = null;
		ResultHistoryDO resultHistoryDO = new ResultHistoryDO();
		try {
			String triggerName = context.getTrigger().getKey().getName();
			appJobDO = appJobService.queryAppJobByAppJobId(Integer.valueOf(triggerName));
			if (appJobDO == null || DELETE.Y.name().equals(appJobDO.getIsDelete())) {
				logger.error("Job 不存在或者已经删除,job name :" + context.getJobDetail().getKey().getName());
				logger.error("Job 不存在或者已经删除,job group :" + context.getJobDetail().getKey().getGroup());
				// quartz存在，app_job表不存在的场合，删除quartz中的job.
				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
				scheduler.deleteJob(context.getJobDetail().getKey());
				logger.error("Job 自动删除成功.");
				return;
			}

			step = 1;

			resultHistoryDO.setJobId(appJobDO.getAppJobId());
			resultHistoryDO.setJobName(appJobDO.getJobName());
			resultHistoryDO.setJobGroup(appJobDO.getJobGroup());
			
			if (HTTP_WAY.GET.name().equals(appJobDO.getRequestWay())) {
				result = HttpHelper.requestByGet(appJobDO.getUri());
			} else {
				String mapDate = appJobDO.getMapData();
				Map<String, String> map = null;
				if (StringUtils.isNotEmpty(mapDate)) {
					map = str2Map(mapDate);
				}
				result = HttpHelper.requestByPost(appJobDO.getUri(), map, null);
			}
			
			buildByResult(result,resultHistoryDO);
			logger.info("result is " + result);
			step = 3;
		} catch (ServiceException e) {
			resultHistoryDO.setSuccess("false");
			resultHistoryDO.setBody("访问URI异常, URI is " + appJobDO.getUri());
			logger.error(ExceptionHelper.getExceptionInfo(e));
		} catch (Exception e) {
			resultHistoryDO.setSuccess("false");
			resultHistoryDO.setBody("系统异常");
			logger.error(ExceptionHelper.getExceptionInfo(e));
		}

		if (step == 1) {
			if (appJobDO != null && StringUtils.isNotBlank(appJobDO.getNoticeErrorUri())) {
				HttpHelper.requestByGet(appJobDO.getNoticeErrorUri());
			}
		}

		if (appJobDO != null && NeedLog.Y.name().equals(appJobDO.getNeedLog())) {
			resultHistoryService.insertResultHistory(resultHistoryDO);
		}

	}

	private Map<String, String> str2Map(String str) {
		Map<String, String> map = new HashMap<String, String>();
		String[] array = str.split(",");
		for (int i = 0; i < array.length; i++) {
			if (StringUtils.isNotBlank(array[i])) {
				String[] temp = array[i].split("=");
				if (temp.length == 2) {
					String key = temp[0];
					if (StringUtils.isNotBlank(key)) {
						String value = temp[1];
						if (StringUtils.isNotBlank(value)) {
							map.put(key.trim(), value.trim());
						}
					}
				}
			}
		}
		return map;
	}

	private ResultHistoryDO buildByResult(String result,ResultHistoryDO resultHistoryDO ) {
		Gson gson = new Gson();

		resultHistoryDO.setBody(result);
		try {
			Map<String, String> map = gson.fromJson(result, Map.class);
			resultHistoryDO.setSuccess(map.get("success"));
		} catch (Exception e) {
		}
		if (resultHistoryDO.getSuccess() == null) {
			resultHistoryDO.setSuccess(Boolean.TRUE.toString());
		}
		if (result.length() > 5000) {
			result = result.substring(0, 4998);
		}
		resultHistoryDO.setBody(result);
		return resultHistoryDO;
	}
}
