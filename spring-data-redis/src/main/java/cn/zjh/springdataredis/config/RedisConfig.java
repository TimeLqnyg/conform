package cn.zjh.springdataredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		//RedisSerializer
		RedisSerializer serializer=new GenericJackson2JsonRedisSerializer();
		RedisSerializer keySerializer=new StringRedisSerializer();
		// 如果keySerializer是空的话 就是DefaultSerializer  对ValueSerializer也一样
		template.setDefaultSerializer(serializer);
		template.setValueSerializer(keySerializer);
		return template;
	}
}
