package cn.zjh.springdataredis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootTest
class SpringDataRedisApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	ZSetOperations<String,String> operations=null;

	@BeforeEach
	public void init(){
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		operations = redisTemplate.opsForZSet();
	}

	@Test
	void contextLoads() {

	}

	@Test
	public void addZSet(){
		operations.add("students","zhangsan",100);
		operations.add("students","lisi",80);
		operations.add("students","wangwu",60);
	}

	@Test
	public void findZSet(){
		System.out.println(operations.score("students", "zhangsan"));
	}

	@Test
	public void testCache(){
		stringRedisTemplate.opsForValue().set("test","test");
	}

}
