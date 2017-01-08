package com.infnet.avaliacao.business.service;

import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.entity.TemplatePergunta;

import java.util.List;

/**
 * Classe que representa o serviço de template pergunta.
 */
public interface ITemplatePerguntaService extends ICrudService<TemplatePerguntaDTO, TemplatePergunta> {

    /**
     * Recupera a lista de templates perguntas a partir de uma lista de seus respectivos ids.
     * @param idsTemplatePergunta idsTemplatePergunta
     * @return List<TemplatePergunta>
     */
    List<TemplatePergunta> getListaTemplatesPerguntasPorId(List<Long> idsTemplatePergunta);

}