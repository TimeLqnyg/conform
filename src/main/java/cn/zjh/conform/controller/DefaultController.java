package cn.zjh.conform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/userLogin")
    public String login() {
        return "login";
    }

    @GetMapping("/404")
    public String fail() {
        return "404";
    }
}
