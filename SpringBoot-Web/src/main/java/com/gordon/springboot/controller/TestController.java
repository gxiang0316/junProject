package com.gordon.springboot.controller;

import com.gordon.springboot.contants.ErrorContants;
import com.gordon.springboot.entity.Student;
import com.gordon.springboot.exception.GwException;
import com.gordon.springboot.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired(required=false)
    private StudentMapper studentMapper;

    @ResponseBody
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

    @RequestMapping("/toMain")
    public String toMain(){
        System.out.println("============ toMain ==========");
        return "main/index.html";
    }

    @RequestMapping(value = {"/home","main/test1.html"})
    public String homePage(){
        System.out.println("============ 进入首页 ==========");
        return "main/test1.html";
    }

    @RequestMapping(value = "index-1.html")
    public String index1(){
        System.out.println("============ 进入第一个标签页 ==========");
        return "main/index-1.html";
    }









}
