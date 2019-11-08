package cn.zjh.shardingjdbc.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解 水果名字
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.shardingjdbc.annotation
 * @date:2019/11/8
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FruitName {
    String value() default "";
}
