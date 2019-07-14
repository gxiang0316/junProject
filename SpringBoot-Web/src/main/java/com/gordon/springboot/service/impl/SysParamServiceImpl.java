package com.gordon.springboot.service.impl;

import com.gordon.springboot.entity.GwSysParam;
import com.gordon.springboot.mapper.GwSysParamMapper;
import com.gordon.springboot.service.SysParamService;
import com.gordon.springboot.utils.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gordon on 2019/2/27.
 */
@Service
public class SysParamServiceImpl implements SysParamService {

    @Autowired(required = false)
    private GwSysParamMapper sysParamMapper;

    @Override
    public GwSysParam selectById(Integer paramId) {
        return sysParamMapper.selectById(paramId);
    }

    @Override
    public String getParamValue(Integer paramId) {
        return sysParamMapper.selectById(paramId).getParamValue();
    }

    public String getParamValue(String propertiesKey) {
        int paramId = PropertiesUtils.getInt(propertiesKey);
        return sysParamMapper.selectById(paramId).getParamValue();
    }
}
