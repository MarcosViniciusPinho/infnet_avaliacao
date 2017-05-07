package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.controller.util.ActionConstant;
import com.infnet.avaliacao.controller.util.ViewConstant;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = ActionConstant.ACTION_HOME)
    public String home(){
        return ViewConstant.VIEW_HOME;
    }

    @RequestMapping(value = ActionConstant.ACTION_LOGIN, method = RequestMethod.GET)
    public String login(@AuthenticationPrincipal User user){
        if(user != null){
            return ActionConstant.REDIRECT + ActionConstant.ACTION_HOME;
        }
        return ViewConstant.VIEW_LOGIN;
    }

    @RequestMapping(ActionConstant.ACTION_ACESSO_NEGADO)
    public String acessoNegado(){
        return ViewConstant.VIEW_ACESSO_NEGADO;
    }

}
