package cn.zjh.springdataredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCache {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public String redisString(String key){
		String str = stringRedisTemplate.opsForValue().get(key);
		//mock不会调用实际函数
		System.out.println("调用实际的函数");
		return str;

	}

}
