package cn.zjh.simplewebsocket.dao.specifications;

import cn.zjh.simplewebsocket.model.Authority;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;

public class AuthoritySpecs {

	//目前来讲 只能查询
	//设置username
	public static Specification<Authority> setAuthority(Authority authority){
		return ((root, query, criteriaBuilder) -> {
			Predicate predicate=null;
			if(StringUtils.isEmpty(authority.getUsername())){
				predicate=criteriaBuilder.equal(root.get("username"),authority.getUsername());
				predicate = criteriaBuilder.and(predicate);
			}
			return predicate;

		});
	}
}
