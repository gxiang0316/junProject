package com.gordon.springboot.service;

import com.gordon.springboot.entity.GwUser;
import com.gordon.springboot.utils.BRUtils;

import java.util.List;
import java.util.Map;

public interface UserService {

    int insert(GwUser user);

    GwUser selectByPrimaryKey(Long userId);

    GwUser selectUserByName(String username);

    List<GwUser> findUserListByMap(Map<String,Object> map);


}
