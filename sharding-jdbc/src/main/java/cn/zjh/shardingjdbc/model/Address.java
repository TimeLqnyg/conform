package cn.zjh.shardingjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.shardingjdbc.model
 * @date:2019/11/6
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private Long id;
    private String code;
    private String name;
    private String pid;
    private Integer type;
    private Integer lit;
}
