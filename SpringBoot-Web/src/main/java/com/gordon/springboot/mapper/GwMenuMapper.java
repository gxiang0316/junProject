package com.gordon.springboot.mapper;

import com.gordon.springboot.entity.GwMenu;

import java.util.List;

public interface GwMenuMapper {
    int insert(GwMenu record);

    int insertSelective(GwMenu record);

    List<GwMenu> getUserRoleMenuList(String username);
}