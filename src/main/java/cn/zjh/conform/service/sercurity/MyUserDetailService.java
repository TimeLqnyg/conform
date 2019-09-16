package cn.zjh.conform.service.sercurity;

import cn.zjh.conform.dao.UserMapper;
import cn.zjh.conform.model.Permission;
import cn.zjh.conform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 读取用户信息
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//		UserDetails 封装用户数据的接口
//		User user=new User("user",new BCryptPasswordEncoder().encode("123456"),
//				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN"));

		User user=userMapper.findByUsername(username);

//		List<Permission> list=userMapper.findPermissionByUsername(username);
//
//		List<GrantedAuthority> grantedAuthorityList=new ArrayList<>();

		return user;
	}
}
