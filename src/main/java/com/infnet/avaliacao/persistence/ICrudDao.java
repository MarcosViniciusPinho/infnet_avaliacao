package com.infnet.avaliacao.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Classe que representa as persistências do sistema.
 * @param <T> entidade
 */
@NoRepositoryBean
public interface ICrudDao<T> extends JpaRepository<T, Long> {
}
