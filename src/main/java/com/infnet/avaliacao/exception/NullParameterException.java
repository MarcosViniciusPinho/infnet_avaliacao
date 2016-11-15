package com.infnet.avaliacao.exception;

/**
 * Classe que representa uma exceção quando o parametro enviado for nulo.
 */
public class NullParameterException extends IllegalArgumentException {

    /**
     * Método que passa a mensagem da exceção capturada.
     * @param mensagem mensagem
     */
    public NullParameterException(String mensagem) {
        super(mensagem);
    }
}
