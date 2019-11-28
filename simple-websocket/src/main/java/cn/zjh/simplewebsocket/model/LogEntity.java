package cn.zjh.simplewebsocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.simplewebsocket.model
 * @date:2019/11/28
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogEntity {
    private String methodName;
    private String moduleName;
}
