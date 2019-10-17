package cn.zjh.simplewebsocket.service.rabbitmq.work;

import cn.zjh.simplewebsocket.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.service.rabbitmq.work
 * @date:2019/10/17
 */
public class Recv {
    private final static String QUEUE_NAME = "work";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = ConnectionUtils.getConnection();
        Channel channel = factory.newConnection().createChannel();
        DeliverCallback deliverCallback=(customerTag,deliver)->{
            String message = new String(deliver.getBody(),"UTF-8");
            System.out.println("Recv message:"+message);
            try{
                Thread.sleep(2000);
            }catch (Exception e){

            }finally {
                System.out.println("Done");
            }
        };

        Boolean autoAct=true;
        channel.basicConsume(QUEUE_NAME,autoAct,deliverCallback,consumerTag->{});
    }


}
