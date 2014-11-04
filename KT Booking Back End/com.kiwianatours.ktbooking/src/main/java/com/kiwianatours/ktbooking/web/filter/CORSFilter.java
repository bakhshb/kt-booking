package com.kiwianatours.ktbooking.web.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		
		// *(allow from all servers) OR http://crunchify.com/ OR http://example.com/
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		// As a part of the response to a request, which HTTP methods can be used during the actual request.
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		
		// How long the results of a request can be cached in a result cache.
		response.setHeader("Access-Control-Max-Age", "3600");
		
		// As part of the response to a request, which HTTP headers can be used during the actual request.
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) {}

	@Override
	public void destroy() {}

}
