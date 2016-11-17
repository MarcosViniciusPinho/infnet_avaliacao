package com.infnet.avaliacao.exception;

/**
 * Classe que representa uma exceção quando uma informação for única
 * e a mesma já existir em outro registro.
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
