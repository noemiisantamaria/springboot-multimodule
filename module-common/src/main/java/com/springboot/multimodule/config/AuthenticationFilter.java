package com.springboot.multimodule.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
	private static final String ORIGIN = "Origin";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("########## Initiating Authentication filter ##########");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse resp = (HttpServletResponse) servletResponse;

		logger.info("Logging Request {} : {}", req.getMethod(), req.getRequestURI());
		logger.info("ORIGIN: {}", req.getHeader("origin"));

		if (req.getHeader(ORIGIN) != null && !req.getHeader(ORIGIN).isEmpty()) {
			resp.setHeader("Access-Control-Allow-Origin", req.getHeader(ORIGIN));
			resp.setHeader("Access-Control-Allow-Methods", "DELETE, GET, OPTIONS, POST, PUT");
			resp.setHeader("Access-Control-Allow-Credentials", "true");
			resp.setHeader("Access-Control-Allow-Headers", "content-type");
		}
		// call next filter in the filter chain
		filterChain.doFilter(req, resp);

		logger.info("Logging Response: {}", resp.getContentType());
	}

}
