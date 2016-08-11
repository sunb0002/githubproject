package com.ufinity.madoka.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.MDC;

/**
 * 
 * @author SunBo
 * @CopyRight Ufinity - [2000-2016] All Rights Reserved
 * 
 */
public class LoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			// Generate a random request ID
			int random = (int) (Math.random() * 1000000 + 1);
			MDC.put("madoka-request-id", "TX" + random);
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MDC.remove("madoka-request-id");
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig conf) throws ServletException {
	}

}
