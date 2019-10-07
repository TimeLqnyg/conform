package cn.zjh.conform.servlet;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = "myServlet") //servletNames 是servlet中的name
//@WebFilter(urlPatterns = "/myServlet")
public class MyFilter  extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		ServletContext servletContext=null;
		servletContext=request.getServletContext();
//		servletContext=getServletContext();
		String url=request.getRequestURI();

		servletContext.log("/myServlet was filtered");
		filterChain.doFilter(request,response); //filter链

	}
}
