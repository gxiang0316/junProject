package com.gordon.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String toIndex(){
        System.out.println(" ============= / ===========");
        return "login";
    }

    @RequestMapping("/login")
    public String login(String name,String password){
        System.out.println(" === login name:"+name+"   password:"+password);

        return "index";
    }

    @RequestMapping("/register")
    public String register(String name,String password){
        System.out.println(" === register name:"+name+"   password:"+password);
        return "login";
    }
}
