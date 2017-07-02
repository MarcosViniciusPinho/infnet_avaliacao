package com.infnet.avaliacao.mailer.impl;

import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.mailer.SmtpMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class SmtpMailSenderImpl implements SmtpMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    private static final String ASSUNTO = "[INFNET] Formulário de avaliação";
    private static final String CORPO = "Olá aluno, chegou um momento importante que é seu feedback" +
            " sobre o curso. <br /> Por favor faça sua avaliação institucional acessando o link ";

    /**
     * {@inheritDoc}
     */
    @Override
    public void enviarEmail(String email) throws MessagingException {
        ParameterExceptionUtil.validateObjectNull(email);
        String linkEmailAluno = "<a href='http://localhost:8081/sai/resposta/avaliacao/aluno/00456787323/turma/1'>Clique aqui</a>";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(ASSUNTO);
        helper.setTo(email);
        helper.setText(CORPO  + linkEmailAluno, true);
        javaMailSender.send(message);
    }

}
