package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ITemplateTopicoService;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.entity.TemplateTopico;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class TemplateTopicoService extends CrudService<TemplateTopicoDTO, TemplateTopico> implements ITemplateTopicoService {
}