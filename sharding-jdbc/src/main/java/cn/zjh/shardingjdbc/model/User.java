package cn.zjh.shardingjdbc.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.DateCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.StringCodec;
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
    @JSONField(deserializeUsing = LongCodec.class)
    private Long id;
//    @JSONField(name = "user_name")
    private String name;
    private String phone;
    private String email;
    private String password;
    @JSONField(name = "city_id")
    private Integer cityId;
    @JSONField(name = "create_time")
    private Date createTime;
    private Integer sex;
}
