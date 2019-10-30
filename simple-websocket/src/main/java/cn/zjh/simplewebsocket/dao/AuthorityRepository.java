package cn.zjh.simplewebsocket.dao;

import cn.zjh.simplewebsocket.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaSpecificationExecutor<Authority>,JpaRepository<Authority,Integer> {
	@Query("select id from Authority where username=:username and authority=:authority")
	Integer findByUserAndAuthority(@Param("username") String username, @Param("authority") String authority);
}
