package org.web.quartz.view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web.quartz.domain.AppJobDO;
import org.web.quartz.domain.AppJobEnum.STATUS;
import org.web.quartz.domain.BaseDO.DELETE;
import org.web.quartz.domain.DateUtils;
import org.web.quartz.domain.MemberDO;
import org.web.quartz.domain.MemberGroupDO;
import org.web.quartz.domain.ResultMessageEnum;
import org.web.quartz.domain.ViewResult;
import org.web.quartz.domain.ViewTypeEnum;
import org.web.quartz.interceptor.CheckLandingByCookieInterceptor;
import org.web.quartz.query.QueryAppJobDO;
import org.web.quartz.query.QueryMemberGroupDO;
import org.web.quartz.query.ServiceException;
import org.web.quartz.service.AppJobService;
import org.web.quartz.service.MemberGroupService;
import org.web.quartz.utils.ViewHelper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Scope("prototype")
@Controller
@RequestMapping("member")
public class JobController {

	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@Resource
	private AppJobService appJobService;

	@Resource
	private MemberGroupService memberGroupService;

	@RequestMapping(value = "job", method = { RequestMethod.GET, RequestMethod.POST })
	public String job(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "member/job";
	}

	@RequestMapping(value = "insertJob", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String insertJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ViewResult result = new ViewResult();
		try {
			AppJobDO appJobDO = buildBeanByRequest(request);
			MemberDO currentMemberDO = CheckLandingByCookieInterceptor.getMemberDOFromCookie(request);
			checkAccessPrivilege(appJobDO.getJobGroup(), currentMemberDO.getMemberId());
			appJobDO.setInputUser(currentMemberDO.getMemberName());
			appJobDO.setUpdateUser(currentMemberDO.getMemberName());
			appJobService.insertAppJob(appJobDO);
			result.setMsg("添加成功");
			result.setResult(true);
			result.setType(ViewTypeEnum.info.name());
		} catch (ServiceException e) {
			result = ViewHelper.buildViewByServiceException(e);
			result.setTitle("添加失败");
		}
		Gson gson = new Gson();
		return gson.toJson(result);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private AppJobDO buildBeanByRequest(HttpServletRequest request) throws ServiceException {
		AppJobDO appJobDO = new AppJobDO();
		try {
			Enumeration e = request.getParameterNames();
			Map map = new HashMap();
			while (e.hasMoreElements()) {
				String name = e.nextElement().toString();
				logger.info(name + "=" + request.getParameter(name));
				map.put(name, request.getParameter(name));
			}
			map.remove("startTime");
			map.remove("endTime");
			BeanUtils.populate(appJobDO, map);
			SimpleDateFormat df = new SimpleDateFormat(DateUtils.FORMAT_ONE);
			if (StringUtils.isNotBlank(request.getParameter("startTime"))) {
				appJobDO.setStartTime(df.parse(request.getParameter("startTime")));
			}
			if (StringUtils.isNotBlank(request.getParameter("endTime"))) {
				appJobDO.setEndTime(df.parse(request.getParameter("endTime")));
			}
			return appJobDO;
		} catch (Exception e) {
			throw new ServiceException(ResultMessageEnum.ERROR_PARAM_INVALID);
		}
	}

	@RequestMapping(value = "deleteJob", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deleteJob(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ViewResult result = new ViewResult();
		MemberDO currentMemberDO = CheckLandingByCookieInterceptor.getMemberDOFromCookie(request);
		try {
			String deletes = request.getParameter("deletes");
			String[] array = deletes.split(",");
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null && StringUtils.isNotBlank(array[i].trim())) {
					list.add(Integer.valueOf(array[i].trim()));
				}
			}

			for (Integer appJobId : list) {
				AppJobDO appJobDO = appJobService.queryAppJobByAppJobId(appJobId);
				QueryMemberGroupDO queryGroup = new QueryMemberGroupDO();

				checkAccessPrivilege(appJobDO.getJobGroup(), currentMemberDO.getMemberId());

				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
				boolean flag = scheduler.checkExists(JobKey.jobKey(appJobDO.getJobName(), queryGroup.getJobGroup()));
				if (flag) {
					scheduler.deleteJob(JobKey.jobKey(appJobDO.getJobName(), queryGroup.getJobGroup()));
				}
				AppJobDO updateAppJobDO = new AppJobDO();
				updateAppJobDO.setAppJobId(appJobDO.getAppJobId());
				updateAppJobDO.setUpdateUser(currentMemberDO.getMemberName());
				updateAppJobDO.setIsDelete(DELETE.Y.name());
				updateAppJobDO.setReason("DELETE BY MEMBER");
				appJobService.updateAppJobByAppJobId(updateAppJobDO);
			}

		} catch (ServiceException e) {
			result = ViewHelper.buildViewByServiceException(e);
			result.setTitle("删除失败");
			return new Gson().toJson(result);
		} catch (Exception e) {
			result.setResult(false);
			result.setMsg("系统异常");
			result.setTitle("删除失败");
			return new Gson().toJson(result);
		}
		result.setResult(true);
		result.setType(ViewTypeEnum.info.name());
		result.setMsg("删除成功");
		Gson gson = new Gson();
		return gson.toJson(result);
	}

