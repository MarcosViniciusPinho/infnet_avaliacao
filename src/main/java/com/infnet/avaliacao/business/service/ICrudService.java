package com.infnet.avaliacao.business.service;

import java.util.List;

/**
 * Classe que representa o serviço generico
 */
public interface ICrudService<V, T> {

    /**
     * Método que salva/altera a entidade no banco.
     * @param dto dto
     */
    void save(V dto);

    /**
     * Método que busca uma entidade no banco a partir de seu id
     * @param id id
     * @return T
     */
    T findById(Long id);

    /**
     * Método que lista todos os registros no banco
     * @return List<T>
     */
    List<T> findAll();

    /**
     * Método que serve para fazer as validacoes
     * @param dto dto
     */
    void validate(V dto);

}
