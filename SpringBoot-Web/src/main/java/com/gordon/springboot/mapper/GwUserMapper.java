package com.gordon.springboot.mapper;

import com.gordon.springboot.entity.GwUser;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * mybatis注解sql  http://www.cnblogs.com/guoyafenghome/p/9123442.html
 */
public interface GwUserMapper {
    int insert(GwUser record);

    int insertSelective(GwUser record);

    @Select("select * from gw_user where user_id = #{userId}")
    GwUser selectByPrimaryKey(Long userId);
    @Select("select * from gw_user where username = #{username}")
    GwUser selectUserByName(String username);
    @Select("select * from gw_user")
    List<GwUser> selectUserList();
    @Update("update ")
    int updateByPrimaryKey(GwUser user);
}