package com.gordon.springboot.service;

import com.gordon.springboot.entity.GwLog;
import com.gordon.springboot.entity.GwUser;
import com.gordon.springboot.mapper.GwLogMapper;
import com.gordon.springboot.mapper.GwUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GwLogServiceImpl implements GwLogService {

    @Autowired(required = false)
    private GwLogMapper gwLogMapper;

    @Override
    public int insert(GwLog record) {
        return gwLogMapper.insert(record);
    }

    @Override
    public int insertSelective(GwLog record) {
        return gwLogMapper.insertSelective(record);
    }

    @Override
    public List<GwLog> selectGwLogList() {
        return gwLogMapper.selectGwLogList();
    }


}
