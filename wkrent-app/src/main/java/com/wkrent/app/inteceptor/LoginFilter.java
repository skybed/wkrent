package com.wkrent.app.inteceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.wkrent.common.constants.Constant;

/**
 * 登录拦截
 */
public class LoginFilter implements Filter {
	
	private final String auth_filter_suffix = ".*?(?<=\\\\.(?:css|js|png|ico|jpeg|json|woff|gif))$";
	
	private String noAuthUrls = "";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        
        //过滤静态资源文件
		if (httpRequest.getRequestURL().toString().matches(auth_filter_suffix)) {
			chain.doFilter(request, response);
			return;
		}
		
        //在配置文件中是否使用sso
        String[] strArray = noAuthUrls.split(",");
        for (String str : strArray) {
        	if ("".equals(str))	continue;
        	if (httpRequest.getRequestURL().indexOf(str) >= 0) {
        		chain.doFilter(request, response);
        		return;
        	}
        }
        
        if (session.getAttribute("userId") != null) {
        	chain.doFilter(request, response);
        } else {
        	httpResponse.setCharacterEncoding("UTF-8");  
        	httpResponse.setContentType("application/json; charset=utf-8");
        	PrintWriter out = null ;
		    JSONObject res = new JSONObject();
		    res.put("code", Constant.RESULT_NOT_LOGIN_CODE);
		    res.put("msg", Constant.RESULT_NOT_LOGIN_MSG);
		    res.put("data", "");
		    out = httpResponse.getWriter();
		    out.append(res.toString());
            return;
        }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		noAuthUrls = arg0.getInitParameter("noAuthUrls");
	}
}
