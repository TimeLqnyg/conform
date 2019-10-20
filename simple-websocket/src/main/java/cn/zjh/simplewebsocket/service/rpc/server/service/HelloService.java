package cn.zjh.simplewebsocket.service.rpc.server.service;

public interface HelloService {

	/**
	 *  客户端和服务端公共的接口???
	 * @param msg
	 * @return
	 */
	public String sayHello(String msg);
}
