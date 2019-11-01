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
	@Query(value = "select id from authority where username=:username and authority=:authority",nativeQuery = true)
	Integer findByUserAndAuthority(@Param("username") String username, @Param("authority") String authority);

	@Query("select id from Authority where username=:username")
	List<Integer> findAllByUsername(@Param("username") String username);

	//查询参数是对象
	@Query("select id from Authority where username=:#{authority.username}")
	Integer findByRoot(@Param("authority") Authority authority);

}
