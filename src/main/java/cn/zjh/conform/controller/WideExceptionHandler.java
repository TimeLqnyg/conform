package cn.zjh.conform.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 控制器通知 @ControllerAdvice
 */

@ControllerAdvice
public class WideExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String duplicateTestHandler(){
		return "Test Duplication Exception Handle";
	}
}
