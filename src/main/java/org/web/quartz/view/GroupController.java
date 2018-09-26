package org.web.quartz.view;

import java.io.IOException;
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
import org.web.quartz.domain.BaseDO.DELETE;
import org.web.quartz.domain.MemberDO;
import org.web.quartz.domain.MemberGroupDO;
import org.web.quartz.domain.ViewResult;
import org.web.quartz.domain.ViewTypeEnum;
import org.web.quartz.interceptor.CheckLandingByCookieInterceptor;
import org.web.quartz.query.QueryMemberGroupDO;
import org.web.quartz.query.ServiceException;
import org.web.quartz.service.MemberGroupService;
import org.web.quartz.utils.ViewHelper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Scope("prototype")
@Controller
@RequestMapping("member")
public class GroupController {

	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

	@Resource
	private MemberGroupService memberGroupService;

	@RequestMapping(value = "group", method = { RequestMethod.GET, RequestMethod.POST })
	public String group(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "member/group";
	}

	@RequestMapping(value = "insertGroup", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String insertGroup(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ViewResult result = new ViewResult();
		try {
			MemberGroupDO memberGroupDO = new MemberGroupDO();

			MemberDO member = CheckLandingByCookieInterceptor.getMemberDOFromCookie(request);
			memberGroupDO.setMemberId(member.getMemberId());
			memberGroupDO.setMemberName(member.getMemberName());
			memberGroupDO.setJobGroup(request.getParameter("jobGroup"));
			memberGroupDO.setInputUser(member.getMemberName());
			memberGroupDO.setUpdateUser(member.getMemberName());
			memberGroupService.insertMemberGroup(memberGroupDO);

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

	@RequestMapping(value = "deleteGroup", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deleteGroup(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ViewResult result = new ViewResult();
		try {
			String deletes = request.getParameter("deletes");
			String[] array = deletes.split(",");
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null && StringUtils.isNotBlank(array[i].trim())) {
					list.add(Integer.valueOf(array[i].trim()));
				}
			}
			// int total = list.size();
			QueryMemberGroupDO query = new QueryMemberGroupDO();
			query.setMemberGroupIdList(list);
			MemberDO member = CheckLandingByCookieInterceptor.getMemberDOFromCookie(request);
			query.setMemberId(member.getMemberId());
			List<MemberGroupDO> queryResult = memberGroupService.queryMemberGroupList(query);

			for (MemberGroupDO memberGroupDO : queryResult) {
				MemberGroupDO updateMemberGroupDO = new MemberGroupDO();
				updateMemberGroupDO.setMemberGroupId(memberGroupDO.getMemberGroupId());
				updateMemberGroupDO.setIsDelete(DELETE.Y.name());
				updateMemberGroupDO.setReason("Delete by myself.");
				memberGroupService.updateMemberGroupByMemberGroupId(updateMemberGroupDO);
			}
			result.setMsg("删除成功,共删除" + queryResult.size() + "条记录");
			result.setResult(true);
			result.setType(ViewTypeEnum.info.name());
		} catch (ServiceException e) {
			result = ViewHelper.buildViewByServiceException(e);
			result.setTitle("删除失败");
		}
		Gson gson = new Gson();
		return gson.toJson(result);
	}

	@RequestMapping(value = "queryGroupDetail", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String queryGroupDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long memberGroupId = Long.valueOf(request.getParameter("memberGroupId"));
		MemberGroupDO memberGroupDO;
		try {
			memberGroupDO = memberGroupService.queryMemberGroupByMemberGroupId(memberGroupId);
		} catch (ServiceException e) {
			ViewResult result = ViewHelper.buildViewByServiceException(e);
			result.setTitle("删除失败");
			return new Gson().toJson(result);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(memberGroupDO);
		// return "member/queryGroupDetail";
	}

	@RequestMapping(value = "queryGroupList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String queryGroupList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryMemberGroupDO query = new QueryMemberGroupDO();

		if (StringUtils.isNotBlank(request.getParameter("page"))) {
			query.setPage(Integer.valueOf(request.getParameter("page")));
		}
		if (StringUtils.isNotBlank(request.getParameter("rows"))) {
			query.setPageSize(Integer.valueOf(request.getParameter("rows")));
		}
		query.setIsDelete(DELETE.N.name());
		MemberDO member = CheckLandingByCookieInterceptor.getMemberDOFromCookie(request);
		query.setMemberId(member.getMemberId());
		int total = memberGroupService.countMemberGroupList(query);
		query.setOrderByClause(" UPDATE_DATE DESC ");
		List<MemberGroupDO> list = memberGroupService.queryMemberGroupList(query);

		ListView<MemberGroupDO> view = new ListView<MemberGroupDO>();
		view.setRows(list);
		view.setTotal(total);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(view);
		// return "member/queryGroupList";
	}

}
