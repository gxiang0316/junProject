package com.gordon.springboot.mapper;

import com.gordon.springboot.entity.GwRole;

public interface GwRoleMapper {
    int insert(GwRole record);

    int insertSelective(GwRole record);
}