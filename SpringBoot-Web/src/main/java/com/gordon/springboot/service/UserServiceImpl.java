package com.gordon.springboot.service;

import com.gordon.springboot.contants.ErrorContants;
import com.gordon.springboot.entity.GwUser;
import com.gordon.springboot.exception.GwException;
import com.gordon.springboot.mapper.GwUserMapper;
import com.gordon.springboot.shiro.ShiroUtils;
import com.gordon.springboot.utils.BRUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private GwUserMapper userMapper;

    @Override
    public int insert(GwUser user) {
        RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        String salt = randomNumberGenerator.nextBytes().toHex();
        String password = new SimpleHash(ShiroUtils.hashAlgorithmName,
                                        user.getPassword(),
                                        ByteSource.Util.bytes(salt),
                                        ShiroUtils.hashIterations).toHex();
        user.setSalt(salt);
        System.out.println(" salt : " + salt);
        user.setPassword(password);

        GwUser guser = selectUserByName(user.getUsername());
        if(guser != null){
            throw new GwException(ErrorContants.ERROR_9000);
        }

        int id = -1;
        try {
            id = userMapper.insert(user);
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

}
