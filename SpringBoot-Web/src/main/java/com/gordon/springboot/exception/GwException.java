package com.gordon.springboot.exception;

/**
 * 自定义异常
 */
public class GwException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    public GwException(String msg){
        super(msg);
        this.msg = msg;
    }

    public GwException(String msg,Throwable e){
        super(msg,e);
        this.msg = msg;
    }

    public GwException(int code,String msg){
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public GwException(int code,String msg,Throwable e){
        super(msg,e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
