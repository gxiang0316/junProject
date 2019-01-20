package com.gordon.springboot.shiro;

import com.gordon.springboot.contants.ErrorContants;
import com.gordon.springboot.contants.GlobalContants;
import com.gordon.springboot.entity.GwUser;
import com.gordon.springboot.exception.GwException;
import com.gordon.springboot.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm  extends AuthorizingRealm {


    @Autowired
    private UserService userServiceImpl;

    /**登录认证*/
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
            throw new LockedAccountException(ErrorContants.ERROR_9002);
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                getName() // 当前realm name
        );
        return authenticationInfo;
    }

    /**授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection) {
        return null;
    }
}
