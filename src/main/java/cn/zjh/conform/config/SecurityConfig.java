package cn.zjh.conform.config;

import cn.zjh.conform.service.sercurity.ImageCodeAuthenticationFilter;
import cn.zjh.conform.service.sercurity.MyAuthenticationFailureHandler;
import cn.zjh.conform.service.sercurity.MyAuthenticationSuccessHandler;
import cn.zjh.conform.service.sercurity.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.sql.DataSource;

/**
 * 参数解析器 argument resolver
 */

@Configuration
@EnableWebSecurity//启用web安全性
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;


//	@Bean
//	public MyUserDetailService myUserDetailService(){
//		return new MyUserDetailService();
//	}

	@Autowired
	MyUserDetailService myUserDetailService;

	@Autowired
	MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

	@Autowired
	MyAuthenticationFailureHandler myAuthenticationFailureHandler;

//	@Autowired
//	ImageCodeAuthenticationFilter imageCodeAuthenticationFilter;

	//https://docs.spring.io/spring-security/site/docs/5.2.0.RC1/reference/htmlsingle/#remember-me-overview
//	@Bean
//	public AbstractAuthenticationProcessingFilter imageCodeAuthenticationFilter(){
////		ImageCodeAuthenticationFilter imageCodeAuthenticationFilter=new ImageCodeAuthenticationFilter();
////		imageCodeAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
//		return null;
//	}

	/**
	 *
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
		http
                .authorizeRequests()
				.antMatchers("/404").permitAll()
				.antMatchers("/userLogin","/logout").permitAll()
				.antMatchers("/imgs/*").permitAll()
				.anyRequest().authenticated() //所以的http请求都要经过认证
				.and()
				.formLogin()
				.loginPage("/userLogin")
				//登录请求地址 即login页面表单action地址
				.loginProcessingUrl("/login")
				.successHandler(myAuthenticationSuccessHandler)
				.failureHandler(myAuthenticationFailureHandler)
//                .defaultSuccessUrl("/index")
				.failureUrl("/404")
				.and()
				.httpBasic()
				.and()
				.rememberMe()
				.and()
				.logout().permitAll().invalidateHttpSession(true)
				.logoutSuccessUrl("/userLogin").logoutUrl("/logout")//<a th:href="@{/logout}">Logout</a>
				.and()
//				.addFilterAt(imageCodeAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
				.csrf().disable();//th:action 可以生成csrf域  添加CSRF将更新LogoutFilter以仅使用HTTP POST。
//                .and()
//				.httpBasic();
				;



	}

	/**
	 *
	 */


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		super.configure(auth);
		/**
		 * .role("USER")和.authorities("ROLE_USER")一样
		 */
		//在内存中维护用户存储
//		auth.inMemoryAuthentication()
//				.passwordEncoder(new BCryptPasswordEncoder())
//				.withUser("user").password(new BCryptPasswordEncoder().encode("123456"))
//				.roles("USER");
//              .and()
//				.withUser("admin").password(new BCryptPasswordEncoder().encode("password"))
//				.roles("USER","ADMIN");

		auth.userDetailsService(myUserDetailService).passwordEncoder(new BCryptPasswordEncoder());

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


	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("./imgs/*");
	}
}
