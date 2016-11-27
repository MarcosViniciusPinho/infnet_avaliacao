package com.infnet.avaliacao.mailer;

import javax.mail.MessagingException;

public interface ISmtpMailSender {

    /**
     * Método de envio de e-mails para os alunos do infnet
     * @param email email
     * @throws MessagingException MessagingException
     */
    void enviarEmail(String email) throws MessagingException;

}
