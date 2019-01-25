package com.gordon.springboot.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gordon.springboot.entity.GwLog;
import com.gordon.springboot.service.GwLogService;
import com.gordon.springboot.utils.BRUtils;
import com.gordon.springboot.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gordon on 2019/1/22.
 */
@Controller
public class GwLogController {

    @Autowired
    private GwLogService gwLogServiceImpl;

    @RequestMapping("/logList")
    public void findGwLogList(@RequestParam(required = false,defaultValue = "1") int pageNum,
                              @RequestParam(required = false,defaultValue = "5") int pageSize,
                              HttpServletRequest request){
        Page<GwLog> page = PageHelper.startPage(pageNum,pageSize);
        List<GwLog> gwLogs = gwLogServiceImpl.selectGwLogList();
        System.out.println("gwLogs.size : " + gwLogs.size());
        long total = page.getTotal();
        System.out.println("total:"+total);
        List<GwLog> result = page.getResult();
        System.out.println("page result size : " + result.size());


    }

    /**
     * 可分页查询
     * @param pageNum   页码
     * @param pageSize  每页显示数，默认为0，当不传pageSize时查所有
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/logList2")
    public BRUtils findGwLogList2(@RequestParam(required = false,defaultValue = "1") int pageNum,
                                 @RequestParam(required = false,defaultValue = "0") int pageSize,
                                 HttpServletRequest request){
        System.out.println("===== 入参 ===  "+pageNum + "   " + pageSize);
        Page<GwLog> page = PageHelper.startPage(pageNum,pageSize);
        List<GwLog> gwLogs = gwLogServiceImpl.selectGwLogList();
        System.out.println("gwLogs.size : " + gwLogs.size());
        long totalNum = page.getTotal();
        System.out.println("total:"+totalNum);
        int pages = page.getPages();
        int pageNum1 = page.getPageNum();
        int pageSize1 = page.getPageSize();
        System.out.println("pages:"+pages+"  pageNum1:"+pageNum1+"  pageSize1:"+pageSize1);
        List<GwLog> result = page.getResult();
        System.out.println("page result size : " + result.size());

        return BRUtils.pageData(page);
//        return BRUtils.ok().put("page",new PageUtils(page));
    }





}
