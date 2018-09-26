package org.web.quartz.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.web.quartz.domain.AppJobDO;
import org.web.quartz.query.QueryAppJobDO;
import org.web.quartz.query.QueryResultHistoryDO;
import org.web.quartz.service.AppJobService;
import org.web.quartz.service.ResultHistoryService;

@Scope("prototype")
@Controller
@RequestMapping("member")
public class ResultController {

	private static final Logger logger = LoggerFactory.getLogger(ResultController.class);

	@Resource
	private ResultHistoryService resultHistoryService;

	@Resource
	private AppJobService appJobService;

	@RequestMapping(value = "queryResultList", method = { RequestMethod.GET, RequestMethod.POST })
	public String queryResultList(HttpServletRequest request, HttpServletResponse response) throws IOException {

		QueryAppJobDO queryAppJobDO = new QueryAppJobDO();
		queryAppJobDO.setIndistinctJobName(null);
		queryAppJobDO.setJobGroup(null);
		List<AppJobDO> list = appJobService.queryAppJobList(queryAppJobDO);
		List<Integer> jobIdList = new ArrayList<Integer>();
		for (AppJobDO appJobDO : list) {
			jobIdList.add(appJobDO.getAppJobId());
		}

		QueryResultHistoryDO query = new QueryResultHistoryDO();
		query.setEqualAndLessThanInputDate(null);
		query.setEqualAndMoreThanInputDate(null);
		query.setJobIdList(jobIdList);
		resultHistoryService.queryResultHistoryList(query);
		return "member/group";
	}
}
