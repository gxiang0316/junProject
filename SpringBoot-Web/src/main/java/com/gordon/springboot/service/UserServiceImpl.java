package com.gordon.springboot.service;

import com.gordon.springboot.entity.GwUser;
import com.gordon.springboot.mapper.GwUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private GwUserMapper userMapper;

    @Override
    public int insert(GwUser user) {
        return userMapper.insert(user);
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
    public List<GwUser> selectUserList() {
        return userMapper.selectUserList();
    }
}
