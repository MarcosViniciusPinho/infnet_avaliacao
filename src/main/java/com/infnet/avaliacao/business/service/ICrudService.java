package com.infnet.avaliacao.business.service;

import java.util.List;

/**
 * Classe que representa o serviço generico
 */
public interface ICrudService<T> {

    /**
     * Método que salva/altera a entidade no banco.
     * @param entity entity
     */
    void save(T entity);

    /**
     * Método que busca uma entidade no banco a partir de seu id
     * @param id id
     * @return T
     */
    T findById(Long id);

    /**
     * Método que exclui uma entidade do banco.
     * @param id id
     */
    void delete(Long id);

    /**
     * Método que lista todos os registros no banco
     * @return List<T>
     */
    List<T> findAll();

}
