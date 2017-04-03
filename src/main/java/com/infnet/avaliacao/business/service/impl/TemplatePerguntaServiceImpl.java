package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ITemplatePerguntaService;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.entity.TemplateAvaliacaoTopicoPergunta;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.repository.ITemplateAvaliacaoTopicoPerguntaRepository;
import com.infnet.avaliacao.repository.ITemplatePerguntaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class TemplatePerguntaServiceImpl extends CrudServiceImpl<TemplatePerguntaDTO, TemplatePergunta> implements ITemplatePerguntaService {

    @Resource
    private ITemplatePerguntaRepository templatePerguntaRepository;

    @Resource
    private ITemplateAvaliacaoTopicoPerguntaRepository templateAvaliacaoTopicoPerguntaRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplatePergunta> getListaTemplatesPerguntasPorId(List<Long> idsTemplateTopico) {
        return this.templatePerguntaRepository.findByIdIn(idsTemplateTopico);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplatePerguntaDTO> findAllComCheckedPerguntasMarcadas(TemplateTopicoDTO templateTopicoDTO,
                                                                        TemplateAvaliacaoDTO templateAvaliacaoDTO) {
        List<TemplatePerguntaDTO> templatePerguntaDTOList = TemplatePerguntaDTO.convertListEntityToListDto(templatePerguntaRepository.findAll());
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