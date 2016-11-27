package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.IEmailService;
import com.infnet.avaliacao.mailer.ISmtpMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailService implements IEmailService{

    @Autowired
    private ISmtpMailSender mailSender;

    /**
     * {@inheritDoc}
     */
    @Override
    public void enviarEmail(String email) throws MessagingException {
        this.mailSender.enviarEmail(email);
    }

}
