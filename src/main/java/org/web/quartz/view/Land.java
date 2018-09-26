package org.web.quartz.view;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web.quartz.domain.MemberDO;
import org.web.quartz.domain.PropertiesConstant;
import org.web.quartz.domain.ResultMessageEnum;
import org.web.quartz.domain.ViewResult;
import org.web.quartz.domain.ViewTypeEnum;
import org.web.quartz.interceptor.CheckLandingByCookieInterceptor;
import org.web.quartz.query.QueryMemberDO;
import org.web.quartz.query.ServiceException;
import org.web.quartz.service.MemberService;
import org.web.quartz.utils.ViewHelper;

import com.google.gson.Gson;

@Scope("prototype")
@Controller
public class Land {

	//private static final Logger logger = LoggerFactory.getLogger(Land.class);

	@Resource
	private MemberService memberService;

	@RequestMapping(value = "land", method = { RequestMethod.GET, RequestMethod.POST })
	public String land(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "land/land";
	}

	@RequestMapping(value = "checkLand", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String checkLand(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String passWord = request.getParameter("password");
		String memberName = request.getParameter("memberName");
		QueryMemberDO query = new QueryMemberDO();
		query.setMemberName(memberName);
		ViewResult result = new ViewResult();
		try {
			if (StringUtils.isEmpty(memberName)) {
				throw new ServiceException(ResultMessageEnum.ERROR_PARAM_EMPTY, null, "用户名不能为空");
			} else if (StringUtils.isEmpty(passWord)) {
				throw new ServiceException(ResultMessageEnum.ERROR_PARAM_EMPTY, null, "密码不能为空");
			}
			MemberDO memberDO = memberService.queryOneMember(query);
			if (memberDO == null) {
				memberDO = new MemberDO();
				memberDO.setPassWord(passWord);
				memberDO.setMemberName(memberName);
				memberService.insertMember(memberDO);
				Cookie c = new Cookie(CheckLandingByCookieInterceptor.COOKIE_KEY, memberDO.getMemberId() + CheckLandingByCookieInterceptor.SEPERAT + memberDO.getMemberName());
				response.addCookie(c);
			} else if (memberDO.getPassWord().equals(passWord)) {
				Cookie c = new Cookie(CheckLandingByCookieInterceptor.COOKIE_KEY, memberDO.getMemberId() + CheckLandingByCookieInterceptor.SEPERAT + memberDO.getMemberName());
				response.addCookie(c);
			}
			result.setResult(true);
			result.setType(ViewTypeEnum.info.name());
		} catch (ServiceException e) {
			result.setType(ViewTypeEnum.error.name());
			result = ViewHelper.buildViewByServiceException(e);
			result.setTitle("登陆失败");
		}
		Gson gson = new Gson();
		return gson.toJson(result);
	}

	@RequestMapping(value = "logout", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		for (Cookie cookie : request.getCookies()) {
			if (CheckLandingByCookieInterceptor.COOKIE_KEY.equals(cookie.getName())) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				break;
			}
		}
		response.sendRedirect(PropertiesConstant.LAND_URI);
		return "";
	}
	
	@RequestMapping(value = "main", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "land/main";
	}

}
