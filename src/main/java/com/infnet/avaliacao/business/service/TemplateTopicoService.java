package com.infnet.avaliacao.business.service;

import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.entity.TemplateTopico;

import java.util.List;

/**
 * Classe que representa o serviço de template topico.
 */
public interface TemplateTopicoService extends CrudService<TemplateTopicoDTO, TemplateTopico> {

    /**
     * Recupera a lista de templates topicos a partir de uma lista de seus respectivos ids.
     * @param idsTemplateTopico idsTemplateTopico
     * @return List<TemplateTopico>
     */
    List<TemplateTopico> getListaTemplatesTopicosPorId(List<Long> idsTemplateTopico);

}