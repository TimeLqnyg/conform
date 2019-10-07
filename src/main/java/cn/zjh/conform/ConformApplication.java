package cn.zjh.conform;

import cn.zjh.conform.springboot.MyServlet2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * RedisCacheManager
 */

/**
 * rabbitmq自动配置原理:
 * 		RabbitAutoConfiguration: static内部类
 * 		RabbitTemplate
 * 		AmqpAdmin: rabbitMQ的系统管理组件
 */

/**
 * SpringBoot默认支持两种技术和ES交互
 * 	1、Jest
 * 	2、SpringData ElasticSearch
 */

@SpringBootApplication
@MapperScan("cn.zjh.conform.dao")
@EntityScan("cn.zjh.conform.model")
@ServletComponentScan("cn.zjh.conform.servlet")
@EnableCaching
@EnableAspectJAutoProxy
//@EnableWebSecurity //启用Web安全性
public class ConformApplication {

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConformApplication.class, args);
	}

	@Bean
	public static ServletRegistrationBean servletRegistrationBean(){
		ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new MyServlet2());
		servletRegistrationBean.addUrlMappings("/spring-boot/myservlet");
		servletRegistrationBean.addInitParameter("myname","myvalue");
		return servletRegistrationBean;
	}
}

