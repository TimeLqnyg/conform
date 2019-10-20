package cn.zjh.simplewebsocket.service.rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.rpc
 * @date:2019/10/18
 */

/**
 * 客户端向服务器发送请求可分为以下步骤：
 * 　　1.创建一个Socket实例
 * 　　2.利用I/O流与服务器进行通信
 * 　　3.关闭socket
 * <p>
 * 　　服务器接收客户端请求步骤：
 * 　　１.创建一个ServerSocket实例，监听客户端发来的请求。
 * 　　2.与客户端获取连接后，创建一个Socket实例，利用I/O流与客户端进行通信，完毕后关闭Socket。
 */
public class ServerCenter implements Server {

	private static HashMap<String, Class> map = new HashMap<>();
	private static int port = 9999; //
	//连接池  获取cpu处理器的性能 来设置连接池的大小
	private static ExecutorService executor=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	//这个服务中 是否开启
	private static boolean isRunning=false;

	public ServerCenter(int port) {
		this.port = port;
	}

	@Override
	public void start() {

		ServerSocket serverSocket=null;
		try {
			//Socket socket=new Socket(); //客户端socket
			 serverSocket = new ServerSocket();
			//绑定端口
			serverSocket.bind(new InetSocketAddress(port));
		} catch (IOException e) {
			e.printStackTrace();
		}

		isRunning=true;
		while (true){
			try {
				Socket socket = serverSocket.accept();//等待客户端连接
				executor.execute(new ServiceTask(socket )); //启动线程 处理客户端请求
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stop() {
		isRunning=false;
		executor.shutdown();
	}

	@Override
	public void register(Class service, Class serviceImpl) {
		map.put(service.getName(), serviceImpl);
	}

	private static class ServiceTask implements Runnable{

		private Socket socket=null;
		public ServiceTask(Socket socket){
			this.socket=socket;
		}

		@Override
		public void run() {
			ObjectInputStream inputStream=null;
			ObjectOutputStream outputStream=null;
			try {
				//InputStream 获取请求
				inputStream = new ObjectInputStream(socket.getInputStream());
				// 因为ObjectInputStream 对发送数据的顺序严格要求 所以需要先发送先接受
				String serviceName = inputStream.readUTF();
				String methodName = inputStream.readUTF();
				Class[] parameterTypes = (Class[]) inputStream.readObject();
				Object[] arguments = (Object[]) inputStream.readObject();
				//根据客户请求 到map中找到具体的接口
				Class serviceClass = map.get(serviceName);
				Method method = serviceClass.getMethod(methodName, parameterTypes);
				//执行方法
				Object result = method.invoke(serviceClass.newInstance(), arguments); //对象加参数

				//返回方法的返回值 发送给客户端
				outputStream=new ObjectOutputStream(socket.getOutputStream());
				outputStream.writeObject(result);


			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try{
					if(outputStream!=null)outputStream.close();
					if(inputStream!=null)inputStream.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
