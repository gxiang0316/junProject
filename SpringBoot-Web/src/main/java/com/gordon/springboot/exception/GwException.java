package com.gordon.springboot.exception;

import java.text.MessageFormat;

/**
 * 自定义异常
 */
public class GwException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    /**异常错误码*/
    private String code;
    /**异常信息*/
    private String msg;
    /**ErrorContants中code*/
    private String errorCode;

    public GwException(String errorCode){
        super(errorCode);
        this.errorCode = errorCode;
        this.code = errorCode.split("@")[0];
        this.msg = errorCode.split("@")[1];
    }

//    public GwException(String msg){
//        super(msg);
//        this.msg = msg;
//    }

    public GwException(String msg,Throwable e){
        super(msg,e);
        this.msg = msg;
    }

    public GwException(String code,String msg){
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public GwException(String code,String msg,Throwable e){
        super(msg,e);
    }

    public GwException(String code,Object[] objects){
        super(code);
        this.msg = MessageFormat.format(msg,objects);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
