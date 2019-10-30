package cn.zjh.simplewebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages ="cn.zjh.simplewebsocket.dao")
public class JpaConfiguration {

}
