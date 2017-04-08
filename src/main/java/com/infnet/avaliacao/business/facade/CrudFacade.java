package com.infnet.avaliacao.business.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Classe que representa o facade generico
 */
public interface CrudFacade<V> {

    /**
     * Método que salva/altera a entidade no banco.
     * @param dto dto
     */
    void save(V dto);

    /**
     * Método que busca uma entidade no banco a partir de seu id
     * @param id id
     * @return V
     */
    V findById(Long id);

    /**
     * Método que lista todos os registros no banco
     * @return List<V>
     */
    List<V> findAll();

    /**
     * Método que lista todos os registros no banco
     * @return Page<V>
     */
    Page<V> findAllPaginated(Pageable pageable);

}
