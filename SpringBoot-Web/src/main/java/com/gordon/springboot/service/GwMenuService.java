package com.gordon.springboot.service;

import com.gordon.springboot.entity.GwMenu;

import java.util.List;

/**
 * Created by gordon on 2019/2/28.
 */
public interface GwMenuService {

    int insertSelective(GwMenu record);

    List<GwMenu> getUserRoleMenuList(String username);

}
