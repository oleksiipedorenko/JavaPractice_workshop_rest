package ua.skillsup.practice.restworkshop.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
public class LoggingFilter implements Filter {
	private final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public void doFilter(ServletRequest servletRequest,
	                     ServletResponse servletResponse,
	                     FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse resp = (HttpServletResponse) servletResponse;
		logger.info("Log request: {} - {}", req.getMethod(), req.getRequestURI());
		filterChain.doFilter(servletRequest, servletResponse);
		logger.info("Log response: {}", resp.getContentType());
	}
}