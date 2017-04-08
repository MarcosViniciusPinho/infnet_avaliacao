package com.infnet.avaliacao.controller;

import com.infnet.avaliacao.business.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/enviarEmail")
    public String enviarEmail() {

        String[] emails = {"marcosjava2008@gmail.com"};

        try{
            for(String email : emails){
                this.emailService.enviarEmail(email);
            }
        } catch (MessagingException e){
            throw new RuntimeException("Erro ao enviar email", e);
        }
        return "Email enviado com sucesso!";
    }

}
