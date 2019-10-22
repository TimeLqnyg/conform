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

@Service(methods = {@Method(name = "sayHello",timeout = 1000)},version = "1.0.1")
@Component
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String msg) {
        return "Old Hello "+msg;
    }
}
