package com.infnet.avaliacao.business.service;

/**
 * Classe que representa o serviço de Usuario
 */
public interface ICrudService<T> {

    void save(T entidade);

}
