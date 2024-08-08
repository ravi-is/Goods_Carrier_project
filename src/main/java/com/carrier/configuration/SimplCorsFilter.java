package com.carrier.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimplCorsFilter implements Filter{

	 @Override
	    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	        HttpServletResponse response = (HttpServletResponse) res;
	        HttpServletRequest request = (HttpServletRequest) req;
	        String originHeader = request.getHeader("Origin");

	        Map<String, String> map = new HashMap<>();
	        map.put("Access-Control-Allow-Origin", originHeader);
	        map.put("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
	        map.put("Access-Control-Max-Age", "3600");
	        map.put("Access-Control-Allow-Headers", "*");

	        map.forEach(response::setHeader);

	        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	            response.setStatus(HttpServletResponse.SC_OK);
	        } else {
	            chain.doFilter(req, res);
	        }
	    }

	    @Override
	    public void init(FilterConfig filterConfig) {
	    }

	    @Override
	    public void destroy() {
	    }

}
