package cn.zjh.conform.config;

import cn.zjh.conform.model.Book;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;

@Configuration
public class MyRedisConfig  extends CachingConfigurerSupport {

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		GenericJackson2JsonRedisSerializer serializer=new GenericJackson2JsonRedisSerializer();
		template.setDefaultSerializer(serializer);
		return template;
	}

	private RedisSerializer<String> keySerializer() {
		return new StringRedisSerializer();
	}

	//使用Jackson序列化器

	private RedisSerializer valueSerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		//缓存配置对象
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

		redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofMinutes(30L)) //设置缓存的默认超时时间：30分钟
				.disableCachingNullValues()             //如果是空值，不缓存
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))         //设置key序列化器
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()));  //设置value序列化器

		return RedisCacheManager
				.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
				.cacheDefaults(redisCacheConfiguration).build();
	}


	@Override
	@Bean
	public KeyGenerator keyGenerator() {
		// TODO Auto-generated method stub
		return new KeyGenerator() {
			@Override
			public Object generate(Object object, Method method, Object... objects) {
				// TODO Auto-generated method stub
				StringBuilder sb = new StringBuilder();
				sb.append(object.getClass().getName());
				sb.append(method.getName());
				for (Object obj : objects) {
					if (obj != null) {
						sb.append(obj.toString());
					}
				}
				return sb.toString();
			}
		};
	}
}
