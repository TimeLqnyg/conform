package cn.zjh.conform.service;

import cn.zjh.conform.dao.BookMapper;
import cn.zjh.conform.model.Book;
import cn.zjh.conform.service.autoBean.IceCream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	@Autowired
	private BookMapper bookMapper;

	@Autowired
	private IceCream iceCream;

	@Qualifier("cacheManager")
	@Autowired
	RedisCacheManager cacheManager; //好像不能用private
	/**
	 * Cacheable 属性key支持spEL表达式
	 * @param id
	 * @return
	 */
	@Cacheable(value = "book",key = "#id")
	public Book queryBookById(Integer id){
//		System.out.println("查询Book");
//		System.out.println("debug调试");
		return bookMapper.queryBookById(id);
	}

	// 用非注解的方式实现 (编码方式)

	/**
	 *
	 * @param id
	 */

	@CacheEvict(value = "book",key = "#id")
	public void deleteBook(Integer id){
		System.out.println("删除缓存"+id);
	}

	//value 即 cacheNames
	@Caching(
			cacheable = {
					@Cacheable(value = "book",key = "#bookName")
			},
			put = {
					@CachePut(value = "book",key = "#result.id"),
					@CachePut(value = "book",key = "#result.author")
			}
	)
	public Book getBookByName(String bookName){
		System.out.println("测试Caching注解");
		return bookMapper.getBookByName(bookName);
	}

	public void beanHash(){
		System.out.println(iceCream.hashCode());
	}

	public void setName(){
		iceCream.setName("iceCream");
	}

	public String getName(){
		return iceCream.getName();
	}
}
