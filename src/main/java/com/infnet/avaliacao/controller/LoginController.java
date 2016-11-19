package com.infnet.avaliacao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private static final String VIEW_HOME = "home";
    private static final String VIEW_LOGIN = "login";
    private static final String ACTION_LOGIN = "/";
    private static final String ACTION_HOME = "/home";

    @RequestMapping(value = ACTION_HOME)
    public String home(){
        return VIEW_HOME;
    }

    @RequestMapping(value = ACTION_LOGIN)
    public String login(){
        return VIEW_LOGIN;
    }

}
