package cn.zjh.conform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages ="cn.zjh.conform.dao")
public class JpaConfiguration {

}
