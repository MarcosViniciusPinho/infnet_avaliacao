package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ITemplateAvaliacaoService;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.entity.TemplateAvaliacao;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class TemplateAvaliacaoService extends CrudService<TemplateAvaliacaoDTO, TemplateAvaliacao> implements ITemplateAvaliacaoService {
}