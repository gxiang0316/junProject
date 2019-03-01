package com.gordon.springboot.service;

import com.gordon.springboot.entity.GwMenu;
import com.gordon.springboot.mapper.GwMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gordon on 2019/2/28.
 */
@Service
public class GwMenuServiceImpl implements GwMenuService {

    @Autowired
    private GwMenuMapper menuMapper;

    @Override
    public int insertSelective(GwMenu record) {
        return 0;
    }

    @Override
    public List<GwMenu> getUserRoleMenuList(String username) {
        return menuMapper.getUserRoleMenuList(username);
    }
}
