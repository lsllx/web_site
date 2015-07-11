package com.website.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;


public class Log4jFilter implements Filter {
	public static Logger logger= Logger.getLogger("UserLogin");
	public Log4jFilter() {
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse resp = (HttpServletResponse)arg1;
		String ip = req.getRemoteAddr();
		String loginName = "AnonymousUser";
		MDC.put("ip", ip);
		MDC.put("loginName",loginName);
		logger.info("User:"+loginName+" Login");
		arg2.doFilter(req, resp);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
