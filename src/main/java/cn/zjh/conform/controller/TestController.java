package cn.zjh.conform.controller;

import cn.zjh.conform.dao.BookMapper;
import cn.zjh.conform.model.Book;
import cn.zjh.conform.service.AOPService;
import cn.zjh.conform.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

//@RestController
@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;

	@Autowired
	private AOPService aopService;

	@RequestMapping("/queryById/{Id}")
	public Book queryByid(@PathVariable("Id") Integer Id){
//		System.out.println("查询书本");
  		return testService.queryBookById(Id);
	}

	@RequestMapping("/getBookByName/{Name}")
	public Book getBookByName(@PathVariable("Name") String Name){
		return testService.getBookByName(Name);
	}

	@RequestMapping("/deleteBook/{Id}")
	public String deleteBook(@PathVariable Integer Id){
		testService.deleteBook(Id);
		return "success";
	}

	@RequestMapping("aopTest")
	public String aopTest(){
		String test=aopService.perform();
		System.out.println(test);
		return test;
	}

	@RequestMapping("aopTest02")
	public String aopTest02(Integer num,String msg){
		return aopService.perform(num,msg);
	}

	/**
	 * 上传multipart形式的文件
	 *  不知道怎么模拟
	 */
	@RequestMapping(value = "processRegistration",method = RequestMethod.POST)
	public void processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture){
		try {
			profilePicture.transferTo(new File("classpath:/static/images" + profilePicture.getOriginalFilename()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 这个如果用@RestController返回的就是字符串数据
	 * 要是thymeleaf模板生效就要用@Controller
	 * @return
	 */
	@RequestMapping({"/","/hello"})
	public String hello(){
		return "index";
	}

}
