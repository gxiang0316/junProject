package com.gordon.springboot.controller.main;

import com.gordon.springboot.mapper.GwMenuMapper;
import com.gordon.springboot.service.GwMenuService;
import com.gordon.springboot.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
    public String toMain(HttpServletRequest request){
        System.out.println(" -======HomeController===== toMain");
        // 返回当前登录用户名
        request.setAttribute("username",ShiroUtils.getUserEntity().getUsername());
        request.setAttribute("menuList",ShiroUtils.getAttribute(ShiroUtils.MENULIST_KEY));
        // beetl获取：${username}
        return "main/main.html";
    }




}
