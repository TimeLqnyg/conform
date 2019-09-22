package cn.zjh.conform.dao;

import cn.zjh.conform.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaSpecificationExecutor<Authorities>,JpaRepository<Authorities,Integer> {
	@Query("select id from Authorities where username=:username and authority=:authority")
	Integer findByUserAndAuthority(@Param("username") String username, @Param("authority") String authority);
}
