package com.gordon.springboot.mapper;

import com.gordon.springboot.entity.GwUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GwUserMapper {

    @Select("delete from gw_user where userid = #{userId}")
    int deleteByPrimaryKey(Long userId);

    int insert(GwUser record);

    int insertSelective(GwUser record);
    @Select("select * from gw_user where userid = #{userId}")
    GwUser selectByPrimaryKey(Long userId);

    @Select("select * from gw_user where username = #{username}")
    GwUser selectUserByName(String username);

    @Select("select * from gw_user")
    List<GwUser> selectUserList();
}