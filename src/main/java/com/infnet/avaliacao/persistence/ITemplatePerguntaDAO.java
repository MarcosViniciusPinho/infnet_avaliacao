package com.infnet.avaliacao.persistence;

import com.infnet.avaliacao.entity.TemplatePergunta;

import java.util.List;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela de template pergunta.
 */
public interface ITemplatePerguntaDAO extends ICrudDAO<TemplatePergunta> {

    /**
     * Recupera os templates pergunta a partir de uma lista de seus respectivos ids.
     * @param idsTemplatePergunta idsTemplatePergunta
     * @return List<TemplatePergunta>
     */
    List<TemplatePergunta> findByIdIn(List<Long> idsTemplatePergunta);
}