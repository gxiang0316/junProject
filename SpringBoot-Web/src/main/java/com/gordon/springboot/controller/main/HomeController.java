package com.gordon.springboot.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gordon on 2019/2/26.
 */
@Controller
public class HomeController {

    /**
     *  /toHome 和 toHome 的区别 ：加了/的不会自动添加ctxPath，不加/会自动添加ctxPath
     */
    @RequestMapping("/toHome")
    public String toHome(){
        System.out.println(" -======HomeController===== toHome");
        return "main/home.html";
    }

    @RequestMapping("/toMain")
    public String toMain(){
        System.out.println(" -======HomeController===== toMain");
        return "main/main.html";
    }




}
