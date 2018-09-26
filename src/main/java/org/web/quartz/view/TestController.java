package org.web.quartz.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Scope("prototype")
@Controller
@RequestMapping("test")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class TestController {


	@RequestMapping(value = "getRequest", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void getRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Map map = new HashMap();

		map.put("message", "haha,lingge hao ku!");
		String param = request.getParameter("param");
		if ("true".equals(param)) {
			map.put("success", true);
		} else {
			map.put("success", false);
		}
		String str = new Gson().toJson(map);
		response.getWriter().write(str);
		response.flushBuffer();
	}


	@RequestMapping(value = "postRequest", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void postRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map map = new HashMap();

		map.put("message", "haha,lingge hao ku!");
		String param = request.getParameter("param");
		if ("true".equals(param)) {
			map.put("success", true);
		} else {
			map.put("success", false);
		}
		String str = new Gson().toJson(map);
		response.getWriter().write(str);
		response.flushBuffer();
	}

}
