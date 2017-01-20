package com.infnet.avaliacao.dto.impl;

import com.infnet.avaliacao.dto.IDTO;
import com.infnet.avaliacao.entity.TemplateAvaliacao;
import com.infnet.avaliacao.entity.TemplateAvaliacaoTopicoPergunta;
import com.infnet.avaliacao.entity.TemplatePergunta;
import com.infnet.avaliacao.entity.TemplateTopico;
import com.infnet.avaliacao.exception.util.ParameterExceptionUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class TemplateAvaliacaoTopicoPerguntaDTO implements IDTO<TemplateAvaliacaoTopicoPergunta> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private TemplateAvaliacao templateAvaliacao;

    private TemplateTopico templateTopico;

    private TemplatePergunta templatePergunta;

    private boolean ativo;

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateAvaliacaoTopicoPergunta toEntity(){
        TemplateAvaliacaoTopicoPergunta templateAvaliacaoTopicoPergunta = new TemplateAvaliacaoTopicoPergunta();
        templateAvaliacaoTopicoPergunta.setId(this.getId());
        templateAvaliacaoTopicoPergunta.setTemplateAvaliacao(this.getTemplateAvaliacao());
        templateAvaliacaoTopicoPergunta.setTemplateTopico(this.getTemplateTopico());
        templateAvaliacaoTopicoPergunta.setTemplatePergunta(this.getTemplatePergunta());
        templateAvaliacaoTopicoPergunta.setAtivo(this.isAtivo());
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
        templateAvaliacaoTopicoPerguntaDTO.setTemplateAvaliacao(templateAvaliacaoTopicoPergunta.getTemplateAvaliacao());
        templateAvaliacaoTopicoPerguntaDTO.setTemplateTopico(templateAvaliacaoTopicoPergunta.getTemplateTopico());
        templateAvaliacaoTopicoPerguntaDTO.setTemplatePergunta(templateAvaliacaoTopicoPergunta.getTemplatePergunta());
        templateAvaliacaoTopicoPerguntaDTO.setAtivo(templateAvaliacaoTopicoPergunta.isAtivo());
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

    public TemplateAvaliacao getTemplateAvaliacao() {
        return templateAvaliacao;
    }

    public void setTemplateAvaliacao(TemplateAvaliacao templateAvaliacao) {
        this.templateAvaliacao = templateAvaliacao;
    }

    public TemplateTopico getTemplateTopico() {
        return templateTopico;
    }

    public void setTemplateTopico(TemplateTopico templateTopico) {
        this.templateTopico = templateTopico;
    }

    public TemplatePergunta getTemplatePergunta() {
        return templatePergunta;
    }

    public void setTemplatePergunta(TemplatePergunta templatePergunta) {
        this.templatePergunta = templatePergunta;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
