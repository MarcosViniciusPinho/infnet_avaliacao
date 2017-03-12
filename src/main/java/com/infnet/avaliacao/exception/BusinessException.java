package com.infnet.avaliacao.exception;

import java.util.Set;

public class BusinessException extends RuntimeException {

    private Set<BusinessException> multipleMessages;
    private String mensagem;

    /**
     * Método que passa a mensagem da exceção capturada.
     * @param mensagem mensagem
     */
    public BusinessException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    public BusinessException(Set<BusinessException> businessExceptionSet){
        this.multipleMessages = businessExceptionSet;
    }

    public Set<BusinessException> getMultipleMessages() {
        return multipleMessages;
    }

    public String getMensagem() {
        return mensagem;
    }
}
