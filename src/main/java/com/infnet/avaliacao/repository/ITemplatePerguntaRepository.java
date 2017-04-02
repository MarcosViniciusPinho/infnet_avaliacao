package com.infnet.avaliacao.repository;

import com.infnet.avaliacao.entity.TemplatePergunta;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe que serve para fazer as operações de CRUD para a tabela de template pergunta.
 */
@Repository
public interface ITemplatePerguntaRepository extends ICrudRepository<TemplatePergunta> {

    /**
     * Recupera os templates pergunta a partir de uma lista de seus respectivos ids.
     * @param idsTemplatePergunta idsTemplatePergunta
     * @return List<TemplatePergunta>
     */
    List<TemplatePergunta> findByIdIn(List<Long> idsTemplatePergunta);
}