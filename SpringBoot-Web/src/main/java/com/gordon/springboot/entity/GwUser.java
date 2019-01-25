package com.gordon.springboot.entity;

import java.util.Date;

public class GwUser {
    private Long userId;

    private String username;

    private String password;

    private String salt;

    private String email;

    private String mobile;

    private String status;

    private Integer loginFailNum;

    private Date lockStime;

    private Integer lockDuration;

    private Date createTime;

    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getLoginFailNum() {
        return loginFailNum;
    }

    public void setLoginFailNum(Integer loginFailNum) {
        this.loginFailNum = loginFailNum;
    }

    public Date getLockStime() {
        return lockStime;
    }

    public void setLockStime(Date lockStime) {
        this.lockStime = lockStime;
    }

    public Integer getLockDuration() {
        return lockDuration;
    }

    public void setLockDuration(Integer lockDuration) {
        this.lockDuration = lockDuration;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}