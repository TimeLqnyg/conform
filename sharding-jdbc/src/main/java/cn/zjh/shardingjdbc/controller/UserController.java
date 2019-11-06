package cn.zjh.shardingjdbc.controller;

import cn.zjh.shardingjdbc.dao.UserMapper;
import cn.zjh.shardingjdbc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.shardingjdbc.controller
 * @date:2019/11/6
 */

@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user/save")
    @ResponseBody
    public String save() {
        for (int i = 0; i <10 ; i++) {
            User user=new User();
            user.setName("test"+i);
            user.setCityId(1%2==0?1:2);
            user.setCreateTime(new Date());
            user.setSex(i%2==0?1:2);
            user.setPhone("11111111"+i);
            user.setEmail("xxxxx");
            user.setCreateTime(new Date());
            user.setPassword("eeeeeeeeeeee");
            userMapper.save(user);
        }

        return "success";
    }

    @RequestMapping("/user/get/{id}")
    @ResponseBody
    public User get(@PathVariable Long id) {
        User user =  userMapper.get(id);
        System.out.println(user.getId());
        return user;
    }
}
