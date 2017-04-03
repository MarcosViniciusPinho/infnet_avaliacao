package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ITemplateTopicoService;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.entity.TemplateTopico;
import com.infnet.avaliacao.exception.CampoObrigatorioException;
import com.infnet.avaliacao.repository.ITemplateTopicoRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class TemplateTopicoServiceImpl extends CrudServiceImpl<TemplateTopicoDTO, TemplateTopico> implements ITemplateTopicoService {

    @Resource
    private ITemplateTopicoRepository templateTopicoRepository;

    @Override
    public List<TemplateTopico> getListaTemplatesTopicosPorId(List<Long> idsTemplateTopico) {
        return this.templateTopicoRepository.findByIdIn(idsTemplateTopico);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(TemplateTopicoDTO dto) {
        if(CollectionUtils.isEmpty(dto.getTemplateAvaliacaoTopicoPerguntaDTOList())){
            throw new CampoObrigatorioException("template.avaliacao.erro.pergunta.obrigatorio");
        }
    }
}