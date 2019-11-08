package cn.zjh.shardingjdbc.utils;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.lang.reflect.Type;

/**
 * @author: create by zjh
 * @version: v1.0
 * @description: cn.zjh.shardingjdbc.utils
 * @date:2019/11/8
 */
public class DateDeserialization extends AbstractDateDeserializer implements ObjectDeserializer {

    @Override
    protected <T> T cast(DefaultJSONParser parser, Type clazz, Object fieldName, Object value) {
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
