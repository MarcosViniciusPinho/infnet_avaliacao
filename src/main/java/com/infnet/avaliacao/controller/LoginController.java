package com.infnet.avaliacao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private static final String VIEW_HOME = "home";
    private static final String VIEW_LAYOUT1 = "layout1";
    private static final String ACTION_HOME = "/";
    private static final String ACTION_LAYOUT1 = "loginController/layout1";

    @RequestMapping(value = ACTION_HOME)
    public String home(){
        return VIEW_HOME;
    }

    @RequestMapping(value = ACTION_LAYOUT1)
    public String irParaLayout1(){
        return VIEW_LAYOUT1;
    }


}
