package cn.zjh.conform.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 使用WebMvcConfigurer扩展springmvc的功能
 * 		在application.yml中spring.mvc中配置 不用写这个
 */

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

}
