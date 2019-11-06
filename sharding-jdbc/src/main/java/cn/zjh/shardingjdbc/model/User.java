package cn.zjh.shardingjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.shardingjdbc.model
 * @date:2019/11/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private Integer cityId;
    private Date createTime;
    private Integer sex;
}
