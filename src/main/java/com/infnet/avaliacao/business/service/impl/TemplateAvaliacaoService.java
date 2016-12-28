package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ITemplateAvaliacaoService;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.entity.TemplateAvaliacao;
import com.infnet.avaliacao.exception.CampoObrigatorioException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class TemplateAvaliacaoService extends CrudService<TemplateAvaliacaoDTO, TemplateAvaliacao> implements ITemplateAvaliacaoService {

    @Override
    public void validate(TemplateAvaliacaoDTO dto) {
        if(CollectionUtils.isEmpty(dto.getTemplateTopicoDTOList())){
            throw new CampoObrigatorioException("template.avaliacao.erro.topico.obrigatorio");
        }
    }
}