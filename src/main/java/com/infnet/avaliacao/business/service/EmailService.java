package com.infnet.avaliacao.business.service;

import javax.mail.MessagingException;

public interface EmailService {

    /**
     * Método de envio de e-mails para os alunos do infnet
     * @param email email
     * @throws MessagingException MessagingException
     */
    void enviarEmail(String email) throws MessagingException;

}
