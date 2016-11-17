package com.infnet.avaliacao.exception;

/**
 * Classe que representa uma exceção quando um dado enviado da tela for obrigatório.
 */
public class UniqueException extends RuntimeException {

    /**
     * Método que passa a mensagem da exceção capturada.
     * @param mensagem mensagem
     */
    public UniqueException(String mensagem) {
        super(mensagem);
    }
}
