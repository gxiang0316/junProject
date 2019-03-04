package com.gordon.springboot.service;

import com.gordon.springboot.contants.ErrorContants;
import com.gordon.springboot.contants.GlobalContants;
import com.gordon.springboot.entity.GwMenu;
import com.gordon.springboot.entity.GwUser;
import com.gordon.springboot.exception.GwException;
import com.gordon.springboot.mapper.GwMenuMapper;
import com.gordon.springboot.mapper.GwSysParamMapper;
import com.gordon.springboot.mapper.GwUserMapper;
import com.gordon.springboot.shiro.ShiroUtils;
import com.gordon.springboot.utils.BRUtils;
import com.gordon.springboot.utils.PropertiesUtils;
import com.gordon.springboot.utils.ResourcesUtil;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private GwUserMapper userMapper;
    
    @Autowired
    private SysParamService sysParamServiceImpl;

    @Override
    public int insert(GwUser user) {
        RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        String salt = randomNumberGenerator.nextBytes().toHex();
        String password = new SimpleHash(ShiroUtils.hashAlgorithmName,
                                        user.getPassword(),
                                        ByteSource.Util.bytes(salt),
                                        ShiroUtils.hashIterations).toHex();
        user.setSalt(salt);
//        System.out.println(" salt : " + salt);
        user.setPassword(password);
//        System.out.println("password : " + password);
        GwUser guser = selectUserByName(user.getUsername());
        if(guser != null){
            throw new GwException(ErrorContants.ERROR_9000);
        }

        int id = -1;
        try {
            id = userMapper.insertSelective(user);
        } catch (Exception e) {
            throw new GwException(ErrorContants.ERROR_500);
        }

        return id;
    }

    @Override
    public GwUser selectByPrimaryKey(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public GwUser selectUserByName(String username) {
        return userMapper.selectUserByName(username);
    }

    @Override
    public List<GwUser> findUserListByMap(Map<String, Object> map) {
        return userMapper.findUserListByMap(map);
    }

    @Override
    public BRUtils loginFailUpdate(String username) {
        GwUser guser = selectUserByName(username);
        if(guser == null){
            throw new GwException(ErrorContants.ERROR_9005);
        }
        Integer loginFailNum = guser.getLoginFailNum();
        loginFailNum++;
        guser.setLoginFailNum(loginFailNum);

        int maxNum = Integer.valueOf(sysParamServiceImpl.getParamValue("sys.param.1000"));

        if(loginFailNum >= maxNum){
            guser.setStatus(GlobalContants.USER_STATUS_LOCKED);
            guser.setLockStime(new Date());
            update(guser);
            return BRUtils.error(ErrorContants.ERROR_9002);
        }else{
            update(guser);
            return BRUtils.error(ErrorContants.ERROR_9001, new Object[]{maxNum-loginFailNum});
        }
    }

    @Override
    public void update(GwUser guser) {
        userMapper.updateUser(guser);
    }


}
