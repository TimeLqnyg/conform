package cn.zjh.shardingjdbc.utils.annotation;

import cn.zjh.shardingjdbc.annotation.FruitName;

import java.lang.reflect.Field;

/**
 * 注解处理器
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.shardingjdbc.utils.annotation
 * @date:2019/11/8
 */
public class FruitInfoUtil {

    public static void getFruitInfo(Class<?> clazz){
        String strFruitName="水果名称";
        //成员变量 属性
        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName = field.getAnnotation(FruitName.class);
                strFruitName=strFruitName+fruitName.value();
                System.out.println(strFruitName);
            }
        }
    }
}
