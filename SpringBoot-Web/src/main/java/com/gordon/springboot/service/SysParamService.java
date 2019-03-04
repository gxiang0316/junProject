package com.gordon.springboot.service;

import com.gordon.springboot.entity.GwSysParam;

/**
 * Created by gordon on 2019/2/27.
 */
public interface SysParamService {

    GwSysParam selectById(Integer paramId);

    String getParamValue(Integer paramId);

    String getParamValue(String propertiesKey);
}
