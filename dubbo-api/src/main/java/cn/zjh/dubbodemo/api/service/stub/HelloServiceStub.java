package cn.zjh.dubbodemo.api.service.stub;

import cn.zjh.dubbodemo.api.service.HelloService;
import org.springframework.util.StringUtils;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.dubboserviceconsumer.service
 * @date:2019/10/22
 */

public class HelloServiceStub implements HelloService {

    private final HelloService helloService;

    public HelloServiceStub(HelloService helloService){
        this.helloService=helloService;
    }

    @Override
    public String sayHello(String msg) {
        if(!StringUtils.isEmpty(msg)){
            System.out.println("stub..");
            return helloService.sayHello(msg);
        }else {
            return "no msg";
        }

    }
}
