package cn.zjh.webflux.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.webflux.model
 * @date:2019/11/21
 */
@Document(collation = "user")
@Data
public class User {

    @Id
    private String id;

    private String name;

    private int age;
}
