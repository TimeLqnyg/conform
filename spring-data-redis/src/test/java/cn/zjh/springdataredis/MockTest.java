package cn.zjh.springdataredis;

import cn.zjh.springdataredis.service.RedisCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockTest {
	@MockBean
	private RedisCache redisCache;

	@Autowired
	protected MockMvc mockMvc;

	@Test
	public  void testMock() throws Exception {

		//不运行实际的代码 但确保返回的是对的
		//stub 就是说在调用这个方法的时候会返回这个值
//		String value=null;
		Mockito.when(redisCache.redisString("test")).thenReturn("test name");

		//然后调用的时候就返回name了
		String result=redisCache.redisString("test");
		System.out.println(result);

		//这里调用的也是上面模拟的数据
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/redis/test")
				.param("key","test"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();

		System.out.println(mvcResult);

	}
}
