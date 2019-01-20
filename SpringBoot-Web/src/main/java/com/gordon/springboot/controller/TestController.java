package com.gordon.springboot.controller;

import com.gordon.springboot.contants.ErrorContants;
import com.gordon.springboot.entity.Student;
import com.gordon.springboot.exception.GwException;
import com.gordon.springboot.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired(required=false)
    private StudentMapper studentMapper;

    @RequestMapping("/mybatis")
    public Object findStudent(String studentId,String name,String sex){

        Student student = studentMapper.selectByPrimaryKey(studentId);
        System.out.println(" student name : " + student.getStudentName());
        return student;
    }

    @RequestMapping("/springmvc")
    public Object findStudent(){
        try{

            int i = 1/0;
        }catch(Exception e){
            throw new GwException(ErrorContants.ERROR_403);
        }
        return "街坊邻居到家乐福吉林省地方";
    }









}
