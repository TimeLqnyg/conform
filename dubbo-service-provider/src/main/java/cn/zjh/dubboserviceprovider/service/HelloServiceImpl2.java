package cn.zjh.dubboserviceprovider.service;

import cn.zjh.dubbodemo.api.service.HelloService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.dubboserviceprovider.service
 * @date:2019/10/22
 */

@Service(methods = {@Method(name = "sayHello")},version = "1.0.2")
@Component
public class HelloServiceImpl2 implements HelloService {
    @Override
    public String sayHello(String msg) {
        System.out.println("provider...2");
        return "New Hello "+msg;
    }
}
