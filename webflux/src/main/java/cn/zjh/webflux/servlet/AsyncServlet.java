package cn.zjh.webflux.servlet;


import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@WebServlet(
		name = "asyncServlet",
		urlPatterns = "/asyncServlet",
		asyncSupported = true)
public class AsyncServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long time1 = System.currentTimeMillis();
		AsyncContext asyncContext=request.startAsync();
		CompletableFuture.runAsync(()->doSomething(asyncContext,asyncContext.getRequest(),asyncContext.getResponse()));

		System.out.println("耗时："+(System.currentTimeMillis()-time1));
	}

	private void doSomething(AsyncContext asyncContext,ServletRequest request, ServletResponse response) {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
		}

		try {
			response.getWriter().append("done");
		} catch (IOException e) {
			e.printStackTrace();
		}

		//通知异步线程完成操作
		asyncContext.complete();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
