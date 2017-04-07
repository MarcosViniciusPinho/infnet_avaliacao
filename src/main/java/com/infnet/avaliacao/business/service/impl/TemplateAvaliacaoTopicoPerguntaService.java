package com.infnet.avaliacao.business.service.impl;

import com.infnet.avaliacao.business.service.ITemplateAvaliacaoTopicoPerguntaService;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoDTO;
import com.infnet.avaliacao.dto.impl.TemplateAvaliacaoTopicoPerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplatePerguntaDTO;
import com.infnet.avaliacao.dto.impl.TemplateTopicoDTO;
import com.infnet.avaliacao.entity.TemplateAvaliacaoTopicoPergunta;
import com.infnet.avaliacao.repository.TemplateAvaliacaoTopicoPerguntaRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class TemplateAvaliacaoTopicoPerguntaService implements ITemplateAvaliacaoTopicoPerguntaService {

    @Resource
    private TemplateAvaliacaoTopicoPerguntaRepository templateAvaliacaoTopicoPerguntaRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList) {
        this.templateAvaliacaoTopicoPerguntaRepository.save(TemplateAvaliacaoTopicoPerguntaDTO.convertListDtoToListEntity(
                templateAvaliacaoTopicoPerguntaDTOList));
    }

    private void inativarTodasMarcacoesDePerguntaDeUmaAvaliacaoPorTopico(TemplateAvaliacaoDTO templateAvaliacaoDTO,
                                                   TemplateTopicoDTO templateTopicoDTO){
        List<TemplateAvaliacaoTopicoPergunta> lista = this.templateAvaliacaoTopicoPerguntaRepository.findAllByTemplateAvaliacaoAndTemplateTopicoEquals(
                templateAvaliacaoDTO.toEntity(),
                templateTopicoDTO.toEntity());
        boolean existePerguntaSelecionada = !this.isNaoExistePerguntaSelecionada(templateTopicoDTO);
        if(existePerguntaSelecionada){
            this.inativarPerguntas(lista);
        }
    }

    private void inativarPerguntas(List<TemplateAvaliacaoTopicoPergunta> lista){
        for(TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta : lista){
            templateAvaliacaoTopicoPergunta.setAtivo(Boolean.FALSE);
            this.templateAvaliacaoTopicoPerguntaRepository.save(templateAvaliacaoTopicoPergunta);
        }
    }

    private boolean isNaoExistePerguntaSelecionada(TemplateTopicoDTO templateTopicoDTO){
        return CollectionUtils.isEmpty(templateTopicoDTO.getIdsTemplatePerguntaSelecionados());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplateAvaliacaoTopicoPerguntaDTO> produceAssociativeClass(List<TemplatePerguntaDTO> templatePerguntaDTOList,
                                                                                   TemplateTopicoDTO templateTopicoDTO,
                                                                                   TemplateAvaliacaoDTO templateAvaliacaoDTO){
        this.inativarTodasMarcacoesDePerguntaDeUmaAvaliacaoPorTopico(templateAvaliacaoDTO, templateTopicoDTO);
        List<TemplateAvaliacaoTopicoPerguntaDTO> templateAvaliacaoTopicoPerguntaDTOList = new ArrayList<>();
        for(TemplatePerguntaDTO templatePerguntaDTO : templatePerguntaDTOList){
            TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta = this.templateAvaliacaoTopicoPerguntaRepository.
                    findByTemplateAvaliacaoAndTemplateTopicoAndTemplatePerguntaEquals(templateAvaliacaoDTO.toEntity(),
                            templateTopicoDTO.toEntity(), templatePerguntaDTO.toEntity());
            templateAvaliacaoTopicoPerguntaDTOList.add(this.populateTemplateAvaliacaoTopicoPergunta(
                    templateAvaliacaoTopicoPergunta, templatePerguntaDTO, templateTopicoDTO, templateAvaliacaoDTO));
        }
        return templateAvaliacaoTopicoPerguntaDTOList;
    }

    private TemplateAvaliacaoTopicoPerguntaDTO populateTemplateAvaliacaoTopicoPergunta(TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta,
                                                 TemplatePerguntaDTO templatePerguntaDTO,
                                                 TemplateTopicoDTO templateTopicoDTO,
                                                 TemplateAvaliacaoDTO templateAvaliacaoDTO){
        TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = new TemplateAvaliacaoTopicoPerguntaDTO();
        if(templateAvaliacaoTopicoPergunta != null){
            templateAvaliacaoTopicoPerguntaDTO.setId(templateAvaliacaoTopicoPergunta.getId());
            templateAvaliacaoTopicoPerguntaDTO.setTemplatePergunta(templateAvaliacaoTopicoPergunta.getTemplatePergunta());
            templateAvaliacaoTopicoPerguntaDTO.setTemplateTopico(templateAvaliacaoTopicoPergunta.getTemplateTopico());
            templateAvaliacaoTopicoPerguntaDTO.setTemplateAvaliacao(templateAvaliacaoTopicoPergunta.getTemplateAvaliacao());
        } else{
            templateAvaliacaoTopicoPerguntaDTO.setTemplatePergunta(templatePerguntaDTO.toEntity());
            templateAvaliacaoTopicoPerguntaDTO.setTemplateTopico(templateTopicoDTO.toEntity());
            templateAvaliacaoTopicoPerguntaDTO.setTemplateAvaliacao(templateAvaliacaoDTO.toEntity());
        }
        templateAvaliacaoTopicoPerguntaDTO.setAtivo(Boolean.TRUE);
        return templateAvaliacaoTopicoPerguntaDTO;
    }
}