package cn.zjh.simplewebsocket.service.rpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.rpc
 * @date:2019/10/18
 */
public class Client {
	public static <T> T getRemoteProxyObj(Class serviceInterface, InetSocketAddress address) {

		//public Object invoke(Object proxy, Method method, Object[] args)
		//返回动态代理对象
		return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class[]{serviceInterface},
				(proxy, method, args) -> {
					/*
					 * 客户端向服务端发送请求 请求某一个具体的接口
					 * 服务端返回接口的执行结果
					 */
					//发送    OutputStream
					//请求    InputStream
					Socket socket = new Socket();
					socket.connect(address);
					//发送序列化流
					try (ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
						 ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());){
						//发送的东西  接口名，方法名：writeUTF
						outputStream.writeUTF(serviceInterface.getName());
						outputStream.writeUTF(method.getName());
						//方法的参数类型 和 具体参数
						outputStream.writeObject(method.getParameterTypes());
						outputStream.writeObject(args);
						//等待服务端处理

						return inputStream.readObject(); //客户端拿到服务端的返回值
					}catch (Exception e){
						return null;
					}
				});
	}
}
