package cn.zjh.conform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;

@Configuration
public class MultipartConfig {

//	/**
//	 * standardServletMultipartResolver 解析器
//	 * @return
//	 * @throws IOException
//	 */
//	@Bean
//	public MultipartResolver multipartResolver() throws IOException {
//		return new StandardServletMultipartResolver();
//	}
//
//	/**
//	 * viewResolver
//	 * @return
//	 */
//	@Bean
//	public InternalResourceViewResolver internalresource()
//	{
//		InternalResourceViewResolver internalresource1 = new InternalResourceViewResolver();
//		internalresource1.setPrefix("/WEB-INF/JSP/");
//		internalresource1.setSuffix(".jsp");
//		return internalresource1;
//	}
}
