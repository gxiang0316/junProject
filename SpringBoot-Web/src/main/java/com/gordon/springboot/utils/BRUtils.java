package com.gordon.springboot.utils;

import com.github.pagehelper.Page;
import com.gordon.springboot.contants.ErrorContants;

import java.text.MessageFormat;
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
    /**当前页码*/
    public static final String KEY_CURR_PAGE = "currPage";
    /**当前页记录数*/
    public static final String KEY_CURR_PAGE_NUM = "currPageNum";
    /**总页数*/
    public static final String KEY_TOTAL_PAGE = "totalPage";
    /**总记录数*/
    public static final String KEY_TOTAL_NUM = "totalNum";

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
        brUtils.put(KEY_CODE,code.split("@")[0]);
        brUtils.put(KEY_MSG,code.split("@")[1]);
        return brUtils;
    }

    public static BRUtils error(String code,Object[] objs){
        BRUtils brUtils = new BRUtils();
        brUtils.put(KEY_CODE,code.split("@")[0]);
        if(objs != null && objs.length > 0){
            brUtils.put(KEY_MSG, MessageFormat.format(code.split("@")[1],objs));
        }else {
            brUtils.put(KEY_MSG, code.split("@")[1]);
        }
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

    public static BRUtils pageData(int totalPage,long totalNum,Object data){
        BRUtils brUtils = new BRUtils();
        brUtils.put(KEY_TOTAL_PAGE,totalPage);
        brUtils.put(KEY_TOTAL_NUM,totalNum);
        brUtils.put(KEY_DATA,data);
        return brUtils;
    }

    public static BRUtils pageData(Page<?> page){
        BRUtils brUtils = new BRUtils();
        brUtils.put(KEY_CURR_PAGE,""+page.getPageNum());
        brUtils.put(KEY_CURR_PAGE_NUM,""+page.getPageSize());
        brUtils.put(KEY_TOTAL_PAGE,""+page.getPages());
        brUtils.put(KEY_TOTAL_NUM,""+page.getTotal());
        brUtils.put(KEY_DATA,page.getResult());
//        brUtils.put("total",""+page.getTotal());
//        brUtils.put("rows",page.getResult());
        return brUtils;
    }


    @Override
    public BRUtils put(String key, Object value) {
        super.put(key,value);
        return this;
    }
}
