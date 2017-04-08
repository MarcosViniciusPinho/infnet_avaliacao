package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.controller.util.ActionConstant;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private static final String VIEW_HOME = "home";
    private static final String VIEW_LOGIN = "login";
    private static final String ACTION_LOGIN = "/login";
    private static final String ACTION_HOME = "/";

    @RequestMapping(value = ACTION_HOME)
    public String home(){
        return VIEW_HOME;
    }

    @RequestMapping(value = ACTION_LOGIN, method = RequestMethod.GET)
    public String login(@AuthenticationPrincipal User user){
        if(user != null){
            return ActionConstant.REDIRECT + ACTION_HOME;
        }
        return VIEW_LOGIN;
    }

}
