package cn.zjh.conform.servlet;


import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;


/**
 * listener组件是最早的组件 request最早就是通过这个组件初始化
 */

@WebListener
public class MyServletRequestListener implements ServletRequestListener {

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request=(HttpServletRequest)sre.getServletRequest();

		ServletContext servletContext=request.getServletContext();

		servletContext.log("request was initialized");

	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		HttpServletRequest request=(HttpServletRequest)sre.getServletRequest();

		ServletContext servletContext=request.getServletContext();

		servletContext.log("request was destroyed");
	}


}
