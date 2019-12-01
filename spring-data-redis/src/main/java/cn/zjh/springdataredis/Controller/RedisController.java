package cn.zjh.springdataredis.Controller;

import cn.zjh.springdataredis.service.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private RedisCache redisCache;

	@GetMapping("/test")
	public String test(String key){
		return redisCache.redisString(key);
	}
}
