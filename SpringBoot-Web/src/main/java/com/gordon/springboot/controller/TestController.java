package com.gordon.springboot.controller;

import com.gordon.springboot.entity.Student;
import com.gordon.springboot.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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









}
