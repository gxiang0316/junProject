package com.gordon.springboot.mapper;

import com.gordon.springboot.entity.GwPermission;

public interface GwPermissionMapper {
    int insert(GwPermission record);

    int insertSelective(GwPermission record);
}