	@RequestMapping(value = "updateJob", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String updateJob(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			AppJobDO updateAppJobDO = buildUpdateAppJobDOByRequest(request);
			MemberDO currentMemberDO = CheckLandingByCookieInterceptor.getMemberDOFromCookie(request);
			
			//校验权限
			checkAccessPrivilege(updateAppJobDO.getJobGroup(), currentMemberDO.getMemberId());
			updateAppJobDO.setUpdateUser(updateAppJobDO.getUpdateUser());
			
			AppJobDO oldAppJobDO = appJobService.queryAppJobByAppJobId(updateAppJobDO.getAppJobId());
			if (STATUS.COMPLETED.name().equalsIgnoreCase(oldAppJobDO.getStatus())) {
				throw new ServiceException(ResultMessageEnum.ERROR_RECORD_NOT_UPDATE, oldAppJobDO.getStatus(), "STATUS 是完成的场合不允许修改.");
			} else if (DELETE.Y.name().equalsIgnoreCase(oldAppJobDO.getIsDelete())){
				throw new ServiceException(ResultMessageEnum.ERROR_RECORD_NOT_UPDATE, oldAppJobDO.getStatus(), "记录已经被删除.");
			}
			updateAppJobDO.setTriggerType(oldAppJobDO.getTriggerType());
			updateAppJobDO.setJobGroup(oldAppJobDO.getJobGroup());
			AppJobService.checkTrigger(updateAppJobDO);
			
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			Trigger trigger = AppJobService.getTriggerByAppJobDO(updateAppJobDO);
			scheduler.rescheduleJob(TriggerKey.triggerKey(oldAppJobDO.getAppJobId().toString(), oldAppJobDO.getJobGroup()), trigger);
			appJobService.updateAppJobByAppJobId(updateAppJobDO);
		
		} catch (ServiceException e) {
			ViewResult result = ViewHelper.buildViewByServiceException(e);
			result.setTitle("修改失败");
			result.setMsg("修改失败");
			return new Gson().toJson(result);
		} catch (Exception e) {

		}

		ViewResult result = new ViewResult();
		result.setMsg("修改成功");
		result.setResult(true);
		result.setType("warning");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(result);
	}

	private AppJobDO buildUpdateAppJobDOByRequest(HttpServletRequest request){
		String uri = request.getParameter("uri");
		String description = request.getParameter("description");
		String mapData = request.getParameter("mapData");
		String requestWay = request.getParameter("requestWay");
		String cron = request.getParameter("cron");
		String repeatCount = request.getParameter("repeatCount");
		String repeatInterval = request.getParameter("repeatInterval");
		String appJobId = request.getParameter("appJobId");
		AppJobDO updateAppJobDO = new AppJobDO();
		updateAppJobDO.setUri(uri);
		updateAppJobDO.setDescription(description);
		updateAppJobDO.setMapData(mapData);
		if(StringUtils.isNotBlank(requestWay)){
			updateAppJobDO.setRequestWay(requestWay);
		}
		//updateAppJobDO.setStatus(status);
		updateAppJobDO.setCron(cron);
		updateAppJobDO.setRepeatCount(Integer.valueOf(repeatCount));
		updateAppJobDO.setRepeatInterval(Integer.valueOf(repeatInterval));
		updateAppJobDO.setAppJobId(Integer.valueOf(appJobId));updateAppJobDO.setRequestWay(requestWay);
		updateAppJobDO.setUpdateUser(CheckLandingByCookieInterceptor.getMemberDOFromCookie(request).getMemberName());
		return updateAppJobDO;
	}
	
