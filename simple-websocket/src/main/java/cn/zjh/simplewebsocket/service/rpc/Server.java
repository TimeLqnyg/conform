package cn.zjh.simplewebsocket.service.rpc;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.rpc
 * @date:2019/10/18
 */
public interface Server {

    public void start();
    public void stop();
    public void register(Class service,Class serviceImpl);
}
