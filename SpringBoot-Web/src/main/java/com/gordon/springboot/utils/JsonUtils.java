package com.gordon.springboot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * json 转换器
 */
public class JsonUtils {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };

    /**
     * 使用自定义规则转换
     * @param object
     * @return
     */
    public static String objectToJSONForFeatures(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * 使用无规则转换
     * @param object
     * @return
     */
    public static String objectToJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }

    /**
     * 将string转化为序列化的json字符串
     * @param text
     * @return
     */
    public static Object textToJson(String text) {
        Object objectJson  = JSON.parse(text);
        return objectJson;
    }

    /**
     *  转换为数组
     * @param text
     * @return
     */
    public static <T> Object[] toArray(String text) {
        return toArray(text, null);
    }

    /**
     *  转换为对象数组
     * @param text
     * @param clazz
     * @return
     */
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    /**
     * 转换为List
     * @param text
     * @param clazz
     * @return
     */
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     * List转换为json
     * @param list
     * @return
     */
    public static <T> String listToJson(List<T> list) {
        String s = JSONObject.toJSONString(list);
        return s;
    }

    /**
     * 转换JSON字符串为对象
     * @param jsonData
     * @param clazz
     * @return
     */
    public static Object jsonToObject(String jsonData, Class<?> clazz) {
        return JSONObject.parseObject(jsonData, clazz);
    }

    /**
     * 转换对象为JSON字符串
     * @param object
     * @return
     */
    public static String objectToJson(Object object) {
        String s = JSONObject.toJSONString(object);
        return s;
    }

    /**
     * json字符串转化为map
     * @param json
     * @return
     */
    public static <K, V> Map<K, V> jsonToMap(String json) {
        Map<K, V> m = (Map<K, V>) JSONObject.parseObject(json);
        return m;
    }

    /**
     * 将map转化为json串
     * @param m
     * @return
     */
    public static <K, V> String mapToJson(Map<K, V> m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }

    /**
     * 把JSON数据转换成较为复杂的List<Map<String, Object>>
     * @param jsonData JSON数据
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> jsonToListMap(String jsonData) {
        return JSON.parseObject(jsonData,
                new TypeReference<List<Map<String, Object>>>() { });
    }

}
