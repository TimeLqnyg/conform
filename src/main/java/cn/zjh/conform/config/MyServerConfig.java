package cn.zjh.conform.config;

import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.conform.config
 * @date:2019/10/8
 */
@Configuration
public class MyServerConfig {

    //配置嵌入式servlet容器
    //servlet容器就是tomcat、jetty这些东西
    public WebServerFactoryCustomizer webServerFactoryCustomizer(){
        return  new WebServerFactoryCustomizer() {
            //定制嵌入式servlet容器相关的信息
            @Override
            public void customize(WebServerFactory factory) {

            }
        };
    }

    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>();
        return servletServletRegistrationBean;
    }
}
