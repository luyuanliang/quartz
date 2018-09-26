package org.web.quartz.view;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope("prototype")
@Controller
public class Land2 {

	public static final String LAND = "land";
	
	@RequestMapping(value = "a", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String a(HttpServletRequest request, HttpServletResponse response) throws IOException {
		for(Cookie cookie:request.getCookies()){
			System.out.println(cookie.getName()+" = "+cookie.getValue());
		}
		String name = request.getParameter("name");
		if(name!=null){
			Cookie c = new Cookie("hello","a");
			response.addCookie(c);
		}
		System.out.println("aaaaaaaaaaaaaaaaaaaa");
		return "a";
	}
	
	@RequestMapping(value = "b", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String b(HttpServletRequest request, HttpServletResponse response) throws IOException {
		for(Cookie cookie:request.getCookies()){
			System.out.println(cookie.getName()+" = "+cookie.getValue());
		}
		String name = request.getParameter("name");
		if(name!=null){
			Cookie c = new Cookie("hello","b");
			response.addCookie(c);
		}
		System.out.println("bbbbbbbbbbbbbbbbbbbbb");
		return "b";
	}
}
