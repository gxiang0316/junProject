package com.gordon.springboot.mapper;

import com.gordon.springboot.entity.GwMenu;
import com.gordon.springboot.entity.GwUser;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface GwUserMapper {

    @Select("delete from gw_user where userid = #{userId}")
    int deleteByPrimaryKey(Long userId);

    int insert(GwUser record);

    int insertUser(GwUser record);

    int insertSelective(GwUser record);
    @Select("select * from gw_user where userid = #{userId}")
    GwUser selectByPrimaryKey(Long userId);

    @Select("select * from gw_user where username = #{username}")
    @ResultMap("com.gordon.springboot.mapper.GwUserMapper.BaseResultMap")
    GwUser selectUserByName(String username);

    List<GwUser> findUserListByMap(Map<String,Object> map);

    void updateUser(GwUser user);



    /**
     * @Select("xxxx")
     *  使用Results构建字段和实体属性的映射关系
     * @Results({
         * @Result(id=true, column="stud_id", property="studId"),
         * @Result(column="name", property="name"),
         * @Result(column="email", property="email"),
         * @Result(property="address", column="addr_id"))
     *
     * 使用ResultMap构建字段和实体属性的映射关系。
     *      com.gordon.springboot.mapper.GwUserMapper：GwUserMapper中namespace值
     *      BaseResultMap：<resultMap>标签定义的id
     * @ResultMap("com.gordon.springboot.mapper.GwUserMapper.BaseResultMap")
     *
     *
     */
}