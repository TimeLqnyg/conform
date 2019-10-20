package cn.zjh.simplewebsocket.service.rpc.test;

import cn.zjh.simplewebsocket.service.rpc.server.Server;
import cn.zjh.simplewebsocket.service.rpc.server.ServerCenter;
import cn.zjh.simplewebsocket.service.rpc.server.service.HelloService;
import cn.zjh.simplewebsocket.service.rpc.server.service.HelloServiceImpl;

public class RPCServerTest {

	public static void main(String[] args) {
		Server server=new ServerCenter(9999);
		server.register(HelloService.class,HelloServiceImpl.class);
		server.start();
	}
}