	@RequestMapping(value = "queryJobDetail", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String queryJobDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {

		AppJobDO appJobDO = null;
		try {
			Integer appJobId = Integer.valueOf(request.getParameter("appJobId"));
			appJobDO = appJobService.queryAppJobByAppJobId(appJobId);
			
			MemberDO member = CheckLandingByCookieInterceptor.getMemberDOFromCookie(request);
			checkAccessPrivilege(appJobDO.getJobGroup(), member.getMemberId());
			
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			if (scheduler.checkExists(TriggerKey.triggerKey(appJobDO.getAppJobId().toString(), appJobDO.getJobGroup()))) {
				Trigger trigger = scheduler.getTrigger(TriggerKey.triggerKey(appJobDO.getAppJobId().toString(), appJobDO.getJobGroup()));
				appJobDO.setNextFireTime(trigger.getNextFireTime());
				appJobDO.setPrevFireTime(trigger.getPreviousFireTime());
			} else if (!STATUS.COMPLETED.name().equals(appJobDO.getStatus())) {
				AppJobDO updateAppJobDO = new AppJobDO();
				updateAppJobDO.setAppJobId(appJobDO.getAppJobId());
				updateAppJobDO.setStatus(STATUS.COMPLETED.name());
				appJobService.updateAppJobByAppJobId(updateAppJobDO);
				appJobDO.setStatus(STATUS.COMPLETED.name());
			}
		} catch (ServiceException e) {
			ViewResult result = ViewHelper.buildViewByServiceException(e);
			return new Gson().toJson(result);
		} catch (Exception e) {

		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(appJobDO);
	}

	@RequestMapping(value = "queryJobList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String queryJobList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListView<AppJobDO> view = new ListView<AppJobDO>();
		try {
			QueryAppJobDO query = buildQueryByRequest(request);
			query.setIsDelete(DELETE.N.name());
			int total = appJobService.countAppJobList(query);
			query.setOrderByClause(" UPDATE_DATE DESC ");
			List<AppJobDO> list = appJobService.queryAppJobList(query);
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			for (AppJobDO appJobDO : list) {
				if (scheduler.checkExists(TriggerKey.triggerKey(appJobDO.getAppJobId().toString(), appJobDO.getJobGroup()))) {
					Trigger trigger = scheduler.getTrigger(TriggerKey.triggerKey(appJobDO.getAppJobId().toString(), appJobDO.getJobGroup()));
					appJobDO.setNextFireTime(trigger.getNextFireTime());
					appJobDO.setPrevFireTime(trigger.getPreviousFireTime());
				} else if (!STATUS.COMPLETED.name().equalsIgnoreCase(appJobDO.getStatus())) {
					AppJobDO updateAppJobDO = new AppJobDO();
					updateAppJobDO.setAppJobId(appJobDO.getAppJobId());
					updateAppJobDO.setStatus(STATUS.COMPLETED.name());
					updateAppJobDO.setReason("Job has been done.");
					appJobService.updateAppJobByAppJobId(updateAppJobDO);
					appJobDO.setStatus(STATUS.COMPLETED.name());
				}
			}
			view.setRows(list);
			view.setTotal(total);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			return gson.toJson(view);
		} catch (ServiceException e) {
			if (ResultMessageEnum.ERROR_RECORD_NOT_EXIST.name().equals(e.getErrorCode())) {
				view.setRows(new ArrayList<AppJobDO>());
				return new Gson().toJson(view);
			}
			ViewResult result = ViewHelper.buildViewByServiceException(e);
			result.setTitle("删除失败");
			return new Gson().toJson(result);
		}
	}

	private QueryAppJobDO buildQueryByRequest(HttpServletRequest request) throws ServiceException {
		String jobGroup = request.getParameter("jobGroup");
		String jobName = request.getParameter("jobName");
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String status = request.getParameter("status");
		String triggerType = request.getParameter("triggerType");
		QueryAppJobDO query = new QueryAppJobDO();
		if (StringUtils.isNotBlank(jobName)) {
			query.setIndistinctJobName(jobName);
		}
		if (StringUtils.isNotBlank(page)) {
			query.setPage(Integer.valueOf(request.getParameter("page")));
		}
		if (StringUtils.isNotBlank(rows)) {
			query.setPageSize(Integer.valueOf(rows));
		}

		QueryMemberGroupDO queryGroup = new QueryMemberGroupDO();
		MemberDO member = CheckLandingByCookieInterceptor.getMemberDOFromCookie(request);
		queryGroup.setMemberId(member.getMemberId());
		if (StringUtils.isNotBlank(jobGroup)) {
			query.setJobGroup(jobGroup);
			checkAccessPrivilege(jobGroup, member.getMemberId());
		} else {
			List<MemberGroupDO> list = memberGroupService.queryMemberGroupList(queryGroup);
			if (list != null && list.size() > 0) {
				List<String> jobGroupList = new ArrayList<String>();
				for (MemberGroupDO memberGroupDO : list) {
					jobGroupList.add(memberGroupDO.getJobGroup());
				}
				query.setJobGroupList(jobGroupList);
			} else {
				throw new ServiceException(ResultMessageEnum.ERROR_RECORD_NOT_EXIST);
			}
		}

		if (StringUtils.isNotBlank(status) && !"ALL".equalsIgnoreCase(status)) {
			query.setStatus(status);
		}
		if (StringUtils.isNotBlank(triggerType) && !"ALL".equalsIgnoreCase(triggerType)) {
			query.setTriggerType(triggerType);
		}
		return query;
	}

	private void checkAccessPrivilege(String jobGroup, Integer memberId) throws ServiceException {
		QueryMemberGroupDO queryGroup = new QueryMemberGroupDO();
		queryGroup.setJobGroup(jobGroup);
		queryGroup.setMemberId(memberId);
		int count = memberGroupService.countMemberGroupList(queryGroup);
		if (count == 0) {
			throw new ServiceException(ResultMessageEnum.ERROR_ACCESS_PRIVILEGE);
		}
	}

}
