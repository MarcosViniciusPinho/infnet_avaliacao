package com.infnet.avaliacao.repository;

import com.infnet.avaliacao.entity.TemplateAvaliacao;
import org.springframework.stereotype.Repository;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela de template avaliação.
 */
@Repository
public interface TemplateAvaliacaoRepository extends CrudRepository<TemplateAvaliacao> {
}