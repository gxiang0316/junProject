package com.gordon.springboot.service;

import com.gordon.springboot.entity.GwLog;
import com.gordon.springboot.entity.GwUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GwLogService {

    int insert(GwLog record);

    int insertSelective(GwLog record);

    List<GwLog> selectGwLogList();
}
