package org.web.quartz.view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web.helper.ExceptionHelper;
import org.web.quartz.domain.BaseDO.DELETE;
import org.web.quartz.domain.DateUtils;
import org.web.quartz.domain.ResultHistoryDO;
import org.web.quartz.domain.ResultMessageEnum;
import org.web.quartz.domain.ViewResult;
import org.web.quartz.query.QueryMemberGroupDO;
import org.web.quartz.query.QueryResultHistoryDO;
import org.web.quartz.query.ServiceException;
import org.web.quartz.service.MemberGroupService;
import org.web.quartz.service.ResultHistoryService;
import org.web.quartz.utils.ViewHelper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Scope("prototype")
@Controller
@RequestMapping("member")
public class HistoryController {

	private static final Logger logger = LoggerFactory.getLogger(HistoryController.class);

	@Resource
	private ResultHistoryService resultHistoryService;

	@Resource
	private MemberGroupService memberGroupService;

	@RequestMapping(value = "history", method = { RequestMethod.GET, RequestMethod.POST })
	public String history(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "member/history";
	}

	@RequestMapping(value = "queryHistoryDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public String queryHistoryDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "member/queryHistoryDetail";
	}

	@RequestMapping(value = "queryHistoryList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String queryHistoryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListView<ResultHistoryDO> view = new ListView<ResultHistoryDO>();
		try {
			QueryResultHistoryDO query = buildQueryByRequest(request);

			int total = resultHistoryService.countResultHistoryList(query);

			List<ResultHistoryDO> list = resultHistoryService.queryResultHistoryList(query);
			view.setRows(list);
			view.setTotal(total);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			return gson.toJson(view);
		} catch (ServiceException e) {
			logger.error(ExceptionHelper.getExceptionInfo(e));
			if (ResultMessageEnum.ERROR_RECORD_NOT_EXIST.name().equals(e.getErrorCode())) {
				view.setRows(new ArrayList<ResultHistoryDO>());
				return new Gson().toJson(view);
			}
			ViewResult result = ViewHelper.buildViewByServiceException(e);
			result.setTitle("删除失败");
			return new Gson().toJson(result);
		}

	}

	private QueryResultHistoryDO buildQueryByRequest(HttpServletRequest request) throws ServiceException {
		QueryResultHistoryDO queryResultHistoryDO = new QueryResultHistoryDO();
		String jobGroup = request.getParameter("jobGroup");
		String jobName = request.getParameter("jobName");
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String result = request.getParameter("result");
		if (StringUtils.isNotBlank(jobName)) {
			queryResultHistoryDO.setIndistinctJobName(jobName);
		}
		if (StringUtils.isNotBlank(page)) {
			queryResultHistoryDO.setPage(Integer.valueOf(request.getParameter("page")));
		}
		if (StringUtils.isNotBlank(rows)) {
			queryResultHistoryDO.setPageSize(Integer.valueOf(rows));
		}
		if (StringUtils.isNotBlank(jobGroup)) {
			queryResultHistoryDO.setJobGroup(jobGroup);
		}
		SimpleDateFormat df = new SimpleDateFormat(DateUtils.FORMAT_ONE);
		try {
			if (StringUtils.isNotBlank(request.getParameter("beginDate"))) {
				queryResultHistoryDO.setEqualAndMoreThanInputDate(df.parse(request.getParameter("beginDate")));
			}
			if (StringUtils.isNotBlank(request.getParameter("endDate"))) {
				queryResultHistoryDO.setEqualAndLessThanInputDate(df.parse(request.getParameter("endDate")));
			}
		} catch (Exception e) {
			throw new ServiceException(ResultMessageEnum.ERROR_PARAM_INVALID);
		}
		if("true".equals(result)){
			queryResultHistoryDO.setSuccess("true");
		} else if ("false".equals(result)){
			queryResultHistoryDO.setSuccess("false");
		}
		
		queryResultHistoryDO.setIsDelete(DELETE.N.name());
		queryResultHistoryDO.setOrderByClause(" INPUT_DATE DESC ");
		return queryResultHistoryDO;
	}


	// @RequestMapping(value = "insertHistory", method = { RequestMethod.GET,
	// RequestMethod.POST })
	// @ResponseBody
	// public String insertHistory(HttpServletRequest request,
	// HttpServletResponse response) throws IOException {
	// Gson gson = new Gson();
	// ViewResult result = new ViewResult();
	// result.setMsg("添加成功");
	// result.setResult(true);
	// result.setType("warning");
	// return gson.toJson(result);
	// }
	//
	//
	//
	// @RequestMapping(value = "deleteHistory", method = { RequestMethod.GET,
	// RequestMethod.POST })
	// @ResponseBody
	// public String deleteHistory(HttpServletRequest request,
	// HttpServletResponse response) throws IOException {
	// Gson gson = new Gson();
	// ViewResult result = new ViewResult();
	// result.setMsg("删除成功");
	// result.setResult(true);
	// result.setType("error");
	// return gson.toJson(result);
	// }
	//
	// @RequestMapping(value = "updateHistory", method = { RequestMethod.GET,
	// RequestMethod.POST })
	// @ResponseBody
	// public String updateHistory(HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// Gson gson = new Gson();
	// ViewResult result = new ViewResult();
	// result.setMsg("修改成功");
	// result.setResult(true);
	// result.setType("warning");
	// return gson.toJson(result);
	// }

}
