package cn.zjh.simplewebsocket.service.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.HashMap;

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
 *
 * 　　服务器接收客户端请求步骤：
 * 　　１.创建一个ServerSocket实例，监听客户端发来的请求。
 * 　　2.与客户端获取连接后，创建一个Socket实例，利用I/O流与客户端进行通信，完毕后关闭Socket。
 */
public class ServerCenter implements Server {

    private static HashMap<String,Class> map=new HashMap<>();
    private static int port=9999; //

    public ServerCenter(int port){
        this.port=port;
    }

    @Override
    public void start() {
        try {

//            Socket socket=new Socket(); //客户端socket
            ServerSocket serverSocket=new ServerSocket();
            //绑定端口
            serverSocket.bind(new InetSocketAddress(port));
            serverSocket.accept();  //等待客户端连接
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void register(Class service,Class serviceImpl) {
        map.put(service.getName(),serviceImpl);
    }
}
