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
     *  html中请求地址，如：/toHome 和 toHome 的区别 ：加了/的不会自动添加ctxPath，不加/会自动添加ctxPath
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

    // ================   菜单页面转发 begin ==========================

    // 用户管理
    @RequestMapping("/sysMng/userMng")
    public String userMng(HttpServletRequest request){
        return "html/sysMng/userMng.html";
    }
    // 角色管理
    @RequestMapping("/sysMng/roleMng")
    public String roleMng(HttpServletRequest request){
        return "html/sysMng/roleMng.html";
    }
    // 菜单管理
    @RequestMapping("/sysMng/menuMng")
    public String menuMng(HttpServletRequest request){
        return "html/sysMng/menuMng.html";
    }
    // 页面元素管理
    @RequestMapping("/sysMng/elementPromissionMng")
    public String elementPromissionMng(HttpServletRequest request){
        return "html/sysMng/elementPromissionMng.html";
    }
    // 数据字典管理
    @RequestMapping("/sysMng/dictMng")
    public String dictMng(HttpServletRequest request){
        return "html/sysMng/dictMng.html";
    }
    // 系统日志管理
    @RequestMapping("/sysMng/logMng")
    public String logMng(HttpServletRequest request){
        return "html/sysMng/logMng.html";
    }
    // java学习
    @RequestMapping("/pgSort/java")
    public String java(HttpServletRequest request){
        return "html/pgSort/java.html";
    }
    // javaScript学习
    @RequestMapping("/pgSort/javaScript")
    public String javaScript(HttpServletRequest request){
        return "html/pgSort/javaScript.html";
    }
    // linux学习
    @RequestMapping("/pgSort/linux")
    public String linux(HttpServletRequest request){
        return "html/pgSort/linux.html";
    }
    // datasource学习
    @RequestMapping("/pgSort/datasource")
    public String datasource(HttpServletRequest request){
        return "html/pgSort/datasource.html";
    }
    // 技术博客
    @RequestMapping("/essay/blog")
    public String blog(HttpServletRequest request){
        return "html/essay/blog.html";
    }
    // 生活随笔
    @RequestMapping("/essay/essayEdit")
    public String essayEdit(HttpServletRequest request){
        return "html/essay/essayEdit.html";
    }


    // ================   菜单页面转发 end ==========================

}
