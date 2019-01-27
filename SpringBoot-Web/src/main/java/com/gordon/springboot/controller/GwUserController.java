package com.gordon.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GwUserController {

    @RequestMapping(value = {"user","user.html"})
    public String toUser(){
        return "user.html";
    }


}
