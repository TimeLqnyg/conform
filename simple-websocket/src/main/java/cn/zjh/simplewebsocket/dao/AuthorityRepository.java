package cn.zjh.simplewebsocket.dao;

import cn.zjh.simplewebsocket.model.Authority;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaSpecificationExecutor<Authority>,JpaRepository<Authority,Integer> {
//	@Query(value = "select id from authority where username=:username and authority=:authority",nativeQuery = true)
	Authority findByUserIdAndAuthority(@Param("userId") Integer userId, @Param("authority") String authority);

	@Query(value = "select id from authority where username=:username",nativeQuery = true)
	List<Integer> findAllByUsername(@Param("username") String username);

	//查询参数是对象
	@Query(value = "select id from authority where username=:#{#authority.username}",nativeQuery = true)
	Integer findByRoot( @Param("authority") Authority authority);

}
