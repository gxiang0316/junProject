package com.gordon.springboot.mapper;

import com.gordon.springboot.entity.GwLog;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GwLogMapper {
    int insert(GwLog record);

    int insertSelective(GwLog record);

    @Select("select * from gw_log")
    @Results({
        @Result(column = "create_date",property = "createDate"),
        @Result(column = "update_time",property = "updateTime"),
        @Result(column = "test_time",property = "testTime")
    })
    List<GwLog> selectGwLogList();
}