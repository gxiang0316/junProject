package com.gordon.springboot.mapper;

import com.gordon.springboot.entity.GwDict;

public interface GwDictMapper {
    int insert(GwDict record);

    int insertSelective(GwDict record);
}