package com.gordon.springboot.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;

/**
 * 读取properties文件工具类 此类不支持 配置文件中的算术表达式(如 5 * 60 * 1000)
 *   加入CalulatorUtils后可支持
 */
@Component
public class PropertiesUtils {

    private static Environment env;
    /**
     * 算术符号
     */
    private static final String[] reg = {"\\+", "-", "\\*", "/", "%"};

    @Autowired
    public PropertiesUtils(Environment env) {
        this.env = env;
    }

    public static String getString(String key) {
        try {
            return env.getProperty(key);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    /**
     * 获取key对应的值
     *
     * @param key  msg = {0}今年{1}岁了
     * @param objs 占位符对应的值  new Object{"tom",18}
     * @return tom今年18岁了
     */
    public static String getString(String key, Object[] objs) {
        try {
            if (objs != null && objs.length > 0) {
                return MessageFormat.format(env.getProperty(key), objs);
            }
            return env.getProperty(key);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    public static String getString(String key, String defaultValue) {
        try {
            String value = env.getProperty(key);
            if (StringUtils.isBlank(value)) {
                return defaultValue;
            }
            return value;
        } catch (MissingResourceException e) {
            return defaultValue;
        }
    }

    public static int getInt(String key) {
        String str = getString(key);
        double result = CalculatorUtils.conversion(str);
        return (int) result;
    }

    public static int getInt(String key, int defaultValue) {
        String value = env.getProperty(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        double result = CalculatorUtils.conversion(value);
        return (int) result;
    }

    public static long getLong(String key) {
        String str = getString(key);
        double result = CalculatorUtils.conversion(str);
        return (long) result;
    }

    public static long getLong(String key, int defaultValue) {
        String value = env.getProperty(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        double result = CalculatorUtils.conversion(value);
        return (long) result;
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(env.getProperty(key));
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = env.getProperty(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return new Boolean(value);
    }


/**

 private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
 private static Properties props;

 synchronized static private void loadProps(String filePath){
 logger.info("开始加载properties文件内容.......");
 props = new Properties();
 InputStream in = null;
 try {
 // 要加载的属性文件
 in = PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath);
 props.load(in);
 } catch (FileNotFoundException e) {
 logger.error(filePath+"文件未找到 ========= ");
 } catch (IOException e) {
 logger.error(" ===== 读取properties文件 出现IOException ====== ");
 } finally {
 try {
 if(null != in) { in.close();
 }
 } catch (IOException e) {
 logger.error(filePath + "文件流关闭出现异常 ======== ");
 }
 }
 logger.info("加载properties文件内容完成...........");
 logger.info("properties文件内容：" + props);
 }

 public static String getProperty(String filePath ,String key){
 if(null == props) {
 loadProps(filePath);
 }
 return props.getProperty(key);
 }

 public static String getProperty(String filePath ,String key, String defaultValue) {
 if(null == props) {
 loadProps(filePath);
 }
 return props.getProperty(key, defaultValue);
 }

 */
}
