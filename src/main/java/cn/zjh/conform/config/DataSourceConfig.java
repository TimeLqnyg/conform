//package cn.zjh.conform.config;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Conditional;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jndi.JndiObjectFactoryBean;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//
//import javax.sql.DataSource;
//
//import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;
//
//@Configuration
////@Profile("prod")
//public class DataSourceConfig {
//
//	/**
//	 * Spring Boot 已经实现了自动加载 DataSource 的相关配置。我们只需要在项目中引用相关依赖，
//	 * 在配置文件 application.yaml  中配置相关属性即可。相关相关源码入口为 
//	 * org.springframework.boot.autoconfigure.jdbc 包下的 DataSourceAutoConfiguration 配置类。
//	 */
//	//	@Bean(destroyMethod ="shutdown")
////	@Conditional
////	@Bean
////	@ConditionalOnMissingBean //仅仅在当前上下文中不存在某个对象时
////	public DataSource dataSource() {
////		DataSource db;
////		//region
////		//内存数据源  嵌入式数据源
////		db = new EmbeddedDatabaseBuilder()
////				.generateUniqueName(true)
////				.setType(H2)
////				.setScriptEncoding("UTF-8")
////				.ignoreFailedDrops(true)
////				.addScript("schema.sql")
////				.addScripts("user_data.sql", "country_data.sql")
////				.build();
////		//endregion
////
////		return db;
////	}
//
//	//这个有点问题
////	@Bean
////	public JndiObjectFactoryBean dataSource_jndi(){
////		JndiObjectFactoryBean jndiObjectFactoryBean=new JndiObjectFactoryBean();
////		jndiObjectFactoryBean.setJndiName(""); //
////		jndiObjectFactoryBean.setResourceRef(true); //如果在java应用服务中，设置这个会给jndiName自动添加"java:comp/env/"前缀
////		jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
////
////		return jndiObjectFactoryBean;
////	}
////
//
////	@Bean
////	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
////			DataSource dataSource, JpaVendorAdapter jpaVendorAdapter){
////		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean=new LocalContainerEntityManagerFactoryBean();
////		entityManagerFactoryBean.setDataSource(dataSource);
////		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
////		return entityManagerFactoryBean;
////	}
//
//
//}
