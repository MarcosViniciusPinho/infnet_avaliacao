package com.infnet.avaliacao.exception;

/**
 * Classe que representa uma exceção em tempo de compilação.
 */
public class ExecutionException extends RuntimeException {

    /**
     * Método que passa o throw da exceção capturada.
     * @param cause cause
     */
    public ExecutionException(Throwable cause) {
        super(cause);
    }

    /**
     * Método que passa o throw da exceção capturada, juntamente com sua mensagem.
     * @param message message
     * @param cause cause
     */
    public ExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
