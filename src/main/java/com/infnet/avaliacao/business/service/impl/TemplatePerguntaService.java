package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ITemplatePerguntaService;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.exception.CampoObrigatorioException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class TemplatePerguntaService extends CrudService<TemplatePerguntaDTO, TemplatePergunta> implements ITemplatePerguntaService {

    @Override
    public void validate(TemplatePerguntaDTO dto) {
        if(CollectionUtils.isEmpty(dto.getTemplateAvaliacaoTopicoPerguntaDTOList())){
            throw new CampoObrigatorioException("template.avaliacao.erro.topico.obrigatorio");
        }
    }
}