package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.dto.IDTO;
import com.infnet.avaliacao.entity.TemplateAvaliacaoTopicoPergunta;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class TemplateAvaliacaoTopicoPerguntaDTO implements IDTO<TemplateAvaliacaoTopicoPergunta> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private TemplateAvaliacaoDTO templateAvaliacaoDTO;

    private TemplateTopicoDTO templateTopicoDTO;

    private TemplatePerguntaDTO templatePerguntaDTO;

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateAvaliacaoTopicoPergunta toEntity(){
        TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta = new TemplateAvaliacaoTopicoPergunta();
        templateAvaliacaoTopicoPergunta.setId(this.getId());
        templateAvaliacaoTopicoPergunta.setTemplateAvaliacao(this.getTemplateAvaliacaoDTO().toEntity());
        templateAvaliacaoTopicoPergunta.setTemplateTopico(this.getTemplateTopicoDTO().toEntity());
        templateAvaliacaoTopicoPergunta.setTemplatePergunta(this.getTemplatePerguntaDTO().toEntity());
        return templateAvaliacaoTopicoPergunta;
    }

    /**
     * Método que converte uma entidade para um dto.
     * @param templateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta
     * @return TemplateAvaliacaoTopicoPerguntaDTO
     */
    public static TemplateAvaliacaoTopicoPerguntaDTO toDto(TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta){
        ParameterExceptionUtil.validateObjectNull(templateAvaliacaoTopicoPergunta);
        TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO = new TemplateAvaliacaoTopicoPerguntaDTO();
        templateAvaliacaoTopicoPerguntaDTO.setId(templateAvaliacaoTopicoPergunta.getId());
        templateAvaliacaoTopicoPerguntaDTO.setTemplateAvaliacaoDTO(
                TemplateAvaliacaoDTO.toDto(
                        templateAvaliacaoTopicoPergunta.getTemplateAvaliacao()));
        templateAvaliacaoTopicoPerguntaDTO.setTemplateTopicoDTO(
                TemplateTopicoDTO.toDto(
                        templateAvaliacaoTopicoPergunta.getTemplateTopico()));
        templateAvaliacaoTopicoPerguntaDTO.setTemplatePerguntaDTO(
                TemplatePerguntaDTO.toDto(
                        templateAvaliacaoTopicoPergunta.getTemplatePergunta()));
        return templateAvaliacaoTopicoPerguntaDTO;
    }

    /**
     * Método que converte uma lista de entidade para uma lista de dto.
     * @param entities entities
     * @return List<TemplateAvaliacaoTopicoPerguntaDTO>
     */
    public static List<TemplateAvaliacaoTopicoPerguntaDTO> convertListEntityToListDto(List<TemplateAvaliacaoTopicoPergunta> entities){
        List<TemplateAvaliacaoTopicoPerguntaDTO> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(entities)){
            for(TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta : entities){
                lista.add(toDto(templateAvaliacaoTopicoPergunta));
            }
        }
        return lista;
    }

    /**
     * Método que converte uma lista de dtos para uma lista de entidades.
     * @param dtos dtos
     * @return List<TemplateAvaliacaoTopicoPergunta>
     */
    public static List<TemplateAvaliacaoTopicoPergunta> convertListDtoToListEntity(List<TemplateAvaliacaoTopicoPerguntaDTO> dtos){
        List<TemplateAvaliacaoTopicoPergunta> lista = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(dtos)){
            for(TemplateAvaliacaoTopicoPerguntaDTO templateAvaliacaoTopicoPerguntaDTO : dtos){
                lista.add(templateAvaliacaoTopicoPerguntaDTO.toEntity());
            }
        }
        return lista;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TemplateAvaliacaoDTO getTemplateAvaliacaoDTO() {
        return templateAvaliacaoDTO;
    }

    public void setTemplateAvaliacaoDTO(TemplateAvaliacaoDTO templateAvaliacaoDTO) {
        this.templateAvaliacaoDTO = templateAvaliacaoDTO;
    }

    public TemplateTopicoDTO getTemplateTopicoDTO() {
        return templateTopicoDTO;
    }

    public void setTemplateTopicoDTO(TemplateTopicoDTO templateTopicoDTO) {
        this.templateTopicoDTO = templateTopicoDTO;
    }

    public TemplatePerguntaDTO getTemplatePerguntaDTO() {
        return templatePerguntaDTO;
    }

    public void setTemplatePerguntaDTO(TemplatePerguntaDTO templatePerguntaDTO) {
        this.templatePerguntaDTO = templatePerguntaDTO;
    }
}
