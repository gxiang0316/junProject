package com.gordon.springboot.controller.login;

import com.gordon.springboot.contants.ErrorContants;
import com.gordon.springboot.entity.GwMenu;
import com.gordon.springboot.entity.GwUser;
import com.gordon.springboot.exception.GwException;
import com.gordon.springboot.service.GwMenuService;
import com.gordon.springboot.service.UserService;
import com.gordon.springboot.shiro.ShiroUtils;
import com.gordon.springboot.utils.BRUtils;
import com.gordon.springboot.utils.JsonUtils;
import com.gordon.springboot.utils.PropertiesUtils;
import com.gordon.springboot.utils.VerifyCodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private GwMenuService menuServiceImpl;

    @RequestMapping(value = {"/","/toLogin"})
    public String toLogin(){
        System.out.println(" ============= / ===========");
        return "login.html";
    }

    @RequestMapping(value="/verifyCode")
    public void getVerifyCode(HttpServletRequest request , HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
//        System.out.println(" ===== 生成验证码 ======== ");
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);

        //删除以前的
        ShiroUtils.removeAttribute(ShiroUtils.VERIFY_KEY);
        //存入会话session
        ShiroUtils.setAttribute(ShiroUtils.VERIFY_KEY, verifyCode.toLowerCase());// 忽略大小写
        //设置过期时间 5 分钟
//        System.out.println(" 验证码过期时长 ： " + PropertiesUtils.getLong("shiro.verifyCode.timeout"));
        ShiroUtils.getSession().setTimeout(PropertiesUtils.getLong("shiro.verifyCode.timeout"));
        //生成图片
        int w = 100, h = 40;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
//        System.out.println(" ==== 验证码： " + ShiroUtils.getAttribute(ShiroUtils.VERIFY_KEY));
    }

    @RequestMapping(value = "/toRegister")
    public String toRegister(String name,String password){
        System.out.println(" === register name:"+name+"   password:"+password);
        return "register.html";
    }

    @RequestMapping("/logout")
    public void logout(){
        ShiroUtils.logout();
    }

    @ResponseBody
    @RequestMapping(value = "/register",method=RequestMethod.POST)
    public Map<String,Object> register(String rusername,String rpassword, String verCode){
//        System.out.println("=========== register =========="+rpassword);
        String valiResult = ShiroUtils.validateCode(verCode);
        if(valiResult.equals("timeout")){
            return BRUtils.error(ErrorContants.ERROR_9004);
        }else if(valiResult.equals("error")){
            return BRUtils.error(ErrorContants.ERROR_9003);
        }
        GwUser gwUser = new GwUser();
        gwUser.setUsername(rusername);
        gwUser.setPassword(rpassword);
        try {
            Thread.sleep(3000);
            int insert = userServiceImpl.insert(gwUser);
//            System.out.println(" insert : " + insert);
            // 注册成功 直接后台登录
            return login(rusername, rpassword, verCode, true);
        } catch (Exception e) {
//            e.printStackTrace();
            return BRUtils.error(ErrorContants.ERROR_500);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> login(String username, String password,String verCode,boolean remeberMe) {
        String valiResult = ShiroUtils.validateCode(verCode);
        if(valiResult.equals("timeout")){
            return BRUtils.error(ErrorContants.ERROR_9004);
        }else if(valiResult.equals("error")){
            return BRUtils.error(ErrorContants.ERROR_9003);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username,password,remeberMe);
        try {
            ShiroUtils.getSubject().login(token);
        } catch (LockedAccountException e){
            return BRUtils.error(ErrorContants.ERROR_9002);
        } catch (AuthenticationException e) {

            return userServiceImpl.loginFailUpdate(username);
            // 记录登录失败次数，并返回这个次数
//            int failNum = userServiceImpl.loginFailUpdate(username);
//            if(failNum >= 5){
//                return BRUtils.error(ErrorContants.ERROR_9002);
//            }else {
//                return BRUtils.error(ErrorContants.ERROR_9001, new Object[]{5-failNum});
//            }
        }
        // 获取用户当前角色对应的菜单
        List<GwMenu> menuList = menuServiceImpl.getUserRoleMenuList(username);
        if(menuList == null || menuList.size() == 0){
            return BRUtils.error(ErrorContants.ERROR_9006);
        }
        ShiroUtils.setAttribute(ShiroUtils.MENULIST_KEY, JsonUtils.listToJson(menuList));
        // 因为在生成验证码的时候设置的时间仅用于验证码校验，登录成功恢复默认时长
        ShiroUtils.getSession().setTimeout(PropertiesUtils.getLong("shiro.sessionTimeout"));
        return BRUtils.ok();
    }

    @RequestMapping(value = {"index","index.html"})
    public String toIndex() throws InterruptedException {
        System.out.println("============= index ============");
        return "index.html";
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
