package com.gordon.springboot.mapper;

import com.gordon.springboot.entity.GwPermission;

import java.util.List;

public interface GwPermissionMapper {
    int insert(GwPermission record);

    int insertSelective(GwPermission record);

    List<String> getPermissionByUserId(Long userId);
}