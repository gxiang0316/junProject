package com.gordon.springboot.shiro;

import com.gordon.springboot.entity.GwUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by gordon on 2019/1/8.
 */
public class ShiroUtils {

    public static String hashAlgorithmName = "SHA-256";
    public static int hashIterations = 5;
    public static String MENULIST_KEY = "menuList";
    public static String VERIFY_KEY = "verifyCode";

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static GwUser getUserEntity() {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if(principal == null || principal == ""){
            return new GwUser();
        }
        return (GwUser)principal;
    }

    public static Long getUserId() {
        return getUserEntity().getUserId();
    }

    public static String getUserName() {
        return getUserEntity().getUsername();
    }

    public static void setAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static Object removeAttribute(Object key) {
        return getSession().removeAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static String validateCode(String verCode) {
        if(StringUtils.isNotBlank(verCode)){
            String verfyCode = (String) getAttribute(VERIFY_KEY);
            if(StringUtils.isBlank(verfyCode)){
                return "timeout";
            }else if(verCode.toLowerCase().equals(verfyCode)){
                return "ok";
            }
        }
        return "error";
    }

}
