package cn.zjh.webflux.repository;

import cn.zjh.webflux.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.webflux.repository
 * @date:2019/11/21
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
