package com.infnet.avaliacao.persistence;

import com.infnet.avaliacao.entity.TemplateTopico;

import java.util.List;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela de template topico.
 */
public interface ITemplateTopicoDAO extends ICrudDAO<TemplateTopico> {

    /**
     * Recupera os templates topicos a partir de uma lista de seus respectivos ids.
     * @param idsTemplateTopico idsTemplateTopico
     * @return List<TemplateTopico>
     */
    List<TemplateTopico> findByIdIn(List<Long> idsTemplateTopico);

}