package com.gordon.springboot.shiro;

import com.gordon.springboot.contants.ErrorContants;
import com.gordon.springboot.contants.GlobalContants;
import com.gordon.springboot.entity.GwUser;
import com.gordon.springboot.exception.GwException;
import com.gordon.springboot.service.GwPermissionService;
import com.gordon.springboot.service.SysParamService;
import com.gordon.springboot.service.UserService;
import com.gordon.springboot.service.impl.GwRoleServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public class UserRealm  extends AuthorizingRealm {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private SysParamService sysParamServiceImpl;

    @Autowired
    private GwPermissionService gwPermissionServiceImpl;

    @Autowired
    private GwRoleServiceImpl gwRoleServiceImpl;


    /**
     * 登录认证 (登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token){

        String username = (String) token.getPrincipal();

        GwUser user = userServiceImpl.selectUserByName(username);

        // Realm只能抛出shiro中的异常，不能抛出自定义异常(抛出自定义异常只会在控制台打印，无法在controller中捕获)
        if(user == null){
            throw new UnknownAccountException(ErrorContants.ERROR_9001);
        }
//        if(user == null){
//            throw new GwException(ErrorContants.ERROR_9001);
//        }

        if(user.getStatus().equals(GlobalContants.USER_STATUS_LOCKED)){
            long lockStime = user.getLockStime().getTime();
            long curTime = System.currentTimeMillis();
            int sysLockTime = Integer.valueOf(sysParamServiceImpl.getParamValue("sys.param.1001"));
            if((curTime - lockStime)/60/1000 >= sysLockTime){
                user.setLoginFailNum(0);
                user.setStatus(GlobalContants.USER_STATUS_ON);
                userServiceImpl.update(user);
            }else {
                throw new LockedAccountException(ErrorContants.ERROR_9002);
            }
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,// shiro缓存对象
                user.getPassword(),
                // 验证用的salt
                ByteSource.Util.bytes(user.getSalt()),
                getName() // 当前realm name
        );
        return authenticationInfo;
    }

    /**
     * 授权 (验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection) {
        // getPrimaryPrincipal()获取的是 登录验证方法中构造SimpleAuthenticationInfo时的第一个参数
        GwUser gwUser = (GwUser) principalCollection.getPrimaryPrincipal();
        Long userId = gwUser.getUserId();

        if(userId == GlobalContants.USER_ADMIN){//系统管理员拥有所有权限
            // 设计：做一个权限页面，增删改查  每次都插入或更新admin的数据
        }

        List<String> permList = gwPermissionServiceImpl.getPermissionByUserId(userId);
        System.out.println("  当前用户拥有的权限 ： " + permList.get(0));
        Set<String> permSet = new HashSet<>();
        permSet.addAll(permList);

        List<String> roleList = gwRoleServiceImpl.getRolesByUserId(userId);
        Set<String> rolesSet = new HashSet<>();
        rolesSet.addAll(roleList);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permSet);
        info.setRoles(rolesSet);
        return info;
    }

    /**
     * 设置认证加密方式
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        matcher.setHashIterations(ShiroUtils.hashIterations);
        matcher.setStoredCredentialsHexEncoded(true);
        super.setCredentialsMatcher(matcher);
    }
}
