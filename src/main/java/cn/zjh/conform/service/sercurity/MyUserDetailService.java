package cn.zjh.conform.service.sercurity;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {

	/**
	 * 读取用户信息
	 * @param s
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

		//UserDetails 封装用户数据的接口
		User user=new User("user",new BCryptPasswordEncoder().encode("123456"),
				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN"));


		return user;
	}
}
