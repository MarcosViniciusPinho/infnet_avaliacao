package com.infnet.avaliacao.exception;

/**
 * Classe que representa uma exceção quando um dado enviado da tela for obrigatório.
 */
public class CampoObrigatorioException extends NullParameterException {

    /**
     * Método que passa a mensagem da exceção capturada.
     * @param mensagem mensagem
     */
    public CampoObrigatorioException(String mensagem) {
        super(mensagem);
    }
}
