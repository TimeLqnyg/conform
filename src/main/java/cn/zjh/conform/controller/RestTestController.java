package cn.zjh.conform.controller;

import cn.zjh.conform.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.MediaTray;

@RestController
@RequestMapping("rest")
public class RestTestController {

    @Bean
     Role getRole(){
        Role role=new Role();
        role.setId(1);
        role.setRoleDesc("用户");
        role.setRoleName("USER");
        return role;
    }

    @Autowired
    @Qualifier("getRole")
    private Role role;

//    @RequestMapping(value = "/restXml")
    @RequestMapping(value = "/restXml",
//            consumes = MediaType.TEXT_HTML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public Role restXml(){
        return role;
    }

    @RequestMapping(value = "/restXml/setRoleName",
//            consumes = MediaType.TEXT_HTML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public Role setRoleName(){
        return role;
    }
}
