package com.infnet.avaliacao.business.facade;

import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;

import java.util.List;

/**
 * Classe que representa o façade de template topico.
 */
public interface ITemplateTopicoFacade extends ICrudFacade<TemplateTopicoDTO> {

    /**
     * Recupera a lista de templates topicos a partir de uma lista de seus respectivos ids.
     * @param idsTemplateTopico idsTemplateTopico
     * @return List<TemplateTopico>
     */
    List<TemplateTopicoDTO> getListaTemplatesTopicosPorId(List<Long> idsTemplateTopico);

}