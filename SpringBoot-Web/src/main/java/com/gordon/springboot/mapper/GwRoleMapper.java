package com.gordon.springboot.mapper;

import com.gordon.springboot.entity.GwRole;

import java.util.List;

public interface GwRoleMapper {
    int insert(GwRole record);

    int insertSelective(GwRole record);

    List<String> getRolesByUserId(Long userId);
}