package com.infnet.avaliacao.business.facade;

/**
 * Classe que representa o facade generico
 */
public interface ICrudFacade<T> {

    void save(T entidade);

}
