package com.gordon.springboot.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 */
public class BRUtils extends HashMap<String,Object> {

    private static final long serialVersionUID = 1L;
    
    public static final String KEY_CODE = "code";
    public static final String KEY_MSG = "msg";

    /**默认成功标志*/
    public BRUtils(){
        put(KEY_CODE,0);
        put(KEY_MSG,"success");
    }

    public static BRUtils error(){
        return error(500,"未知异常，请联系管理员");
    }

    public static BRUtils error(String msg){
        return error(500,msg);
    }

    public static BRUtils error(int code, String msg){
        BRUtils brUtils = new BRUtils();
        brUtils.put(KEY_CODE,code);
        brUtils.put(KEY_MSG,msg);
        return brUtils;
    }

    public static BRUtils ok(){
        return new BRUtils();
    }

    public static BRUtils ok(String msg){
        BRUtils brUtils = new BRUtils();
        brUtils.put(KEY_MSG,msg);
        return brUtils;
    }

    public static BRUtils ok(Map<String,Object> map){
        BRUtils brUtils = new BRUtils();
        brUtils.putAll(map);
        return brUtils;
    }

    @Override
    public Object put(String key, Object value) {
        super.put(key,value);
        return this;
    }
}
