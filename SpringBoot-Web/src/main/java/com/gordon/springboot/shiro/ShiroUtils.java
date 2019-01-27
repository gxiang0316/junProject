package com.gordon.springboot.shiro;

import com.gordon.springboot.entity.GwUser;
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

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static GwUser getUserEntity() {
        return (GwUser)SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return getUserEntity().getUserId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

//    public static String getKaptcha(String key) {
//        Object kaptcha = getSessionAttribute(key);
//        if(kaptcha == null){
//            throw new RRException("验证码已失效");
//        }
//        getSession().removeAttribute(key);
//        return kaptcha.toString();
//    }







}
