package com.ashokIt.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if ("/login".equals(request.getRequestURI()) || "/loginSubmit".equals(request.getRequestURI())) {
		
			return true;
		} else {
			if (request.getSession().getAttribute("user") != null) {
				return true;
			}
		}
		response.sendRedirect("/login");
		return false;
	}

}
