package cn.zjh.conform.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(
		name = "myServlet",
		urlPatterns = "/myServlet",
		initParams = {@WebInitParam(name = "myName",value = "myValue")})

public class MyServlet extends HttpServlet {

	private String value;

	public  void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		value=servletConfig.getInitParameter("myName");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Writer writer=response.getWriter();
		writer.write("my value="+value);
		super.doGet(request, response);
	}

	@Override
	public String getInitParameter(String name) {
		return super.getInitParameter(name);
	}
}
