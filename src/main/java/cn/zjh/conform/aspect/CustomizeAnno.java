package cn.zjh.conform.aspect;

import java.lang.annotation.*;

/**
 * 自定义注解 然后在定义一个切面 加了注解的地方就能使用通知
 */

@Documented
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomizeAnno {
}
