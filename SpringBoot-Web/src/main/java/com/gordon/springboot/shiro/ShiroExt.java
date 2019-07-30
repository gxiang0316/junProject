package com.gordon.springboot.shiro;

import com.gordon.springboot.entity.GwUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.beetl.core.GroupTemplate;

import java.util.Arrays;

/**
 * shiro权限标签
 * Created by gordon on 2019/7/16.
 */
public class ShiroExt {

    private static final String NAMES_DELIMETER = ",";

    protected static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 是否具有某个权限
     *
     * @param permission
     * @return true:有   false:无
     */
    public boolean hasPermission(String permission) {
        return getSubject() != null && permission != null &&
                permission.length() > 0 && getSubject().isPermitted(permission);
    }

    /**
     * 是否具有任意一个权限
     *
     * @param permissions
     * @return true:有   false:无
     */
    public boolean hasAnyPermission(String... permissions) {
        boolean flag = false;
        if (permissions == null || permissions.length == 0) {
            return flag;
        }

        for (String perm : permissions) {
            if (getSubject() != null && getSubject().isPermitted(perm)) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    /**
     * 是否具有所有权限
     *
     * @param permissions
     * @return true:有   false:无
     */
    public boolean hasAllPermission(String... permissions) {
        boolean flag = false;
        if (permissions == null || permissions.length == 0) {
            return flag;
        }

        for (String perm : permissions) {
            if (getSubject() != null && getSubject().isPermitted(perm)) {
                flag = true;
                continue;
            } else {
                flag = false;
                break;
            }
        }

        return flag;
    }

    /**
     * 是否已登录
     *
     * @return
     */
    public boolean isLogged() {
        return getSubject() != null && getSubject().isAuthenticated();
    }

    /***
     * 获取当前登录用户
     * @return  null:游客 或 未登录
     */
    public GwUser getUser() {
        return (getSubject() != null && getSubject().isAuthenticated()) ?
                (GwUser) getSubject().getPrincipals().getPrimaryPrincipal() : null;
    }

    /***
     * 是否具有某个角色
     * @param roleName
     * @return true:有   false：无
     */
    public boolean hasRole(String roleName) {
        return roleName != null && roleName.length() > 0
                && getSubject() != null && getSubject().hasRole(roleName);
    }

    /**
     * 是否具有所有一个角色
     *
     * @param roleNames
     * @return true:有   false：无
     */
    public boolean hasAnyRoles(String... roleNames) {
        boolean flag = false;
        if(roleNames == null || roleNames.length == 0){
            return flag;
        }

        for (String roleName : roleNames) {
            if(getSubject() != null && getSubject().hasRole(roleName)){
                flag = true;
                break;
            }
        }

        return flag;
    }


    /**
     * 是否具有所有一个角色
     *
     * @param roleNames
     * @return true:有   false：无
     */
    public boolean hasAllRoles(String... roleNames) {
        return roleNames != null && roleNames.length > 0
                && getSubject() != null && getSubject().hasAllRoles(Arrays.asList(roleNames));
    }

    /**
     * 添加进 Beetl 模板
     * @param args
     */
    public static void main(String[] args) {
        GroupTemplate gt = new GroupTemplate();
        gt.registerFunctionPackage("shiro", new ShiroExt());
    }


}
