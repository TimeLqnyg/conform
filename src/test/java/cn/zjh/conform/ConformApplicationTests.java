package cn.zjh.conform;

import cn.zjh.conform.controller.TestController;
import cn.zjh.conform.dao.AuthoritiesRepository;
import cn.zjh.conform.dao.UserMapper;
import cn.zjh.conform.model.Article;
import cn.zjh.conform.model.Authorities;
import cn.zjh.conform.model.Book;
import cn.zjh.conform.model.User;
import cn.zjh.conform.service.TestService;
import cn.zjh.conform.service.autoBean.AutoBeanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConformApplicationTests {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	private TestService testService;

	@Autowired
	private AutoBeanService autoBeanService;

	@Autowired
	private TestController testController;

//	@Autowired
//	JestClient jestClient;

	@Test
	public void contextLoads() {
	}

	/**
	 * 缓存原理：
	 * 		CacheManager===Cache 缓存组件来实际给缓存中存取数据
	 */
	@Test
	public void test01(){
//		stringRedisTemplate.opsForValue().append("msg","String类型的redis");
//		redisTemplate.opsForValue().set("object_key","object_value");
		String msg=stringRedisTemplate.opsForValue().get("msg");
		System.out.println(msg);
		Book book=testService.queryBookById(1);
		redisTemplate.opsForValue().set("book",book);
	}

	@Test
	public void test04(){
//		Book book=(Book) redisTemplate.opsForValue().get("book");

		System.out.println(redisTemplate.opsForValue().get("book"));
	}

	/**
	 * 这个结果返回的是一样的 所以本质上来讲这两个bean是同一个实例
	 */
	@Test
	public void test02(){
		testService.beanHash();
		autoBeanService.beanHash();
		testService.setName();
		System.out.println(testService.getName());
		System.out.println(autoBeanService.getName());
		autoBeanService.setName();
		System.out.println(testService.getName());
		System.out.println(autoBeanService.getName());
	}


	/**
	 * mock模拟请求
	 * @throws Exception
	 */
	@Test
	public void test03() throws Exception {

		MockMvc mockMvc= MockMvcBuilders.standaloneSetup(testController).build();
		mockMvc.perform(get("/test/aopTest"));
	}
//
//	@Test
//	public void contextLoad(){
//		Article article=new Article();
//		article.setId(1);
//		article.setAuthor("zhangsan");
//		article.setTitle("好消息");
//		article.setContent("hello world");
//
//		//构建一个索引
//		Index index = new Index.Builder(article).index("zjh").type("news").build();
//		try {
//			jestClient.execute(index);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public void Test04(){
		System.out.println("-----------------");
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testUpdatePassword(){
		User user=new User();
		user.setUsername("user");
		PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode("123456") );
		userMapper.updateUserPassword(user);
	}

	@Autowired
	private AuthoritiesRepository authoritiesRepository;

	@Test
	public void testJPA(){

		Integer list=authoritiesRepository.findByUserAndAuthority("zhangsan","ROLE_USER");
//		list.forEach(e-> System.out.println(e.getUsername()));
		System.out.println(list);
	}
}

