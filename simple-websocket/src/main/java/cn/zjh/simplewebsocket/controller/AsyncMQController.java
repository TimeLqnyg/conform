package cn.zjh.simplewebsocket.controller;

import cn.zjh.simplewebsocket.model.LogEntity;
import cn.zjh.simplewebsocket.service.async.AsyncSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.controller
 * @date:2019/11/28
 */

@RestController
@RequestMapping("/mq")
@Slf4j
public class AsyncMQController {
    @Autowired
    private AsyncSender asyncSender;

    @GetMapping("/test")
    private void TestAsyncMq(){

        try {
            long startTime = System.currentTimeMillis();
            //模拟业务处理 暂停五秒
            TimeUnit.SECONDS.sleep(5);
            LogEntity logEntity=new LogEntity("test","test");
            asyncSender.handleSysLog(logEntity);
            long time=startTime-System.currentTimeMillis();
            log.info("共花费{}毫秒",time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
