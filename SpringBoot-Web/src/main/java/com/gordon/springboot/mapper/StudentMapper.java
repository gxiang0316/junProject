package com.gordon.springboot.mapper;

import com.gordon.springboot.entity.Student;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(String studentId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String studentId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}