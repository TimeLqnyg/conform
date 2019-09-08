package cn.zjh.conform.utils.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * 算是自定义异常8
 * @Exceptionhandler 处理同一个controller下的相同的异常
 * 当多个controller都会抛出某个特定异常解决方法
 * 		1、创建一个基础controller类 继承通用的@ExceptionHandler方法
 * 		2、控制器通知
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Test Not Found")
public class TestNotFundException extends RuntimeException {
}
