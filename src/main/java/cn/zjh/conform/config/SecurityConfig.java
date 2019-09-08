package cn.zjh.conform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

/**
 * 参数解析器 argument resolver
 */

@Configuration
@EnableWebSecurity//启用web安全性
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	/**
	 * 这个是没有用户的 所以没有人能登陆成功
	 * @param http
	 * @throws Exception
	 */
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
////		super.configure(http);
//		http.authorizeRequests()
//				.anyRequest().authenticated() //所以的http请求都要经过认证
//				.and()
//				.formLogin().and()
//				.httpBasic();
//
//	}

	/**
	 *
	 */

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		super.configure(auth);
//		/**
//		 * .role("USER")和.authorities("ROLE_USER")一样
//		 */
//		//在内存中维护用户存储
//		auth.inMemoryAuthentication()
//				.passwordEncoder(new BCryptPasswordEncoder())
//				.withUser("user").password(new BCryptPasswordEncoder().encode("password"))
//				.roles("USER").and()
//				.withUser("admin").password(new BCryptPasswordEncoder().encode("password"))
//				.roles("USER","ADMIN");
//


//		 在数据库中维护用户存储
//		auth.jdbcAuthentication()
//				.dataSource(dataSource)
//				.usersByUsernameQuery("select username,password,enabled from user where username=?")
//				.authoritiesByUsernameQuery("select username,'ROLE_USER' from authorities where username=?")
//				.passwordEncoder(new BCryptPasswordEncoder()); // 这样的话要在数据库中存储BCrypt加密的数据


		// 基于LDAP的认证 LDAP服务器 ldif文件
//		auth.ldapAuthentication()
//				.userSearchFilter("(uid={0})")
//				.groupSearchFilter("member={0}");


//	}


}
