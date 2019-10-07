package cn.zjh.conform.springboot;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter2 extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		 doSomething();
		 filterChain.doFilter(request,response);
	}

	private void doSomething() {
		//不传入request  从RequestContextHolder中拿到attributes  在这中找request
		RequestAttributes requestAttributes=RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes)requestAttributes;
		HttpServletRequest request=servletRequestAttributes.getRequest();

	}
}
