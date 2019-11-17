package com.gdufe.dbmsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author: Bravery
 * @create: 2019-11-16 19:11
 **/

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String hello(Model m){
        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
        m.addAttribute("html","<a href='main1287.html' target='_parent'>账号正确进入【主功能】页面</a>");
        return "hello";
    }




}
