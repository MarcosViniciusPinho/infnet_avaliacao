package com.infnet.avaliacao.repository;

import com.infnet.avaliacao.entity.TemplateTopico;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela de template topico.
 */
@Repository
public interface ITemplateTopicoRepository extends ICrudRepository<TemplateTopico> {

    /**
     * Recupera os templates topicos a partir de uma lista de seus respectivos ids.
     * @param idsTemplateTopico idsTemplateTopico
     * @return List<TemplateTopico>
     */
    List<TemplateTopico> findByIdIn(List<Long> idsTemplateTopico);

}