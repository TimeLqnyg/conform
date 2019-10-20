package cn.zjh.simplewebsocket.service.rpc.test;

import cn.zjh.simplewebsocket.service.rpc.client.Client;
import cn.zjh.simplewebsocket.service.rpc.server.service.HelloService;

import java.net.InetSocketAddress;

public class RPClientTest {
	public static void main(String[] args) {
		try {
//			Class HelloService=Class.forName("cn.zjh.simplewebsocket.service.rpc.serviceHelloService");
			HelloService service=Client.getRemoteProxyObj(
					Class.forName("cn.zjh.simplewebsocket.service.rpc.serviceHelloService"),
					new InetSocketAddress("127.0.0.1",9999));
			service.sayHello("公共接口？");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
