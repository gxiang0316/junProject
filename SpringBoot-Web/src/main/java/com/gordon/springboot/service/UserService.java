package com.gordon.springboot.service;

import com.gordon.springboot.entity.GwUser;

import java.util.List;

public interface UserService {

    int insert(GwUser user);

    GwUser selectByPrimaryKey(Long userId);

    GwUser selectUserByName(String username);

    List<GwUser> selectUserList();
}
