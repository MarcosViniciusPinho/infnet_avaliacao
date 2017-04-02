package com.infnet.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Classe que representa as persistências do sistema.
 * @param <T> entidade
 */
@NoRepositoryBean
public interface ICrudRepository<T> extends JpaRepository<T, Long> {
}
