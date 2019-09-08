package cn.zjh.conform.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {

//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//
//		DispatcherServlet dispatcherServlet=new DispatcherServlet();
//		ServletRegistration.Dynamic registration=servletContext.addServlet("appServlet",dispatcherServlet);
//		registration.addMapping("/");
//		registration.setMultipartConfig(new MultipartConfigElement("classpath:/static",2*1024*1024,4*1024*1024,0));
//	}

}
