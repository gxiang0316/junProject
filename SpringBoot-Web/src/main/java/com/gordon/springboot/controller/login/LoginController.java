package com.gordon.springboot.controller.login;

import com.gordon.springboot.contants.ErrorContants;
import com.gordon.springboot.exception.GwException;
import com.gordon.springboot.shiro.ShiroUtils;
import com.gordon.springboot.utils.BRUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String toLogin(){
        System.out.println(" ============= / ===========");
        return "login";
    }

    @RequestMapping("/register")
    public String register(String name,String password){
        System.out.println(" === register name:"+name+"   password:"+password);
        return "login";
    }

    @RequestMapping("/logout")
    public void logout(){
        ShiroUtils.logout();
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BRUtils login(String username,String password,String remeberMe) throws InterruptedException {
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        try {
            ShiroUtils.getSubject().login(token);
        } catch (LockedAccountException e){
            return BRUtils.error(ErrorContants.ERROR_9002);
        } catch (AuthenticationException e) {
            return BRUtils.error(ErrorContants.ERROR_9001);
        }
        return BRUtils.ok();
    }

    @RequestMapping("/index")
    public String toIndex(){
        System.out.println("============= index ============");
        return "index";
    }

//    @RequestMapping("/login")
//    public ModelAndView login(String username, String password){
////    public BRUtils login(String name,String password){
//        ModelAndView modelAndView = new ModelAndView();
//        System.out.println(" === login name:"+username+"   password:"+password);
//        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
//
//        try {
//            ShiroUtils.getSubject().login(token);
//        } catch (LockedAccountException e){
//            modelAndView.addObject("login_flag",ErrorContants.ERROR_9002.split("@")[1]);
//            modelAndView.setViewName("login");
//            return modelAndView;
//        } catch (AuthenticationException e) {
//            modelAndView.addObject("login_flag","用户名或密码错误，还可尝试5次");
//            modelAndView.setViewName("login");
//            return modelAndView;
//            //return BRUtils.error(ErrorContants.ERROR_9001);
//        }
//        modelAndView.addObject("login_flag","登录成功");
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }
}
