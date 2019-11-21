package cn.zjh.webflux.controller;

import cn.zjh.webflux.model.User;
import cn.zjh.webflux.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.webflux.controller
 * @date:2019/11/21
 */

@RestController
@RequestMapping("/user")
public class UserController {

    //这样直接注入比写注解的耦合度低
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @GetMapping("/")
    public Flux<User> getAll(){
        return userRepository.findAll();
    }

    @GetMapping(value = "/stream/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll(){
        return userRepository.findAll();
    }

    /**
     * 添加数据
     * @param user
     * @return
     */
    @PostMapping("/")
    public Mono<User> addUser(@RequestBody User user){
        return userRepository.save(user);
    }


}
