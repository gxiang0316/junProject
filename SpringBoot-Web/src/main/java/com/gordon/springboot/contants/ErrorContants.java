package com.gordon.springboot.contants;

/**
 * 错误常量
 */
public class ErrorContants {

    public static final String ERROR_401 = "401@请求参数不匹配";
    public static final String ERROR_403 = "403@没有权限，请联系管理员授权";
    public static final String ERROR_500 = "500@未知异常，请联系管理员";



    public static final String ERROR_9000 = "9000@该用户名已存在";
    // {}必须写上数字，否则报 NumberFormatException：for input string:''错误
    public static final String ERROR_9001 = "9001@用户名或密码错误，您还可尝试{0}次";
    public static final String ERROR_9002 = "9002@账号已被锁定，请等待30分钟后再试";
    public static final String ERROR_9003 = "9003@验证码错误，请重新输入!";
    public static final String ERROR_9004 = "9004@验证码已过期，请点击刷新!";
    public static final String ERROR_9005 = "9005@用户名或密码错误";
    public static final String ERROR_9006 = "9006@当前用户没有任何操作权限 ！";






}
