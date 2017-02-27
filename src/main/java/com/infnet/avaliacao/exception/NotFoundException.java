package com.infnet.avaliacao.exception;

/**
 * Classe que representa uma exceção quando não é encontrado uma determinada
 * informação.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Método que passa a mensagem da exceção capturada.
     * @param mensagem mensagem
     */
    public NotFoundException(String mensagem) {
        super(mensagem);
    }
}
