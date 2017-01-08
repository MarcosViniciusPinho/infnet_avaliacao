package com.infnet.avaliacao.business.facade;

import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;

import java.util.List;

/**
 * Classe que representa o façade de template pergunta.
 */
public interface ITemplatePerguntaFacade extends ICrudFacade<TemplatePerguntaDTO> {

    /**
     * Recupera a lista de templates pergunta a partir de uma lista de seus respectivos ids.
     * @param idsTemplatePergunta idsTemplatePergunta
     * @return List<TemplatePerguntaDTO>
     */
    List<TemplatePerguntaDTO> getListaTemplatesPerguntasPorId(List<Long> idsTemplatePergunta);

}