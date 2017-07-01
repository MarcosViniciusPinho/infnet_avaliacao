package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.TemplatePerguntaService;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.entity.TemplateAvaliacaoTopicoPergunta;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import com.infnet.avaliacao.repository.TemplateAvaliacaoTopicoPerguntaRepository;
import com.infnet.avaliacao.repository.TemplatePerguntaRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class TemplatePerguntaServiceImpl extends CrudServiceImpl<TemplatePerguntaDTO, TemplatePergunta> implements TemplatePerguntaService {

    @Resource
    private TemplatePerguntaRepository templatePerguntaRepository;

    @Resource
    private TemplateAvaliacaoTopicoPerguntaRepository templateAvaliacaoTopicoPerguntaRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplatePergunta> getListaTemplatesPerguntasPorId(List<Long> idsTemplateTopico) {
        ParameterExceptionUtil.validateObjectNull(idsTemplateTopico);
        return this.templatePerguntaRepository.findByIdIn(idsTemplateTopico);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TemplatePerguntaDTO> findAllComCheckedPerguntasMarcadas(TemplateTopicoDTO templateTopicoDTO,
                                                                        TemplateAvaliacaoDTO templateAvaliacaoDTO,
                                                                        Page<TemplatePerguntaDTO> templatePerguntaDTOList) {
        ParameterExceptionUtil.validateObjectNull(templateTopicoDTO);
        ParameterExceptionUtil.validateObjectNull(templateAvaliacaoDTO);
        ParameterExceptionUtil.validateObjectNull(templatePerguntaDTOList);
        for(TemplatePerguntaDTO templatePerguntaDTO : templatePerguntaDTOList){
            TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta = this.templateAvaliacaoTopicoPerguntaRepository.
                    findByTemplateAvaliacaoAndTemplateTopicoAndTemplatePerguntaEquals(templateAvaliacaoDTO.toEntity(),
                            templateTopicoDTO.toEntity(), templatePerguntaDTO.toEntity());
            if(templateAvaliacaoTopicoPergunta != null) {
                templatePerguntaDTO.setChecked(templateAvaliacaoTopicoPergunta.isAtivo());
            }
        }
        return templatePerguntaDTOList;
    }

}