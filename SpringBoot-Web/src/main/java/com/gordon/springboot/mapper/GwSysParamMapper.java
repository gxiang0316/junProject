package com.gordon.springboot.mapper;

import com.gordon.springboot.entity.GwSysParam;

public interface GwSysParamMapper {
    int insert(GwSysParam record);

    int insertSelective(GwSysParam record);

    GwSysParam selectById(Integer paramId);
}