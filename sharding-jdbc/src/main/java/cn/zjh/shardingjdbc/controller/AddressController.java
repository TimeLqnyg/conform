package cn.zjh.shardingjdbc.controller;

import cn.zjh.shardingjdbc.dao.AddressMapper;
import cn.zjh.shardingjdbc.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.shardingjdbc.controller
 * @date:2019/11/6
 */
@Controller
public class AddressController {
    @Autowired
    private AddressMapper addressMapper;

    @RequestMapping("/address/save")
    @ResponseBody
    public String save() {
        for (int i = 0; i <10 ; i++) {
            Address address=new Address();
            address.setCode("code_"+i);
            address.setName("name_"+i);
            address.setPid(i+"");
            address.setType(0);
            address.setLit(i%2==0?1:2);
            addressMapper.save(address);
        }

        return "success";
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("/address/get/{id}")
    @ResponseBody
    public Address get(@PathVariable Long id) {
        return addressMapper.get(id);
    }
}
