package com.gordon.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class GwLog {
    private Long id;

    private String userid;

    private String operation;

    private String clazz;

    private String method;

    private String params;

    private Long time;

    private String ip;
//    @JsonFormat(pattern = "yyyy-MM-DD hh:mm:ss",timezone="GMT+8")
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")// 配置FastJsonHttpMessageConverter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // GMT+8在properties配置
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    //    @DateTimeFormat(pattern = "yyyy-MM-DD")
//    @JSONField(format = "yyyy-MM-DD")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date testTime;

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    private String exMsg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz == null ? null : clazz.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getExMsg() {
        return exMsg;
    }

    public void setExMsg(String exMsg) {
        this.exMsg = exMsg == null ? null : exMsg.trim();
    }
}