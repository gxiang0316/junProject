package com.gordon.springboot.utils;

import com.gordon.springboot.contants.ErrorContants;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 */
public class BRUtils extends HashMap<String,Object> {

    private static final long serialVersionUID = 1L;
    
    public static final String KEY_CODE = "code";
    public static final String KEY_MSG = "msg";
    public static final String KEY_DATA = "data";
    public static final String KEY_SUCCESS_CODE = "0000";
    public static final String KEY_SUCCESS_MSG = "success";

    /**默认成功标志*/
    public BRUtils(){
        put(KEY_CODE,KEY_SUCCESS_CODE);
        put(KEY_MSG,KEY_SUCCESS_MSG);
    }

    public static BRUtils error(){
        return error(ErrorContants.ERROR_500);
    }

    public static BRUtils error(String code){
        BRUtils brUtils = new BRUtils();
//        String c = code.split("@")[0];
//        String m = code.split("@")[1];
        brUtils.put(KEY_CODE,code.split("@")[0]);
        brUtils.put(KEY_MSG,code.split("@")[1]);
        return brUtils;
    }

    public static BRUtils error(int code, String msg){
        BRUtils brUtils = new BRUtils();
        brUtils.put(KEY_CODE,code);
        brUtils.put(KEY_MSG,msg);
        return brUtils;
    }
//    public static BRUtils error(String msg){
//        return error(500,msg);
//    }
//
//    public static BRUtils error(int code, String msg){
//        BRUtils brUtils = new BRUtils();
//        brUtils.put(KEY_CODE,code);
//        brUtils.put(KEY_MSG,msg);
//        return brUtils;
//    }

    public static BRUtils ok(){
        return new BRUtils();
    }

    public static BRUtils ok(String msg){
        BRUtils brUtils = new BRUtils();
        brUtils.put(KEY_MSG,msg);
        return brUtils;
    }

    public static BRUtils ok(Object data){
        BRUtils brUtils = new BRUtils();
        brUtils.put(KEY_DATA,data);
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
