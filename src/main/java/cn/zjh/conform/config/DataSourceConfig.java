package cn.zjh.conform.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
@Profile("prod")
public class DataSourceConfig {

//	@Bean(destroyMethod ="shutdown")
//	@Conditional
	@Bean
	@ConditionalOnMissingBean //仅仅在当前上下文中不存在某个对象时
	public DataSource dataSource(){
		EmbeddedDatabase db=new EmbeddedDatabaseBuilder()
				.generateUniqueName(true)
				.setType(H2)
				.setScriptEncoding("UTF-8")
				.ignoreFailedDrops(true)
				.addScript("schema.sql")
				.addScripts("user_data.sql", "country_data.sql")
				.build();
		return db;
	}
}
