package com.infnet.avaliacao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Classe responsável pela paginação por demanda da aplicação
 * @param <T>
 */
@FunctionalInterface
public interface PageRepository<T> {

    Page<T> findAll(Pageable pageable);

}
