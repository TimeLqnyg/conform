package cn.zjh.dubboserviceconsumer.controller;

import cn.zjh.dubbodemo.api.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.dubboserviceconsumer.controller
 * @date:2019/10/22
 */
@RestController
public class HelloController {

    @Reference(timeout = 3000,
            version = "1.0.2",
            stub = "cn.zjh.dubbodemo.api.service.stub.HelloServiceStub",
            loadbalance = "roundrobin") //负载均衡
    private HelloService helloService;

    @RequestMapping(value = "/sayHello",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public String sayHello(@RequestParam("msg") String msg){
        return helloService.sayHello(msg);
    }

}
