package com.pressure.test.login.utils;

import com.pressure.test.login.pojo.User;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;

/**
 * @NAME: JsonUtils
 * @USER: zhouyu
 * @DATE: 2020/12/7
 */

@Log4j2
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {

        //对象所有字段全部列入
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);

        //取消默认转成timestamp形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

        //  忽略空bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        //统一日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        //忽略json中存在，java对象中不存在该属性的错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }


    public static <T> String obj2json(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse obj to String error", e);
            return null;
        }
    }

    public static <T> String obj2jsonPrety(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse obj to String error", e);
            return null;
        }
    }


    public static <T> T string2obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }

        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.warn("String to parse obj error", e);
            return null;
        }
    }

    public static <T> T string2obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }

        try {
            return (T)(typeReference.getType().equals(String.class)?str:objectMapper.readValue(str,typeReference));
        } catch (Exception e) {
            log.warn("String to parse obj error", e);
            return null;
        }
    }

    public static <T> T string2obj(String str,Class<?>collectionClass,Class<?>... elementsClasses) {
        JavaType javaType=objectMapper.getTypeFactory().constructParametricType(collectionClass,elementsClasses);
        try {
            return objectMapper.readValue(str,javaType);
        } catch (Exception e) {
            log.warn("String to parse obj error", e);
            return null;
        }
    }



    public static void main(String[] args) {
        User user=new User();
        System.out.println(JsonUtils.obj2jsonPrety(user));
    }
}